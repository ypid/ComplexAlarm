package com.github.ypid.complexalarm;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class OpeningHoursWebView extends ActionBarActivity {
    private static final String DEFAULT_OH_VALUE = "Fr-Sa 18:00-06:30";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView mWebView = new WebView(this);
        Bundle extras = getIntent().getExtras();
        String oh_value = DEFAULT_OH_VALUE;
        int oh_mode = 0;
        if (extras != null) {
            oh_mode = extras.getInt("oh_mode");
            oh_value = extras.getString("oh_value");
            if (oh_value == null) {
                oh_value = DEFAULT_OH_VALUE;
            }
        }
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/javascript-libs/opening_hours/demo.html"
                + "?EXP=" + EncodingUtil.encodeURIComponent(oh_value)
                + "&mode=" + oh_mode);
        setContentView(mWebView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alarm_add, menu);
        return true;
    }

    public void onCancelClick(View view) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        case R.id.action_cancel:
            finish();
            return true;
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
