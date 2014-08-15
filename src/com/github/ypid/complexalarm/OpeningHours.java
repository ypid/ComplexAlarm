package com.github.ypid.complexalarm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

public class OpeningHours {
    JsEvaluator     mJsEvaluator;
    private Scanner scanner;

    protected String getFileContent(String fileName, Context context) throws IOException {
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

    protected void loadOpeningHours(Context context) {
        Log.d("OpeningHours", "Loading up opening_hours.js");
        mJsEvaluator = new JsEvaluator(context);
        final String librarySrouce = loadJs("javascript-libs/opening_hours/opening_hours.js", context);
        mJsEvaluator.evaluate(librarySrouce);

        final String code = "var crashed = true;" +
        		"try {" +
        		"    oh = new opening_hours(value, nominatiomTestJSON);" +
        		"    warnings = oh.getWarnings();" +
                "    crashed = false;" +
        		"} catch(err) {" +
        		"    crashed = err;" +
        		"}";

        mJsEvaluator.evaluate(code, new JsCallback() {
            @Override
            public void onResult(final String resultValue) {
                Log.d("OpeningHours", String.format("Result: %s", resultValue));
            }
        });
    }
}
