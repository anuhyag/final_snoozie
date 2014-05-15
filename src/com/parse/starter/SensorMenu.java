package com.parse.starter;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.parse.ParseObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
// http://stackoverflow.com/questions/12326429/get-multiple-sensor-data-at-the-same-time-in-android
public class SensorMenu extends Activity implements SensorEventListener {

	LinearLayout layout;
	RadioButton acc_radio, humid_radio, light_radio, temperature_radio,
			selected_button;
	Button acc_button, light_button, humid_button, temp_button, graph_button;
	String radioText, sensor_on = null;
	boolean run = false;
	ProgressDialog progress;
	SensorManager manager;
	SensorEventListener listener;
	int num_iters, child_count, count = 0;
	RadioGroup options_group;
	float[] vals_array;
	GraphView graphView = null;
	EditText number;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_menu);
		layout = (LinearLayout) findViewById(R.id.sensor_linear_layout);

		manager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		graph_button = (Button) findViewById(R.id.data_btn);
		options_group = (RadioGroup) findViewById(R.id.sensor_radio_group);

		graph_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (graphView != null) {
					Log.d("GRAPHVIEW", "not null");
					layout.removeViewAt(child_count - 1);
				}
				number = (EditText) findViewById(R.id.void_data);
				num_iters = Integer.parseInt(number.getText().toString());
				Log.d("NUM_ITERS", number.getText().toString());

				int selected = options_group.getCheckedRadioButtonId();
				Log.d("SELECTED_VALUE", Integer.toString(selected));
				selected_button = (RadioButton) findViewById(selected);

				sensor_on = (String) selected_button.getText().toString();
				Log.d("SENSOR-ON", sensor_on);
				vals_array = new float[num_iters];

				getMeasurements();
			}
		});
	}

	void getMeasurements() {
		progress = new ProgressDialog(this);
		progress.setTitle("Generating Graph");
		progress.setMessage("Please Wait...");
		progress.show();
		// To dismiss the dialog
		listener = new SensorEventListener() {

			@Override
			public void onAccuracyChanged(Sensor arg0, int arg1) {
			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				Sensor sensor = event.sensor;

				if (count < num_iters) {
					if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY
							&& sensor_on.equals("Humidity")) {
						Log.d("HUMIDITY", Integer.toString(count));
						float humidity = event.values[0];
						vals_array[count] = humidity;
						ParseObject humidityReading = new ParseObject(
								"Humidity");
						humidityReading.put("time",
								String.valueOf(event.timestamp));
						humidityReading.put("humidity", humidity);
						humidityReading.saveInBackground();
						count += 1;
					} else if (sensor.getType() == Sensor.TYPE_LIGHT
							&& sensor_on.equals("Light")) {
						float light = event.values[0];
						Log.d("LIGHT", Integer.toString(count));
						vals_array[count] = light;
						ParseObject lightReading = new ParseObject("Light");
						lightReading.put("time",
								String.valueOf(event.timestamp));
						lightReading.put("light", light);
						lightReading.saveInBackground();
						count += 1;
					} else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE
							&& sensor_on.equals("Temperature")) {
						float temperature = event.values[0];
						Log.d("TEMPERATURE", Integer.toString(count));
						vals_array[count] = temperature;
						// Add all the data to Parse
						ParseObject temperatureReading = new ParseObject(
								"Temperature");
						temperatureReading.put("time",
								String.valueOf(event.timestamp));
						temperatureReading.put("temperature", temperature);
						temperatureReading.saveInBackground();
						count += 1;
					}
				} else {
					count = 0;
					graphData();
					TextView sensor_title = (TextView) findViewById(R.id.sensor_title);
					sensor_title.setVisibility(View.GONE);
					options_group.setVisibility(View.GONE);
					TextView data_point_heading = (TextView) findViewById(R.id.data_point_heading);
					data_point_heading.setVisibility(View.GONE);
					EditText void_data = (EditText) findViewById(R.id.void_data);
					void_data.setVisibility(View.GONE);
					graph_button.setVisibility(View.GONE);
					manager.unregisterListener(listener, manager
							.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY));
					manager.unregisterListener(listener,
							manager.getDefaultSensor(Sensor.TYPE_LIGHT));
					manager.unregisterListener(listener, manager
							.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE));
				}
			}
		};
		manager.registerListener(listener,
				manager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),
				SensorManager.SENSOR_DELAY_NORMAL);
		manager.registerListener(listener,
				manager.getDefaultSensor(Sensor.TYPE_LIGHT),
				SensorManager.SENSOR_DELAY_NORMAL);
		manager.registerListener(listener,
				manager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	void graphData() {
		progress.dismiss();
		GraphViewData[] data = new GraphViewData[num_iters];
		for (int i = 1; i < num_iters + 1; i++) {
			data[i - 1] = new GraphViewData(i, vals_array[i - 1]);
		}
		GraphViewSeries exampleSeries = new GraphViewSeries(data);
		graphView = new LineGraphView(this, "");
		graphView.addSeries(exampleSeries); // data
		child_count = layout.getChildCount();
		layout.addView(graphView);
		Button return_button = (Button) findViewById(R.id.return_btn);
		return_button.setVisibility(View.VISIBLE);
		TextView name_text = (TextView) findViewById(R.id.changing_title);
		name_text.setText(sensor_on + " Data (" + Integer.toString(num_iters) + " points)");
		name_text.setVisibility(View.VISIBLE);
		
		return_button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent returnIntent = new Intent(SensorMenu.this, ParseStarterProjectActivity.class);
				SensorMenu.this.startActivity(returnIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	}

}
