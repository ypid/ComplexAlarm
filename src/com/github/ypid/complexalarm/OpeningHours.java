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
    private JsEvaluator mJsEvaluator;
    private Scanner scanner;

    private String getFileContent(String fileName, Context context) throws IOException {
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
        String librarySrouce = loadJs("javascript-libs/suncalc/suncalc.min.js", context);
        mJsEvaluator.evaluate(librarySrouce);
        librarySrouce = loadJs("javascript-libs/opening_hours/opening_hours.min.js", context);
        // mJsEvaluator.evaluate(librarySrouce);
    }
    
    protected void evalOpeningHours(String value, String nominatiomJSON, byte oh_mode) {
        String ohConstructorCall = JsFunctionCallFormatter.toString("opening_hours", value, nominatiomJSON, oh_mode);
        Log.d("OpeningHours constructor", ohConstructorCall);
        final String code = "var oh, warnings, crashed = true;" +
                "try {" +
                "    oh = new " + ohConstructorCall + ";" +
                "    warnings = oh.getWarnings();" +
                "    crashed = false;" +
                "} catch(err) {" +
                "    crashed = err;" +
                "}" +
               // "crashed.toString();" +
                "opening_hours;";
        
        final String suncalcTest = "var times = SunCalc.getTimes(new Date(), 51.5, -0.1);" +
        		"var sunriseStr = times.sunrise.getHours() + ':' + times.sunrise.getMinutes();" +
        		"sunriseStr;";
        
        mJsEvaluator.evaluate(suncalcTest, new JsCallback() {
            @Override
            public void onResult(final String resultValue) {
                Log.d("OpeningHours", String.format("Result: %s", resultValue));
            }
        });
    }
    protected void evalOpeningHours(String value, String nominatiomJSON) {
        evalOpeningHours(value, nominatiomJSON, (byte)0);
    }
    protected void evalOpeningHours(String value) {
        evalOpeningHours(value, "{}");
    }
}
