package com.github.ypid.complexalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ActivityAlarmList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

        /*
        Log.d(getResources().getString(R.string.app_name), "Loading up opening_hours.js");
        OpeningHours oh = new OpeningHours(getApplicationContext());
        oh.evalOpeningHours("Fr-Sa 18:00-(sunrise+02:00)");
        oh.evalOpeningHours("PH");
        oh.evalOpeningHours("easter: open \"Around easter\"");
        Log.d("OpeningHourss", String.format("date: %s", oh.getDate()));
        Log.d("OpeningHoursretrun", String.format("date: %s", oh.returnDate()));
        */
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alarm_list, menu);
        return true;
    }

    public void onAlarmAddClick(View view) {
        startActivity(new Intent(this, ActivityAlarmAdd.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
        case R.id.action_add_alarm:
            startActivity(new Intent(this, ActivityAlarmAdd.class));
            return true;
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
