package com.parse.starter;

import java.util.Calendar;

import com.parse.ParseObject;

import android.os.Bundle;
import android.os.PowerManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class Screen extends Activity {

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen);
		Log.d("SCREEN", "on create");
		
		PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
		if (powerManager.isScreenOn()){ 
			ParseObject powerReading = new ParseObject("ScreenStatus");
		    powerReading.put("status", "yes");
		    String time = Integer.toString(hour) + ":" + Integer.toString(minute) + "." + Integer.toString(second);
		    powerReading.put("time", time);
			powerReading.saveInBackground();
		} else {
			Log.d("SCREEN OFF", "boo");	
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.screen, menu);
		return true;
	}

}
