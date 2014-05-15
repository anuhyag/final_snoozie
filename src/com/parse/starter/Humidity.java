package com.parse.starter;

import com.parse.ParseObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@SuppressLint("InlinedApi") public class Humidity extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mHumidity;

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_humidity);

		// Get an instance of the sensor service, and use that to get an instance of
		// a particular sensor.
		init();
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
	}

	public void init(){
		TableLayout stk = (TableLayout) findViewById(R.id.table_humidity);
		TableRow tbrow0 = new TableRow(this);
		TextView tv0 = new TextView(this);
		tv0.setText("Time");
		tv0.setTextColor(Color.BLACK);
		tbrow0.addView(tv0);
		TextView tv1 = new TextView(this);
		tv1.setText("Humidity");
		tv1.setTextColor(Color.WHITE);
		tbrow0.addView(tv1);
		stk.addView(tbrow0);
	}

	@Override
	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
		float humidity = event.values[0];

		// Save data to Parse    
		ParseObject humidityReading = new ParseObject("Humidity");
		humidityReading.put("time", String.valueOf(event.timestamp));
		humidityReading.put("humidity", humidity);
		humidityReading.saveInBackground();

		// Dynamically add to table on phone
		TableLayout stk = (TableLayout) findViewById(R.id.table_humidity);
		TableRow tbrow = new TableRow(this);
		TextView t1v = new TextView(this);
		t1v.setText(String.valueOf(event.timestamp));
		t1v.setTextColor(Color.BLACK);
		t1v.setGravity(Gravity.LEFT);
		tbrow.addView(t1v);
		TextView t2v = new TextView(this);
		t2v.setText(Float.toString(humidity));
		t2v.setTextColor(Color.WHITE);
		t2v.setGravity(Gravity.LEFT);
		tbrow.addView(t2v);
		stk.addView(tbrow);
	}

	@Override
	protected void onResume() {
		// Register a listener for the sensor.
		super.onResume();
		mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// Be sure to unregister the sensor when the activity pauses.
		super.onPause();
		mSensorManager.unregisterListener(this);
	}


}