package com.github.ypid.complexalarm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.JsFunctionCallFormatter;
import com.evgenii.jsevaluator.interfaces.JsCallback;

/*
 * Simple wrapper for the API documented here:
 * https://github.com/ypid/opening_hours.js#library-api
 */
public class OpeningHours {
    private JsEvaluator  mJsEvaluator;
    final private String nominatiomTestJSONString = "{\"place_id\":\"44651229\",\"licence\":\"Data \u00a9 OpenStreetMap contributors, ODbL 1.0. http://www.openstreetmap.org/copyright\",\"osm_type\":\"way\",\"osm_id\":\"36248375\",\"lat\":\"49.5400039\",\"lon\":\"9.7937133\",\"display_name\":\"K 2847, Lauda-K\u00f6nigshofen, Main-Tauber-Kreis, Regierungsbezirk Stuttgart, Baden-W\u00fcrttemberg, Germany, European Union\",\"address\":{\"road\":\"K 2847\",\"city\":\"Lauda-K\u00f6nigshofen\",\"county\":\"Main-Tauber-Kreis\",\"state_district\":\"Regierungsbezirk Stuttgart\",\"state\":\"Baden-W\u00fcrttemberg\",\"country\":\"Germany\",\"country_code\":\"de\",\"continent\":\"European Union\"}}";
    private Scanner      scanner;
    private String globalResult;

    private String getFileContent(String fileName, Context context)
            throws IOException {
        final AssetManager am = context.getAssets();
        final InputStream inputStream = am.open(fileName);

        scanner = new Scanner(inputStream, "UTF-8");
        return scanner.useDelimiter("\\A").next();
    }

    private String loadJs(String fileName, Context context) {
        try {
            return getFileContent(fileName, context);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected OpeningHours(Context context) {
        Log.d("OpeningHours", "Loading up opening_hours.js");
        mJsEvaluator = new JsEvaluator(context);
        String librarySrouce = loadJs("javascript-libs/suncalc/suncalc.min.js",
                context);
        mJsEvaluator.evaluate(librarySrouce);
        librarySrouce = loadJs(
                "javascript-libs/opening_hours/opening_hours.min.js", context);
        mJsEvaluator.evaluate(librarySrouce);
    }

    protected String evalOpeningHours(String value, String nominatiomJSON,
            byte oh_mode) {
        String ohConstructorCall = "new opening_hours('" + value
                + "', JSON.parse('" + nominatiomJSON + "'), " + oh_mode + ")";
        Log.d("OpeningHours constructor", ohConstructorCall);
        final String code = "var oh, warnings, crashed = true;" + "try {"
                + "    oh = " + ohConstructorCall + ";"
                + "    warnings = oh.getWarnings();" + "    crashed = false;"
                + "} catch(err) {" + "    crashed = err;" + "}"
                + "oh.getNextChange().toString();" +
                // "crashed.toString();" +
                "";

        mJsEvaluator.evaluate(code, new JsCallback() {
            @Override
            public void onResult(final String resultValue) {
                Log.d("OpeningHours", String.format("Result: %s", resultValue));
            }
        });
        return "test";
    }
    protected String getDate() {
        globalResult = null;
        mJsEvaluator.evaluate("new Date().toString()", new JsCallback() {
            @Override
            public void onResult(final String resultValue) {
                Log.d("Date", String.format("Result: %s", resultValue));
                // Block until event occurs.
                globalResult = resultValue;
                Log.d("Date", String.format("globalResult: %s", globalResult));
            }
        });
        for (int i = 0; i < 100; i++) {
            Log.d("Date", String.format("%d, %s", i, globalResult));
            try {
                Log.d("Date", "sleep");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                Log.d("Date", "Catch");
                e.printStackTrace();
            }
            if (globalResult != null) {
                break;
            }
        }
        return globalResult;
    }
    protected String returnDate() {
        return globalResult;
    }


    protected String evalOpeningHours(String value, String nominatiomJSON) {
        return evalOpeningHours(value, nominatiomJSON, (byte) 0);
    }

    protected String evalOpeningHours(String value) {
        // evalOpeningHours(value, "{}");
        return evalOpeningHours(value, nominatiomTestJSONString); // FIXME
                                                                  // testing
                                                                  // only
    }
}
