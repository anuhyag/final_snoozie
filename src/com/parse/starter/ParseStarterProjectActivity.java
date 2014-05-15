package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.LineGraphView;
import com.parse.ParseAnalytics;

public class ParseStarterProjectActivity extends Activity {
	
	Button phone_button, sensor_button, survey_button, consumption_button, sleep_button;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
		addListenersOnButtons();
	}

	private void addListenersOnButtons() {
		phone_button = (Button) findViewById(R.id.phone_button);
		phone_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent phoneIntent = new Intent(ParseStarterProjectActivity.this, Phone.class);
				ParseStarterProjectActivity.this.startActivity(phoneIntent);
			}
		});
		
		sensor_button = (Button) findViewById(R.id.sensor_button);
		sensor_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent sensorIntent = new Intent(ParseStarterProjectActivity.this, SensorMenu.class);
				ParseStarterProjectActivity.this.startActivity(sensorIntent);
			}
		});
		
		survey_button = (Button) findViewById(R.id.survey_button);
		survey_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent surveyIntent = new Intent(ParseStarterProjectActivity.this, Survey.class);
				ParseStarterProjectActivity.this.startActivity(surveyIntent);
			}
		});
		
		
		consumption_button = (Button) findViewById(R.id.consumption_button);
		consumption_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent consumptionIntent = new Intent(ParseStarterProjectActivity.this, AlcoholFood.class);
				ParseStarterProjectActivity.this.startActivity(consumptionIntent);
			}
		});
		
		sleep_button = (Button) findViewById(R.id.sleep_button);
		sleep_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent sleepIntent = new Intent(ParseStarterProjectActivity.this, Sleep.class);
				ParseStarterProjectActivity.this.startActivity(sleepIntent);
			}
		});
		
	}
}
