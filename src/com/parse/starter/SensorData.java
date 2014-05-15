package com.parse.starter;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.parse.ParseObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorData extends Activity implements SensorEventListener {

	RadioButton acc_radio, humid_radio, light_radio, temperature_radio,
			selected_button;
	Button acc_button, light_button, humid_button, temp_button, graph_button;
	String radioText, sensor_on = null;
	boolean run = false;
	int num_iters, count = 0;
	RadioGroup options_group;
	float[] vals_array;
	EditText number;

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
//
//		SensorManager manager = (SensorManager) this
//				.getSystemService(Context.SENSOR_SERVICE);
		graph_button = (Button) findViewById(R.id.data_btn);
		options_group = (RadioGroup) findViewById(R.id.sensor_radio_group);

		graph_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				number = (EditText) findViewById(R.id.void_data);
				num_iters = Integer.parseInt(number.getText().toString());
				int selected = options_group.getCheckedRadioButtonId();
				selected_button = (RadioButton) findViewById(selected);
				sensor_on = (String) selected_button.getText();
				vals_array = new float[num_iters];
				run = true;
			}

		});
		if (run) {
//			SensorEventListener listener = new SensorEventListener() {
//				@Override
//				public void onAccuracyChanged(Sensor arg0, int arg1) {
//				}
//
//				@Override
//				public void onSensorChanged(SensorEvent event) {
//					Sensor sensor = event.sensor;
//					if (count < num_iters) {
//						if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY
//								&& sensor_on == "humidity") {
//							float humidity = event.values[0];
//							vals_array[count] = humidity;
//							ParseObject humidityReading = new ParseObject(
//									"Humidity");
//							humidityReading.put("time",
//									String.valueOf(event.timestamp));
//							humidityReading.put("humidity", humidity);
//							humidityReading.saveInBackground();
//							count += 1;
//
//						} else if (sensor.getType() == Sensor.TYPE_LIGHT
//								&& sensor_on == "light") {
//							float light = event.values[0];
//							vals_array[count] = light;
//							ParseObject lightReading = new ParseObject("Light");
//							lightReading.put("time",
//									String.valueOf(event.timestamp));
//							lightReading.put("light", light);
//							lightReading.saveInBackground();
//							count += 1;
//
//						} else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE
//								&& sensor_on == "temperature") {
//							float temperature = event.values[0];
//							vals_array[count] = temperature;
//							// Add all the data to Parse
//							ParseObject temperatureReading = new ParseObject(
//									"Temperature");
//							temperatureReading.put("time",
//									String.valueOf(event.timestamp));
//							temperatureReading.put("temperature", temperature);
//							temperatureReading.saveInBackground();
//							count += 1;
//
//						}
//					} else {
//						graphData();
//					}
//				}
//			};
		}
	}

	void graphData() {
		GraphViewData[] data = new GraphViewData[num_iters];
		for (int i = 1; i < num_iters + 1; i++) {
			data[i-1] = new GraphViewData(i, vals_array[i]);
		}
		GraphViewSeries exampleSeries = new GraphViewSeries(data);

		GraphView graphView = new LineGraphView(this, sensor_on + " data");
		graphView.addSeries(exampleSeries); // data

		LinearLayout layout = (LinearLayout) findViewById(R.id.sensor_linear_layout);
		layout.addView(graphView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sensor, menu);
		return true;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

	}

}
