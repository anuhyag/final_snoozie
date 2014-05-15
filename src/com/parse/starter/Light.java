package com.parse.starter;

import java.util.ArrayList;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.LineGraphView;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Light extends Activity implements SensorEventListener {
	private SensorManager mSensorManager;
	private Sensor mLight;
	public ArrayList<Float> light_graph_vals = new ArrayList<Float>();
	public int val_count = 0;
	Button vis_button;
	Context context = this;

	@Override
	public final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_light);

		init();
		addListenersOnButtons();
		// Get an instance of the sensor service, and use that to get an instance of
		// a particular sensor.
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		}

	public void init(){
		TableLayout stk = (TableLayout) findViewById(R.id.table_light);
		TableRow tbrow0 = new TableRow(this);
		TextView tv0 = new TextView(this);
		tv0.setText("Time");
		tv0.setTextColor(Color.BLACK);
		tbrow0.addView(tv0);
		TextView tv1 = new TextView(this);
		tv1.setText("Light");
		tv1.setTextColor(Color.WHITE);
		tbrow0.addView(tv1);
		stk.addView(tbrow0);
	}
	

	public final void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Do something here if sensor accuracy changes.
	}

	private void addListenersOnButtons() {
		vis_button = (Button) findViewById(R.id.vis_light_button);
		vis_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent graphIntent = new Intent(Light.this, Graph.class);
				for (int j=0; j<val_count; j++){
					Log.d("REACED HERE", "value" + Integer.toString(j));
					graphIntent.putExtra("value" + Integer.toString(j), light_graph_vals.get(j));
				}
				graphIntent.putExtra("valcount", val_count);
				Log.d("NOW I REACHED HERE", "YEP");
				Light.this.startActivity(graphIntent);
				
			}
		});
	}

	@Override
	public final void onSensorChanged(SensorEvent event) {
		val_count +=1 ;
		float light = event.values[0];

		// save data in Parse	    
		ParseObject lightReading = new ParseObject("Light");
		lightReading.put("time", String.valueOf(event.timestamp));
		lightReading.put("light", light);
		lightReading.saveInBackground();

		// add value to list
		light_graph_vals.add(light);
		// add data dynamically to the phone
		TableLayout stk = (TableLayout) findViewById(R.id.table_light);
		TableRow tbrow = new TableRow(this);
		TextView t1v = new TextView(this);
		t1v.setText(String.valueOf(event.timestamp));
		t1v.setTextColor(Color.BLACK);
		t1v.setGravity(Gravity.LEFT);
		tbrow.addView(t1v);
		TextView t2v = new TextView(this);
		t2v.setText(Float.toString(light));
		t2v.setTextColor(Color.WHITE);
		t2v.setGravity(Gravity.LEFT);
		tbrow.addView(t2v);
		stk.addView(tbrow);
	}

	@Override
	protected void onResume() {
		// Register a listener for the sensor.
		super.onResume();
		mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		// Be sure to unregister the sensor when the activity pauses.
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
}