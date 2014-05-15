package com.parse.starter;

import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

public class AlcoholFood extends Activity {
	// used http://www.mkyong.com/android/android-radio-buttons-example/ as a
	// resource
	String radioText, item, quantity, time;
	RadioGroup consumptionRadioGroup;
	Button consumptionButton;
	EditText quantityEditView, itemEditView, timeEditView;
	TextView quantityTextView, itemTextView;
	RadioButton food_radio, alcohol_radio, caffeine_radio;
	Spinner time_spinner, am_pm_spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alcohol_food);

		consumptionRadioGroup = (RadioGroup) findViewById(R.id.consumption_radio_group);
		consumptionButton = (Button) findViewById(R.id.consumption_btn);

		quantityEditView = (EditText) findViewById(R.id.void_quantity);
		quantityTextView = (TextView) findViewById(R.id.quantity_heading);
		itemEditView = (EditText) findViewById(R.id.void_item);
		itemTextView = (TextView) findViewById(R.id.item_heading);
		timeEditView = (EditText) findViewById(R.id.void_time);


		food_radio = (RadioButton) findViewById(R.id.food_radio);
		food_radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				radioText = "food";
				itemTextView.setText("What " + radioText + " did you consume?");
				quantityTextView.setText("How much did you consume?");
			}
		});

		alcohol_radio = (RadioButton) findViewById(R.id.alcohol_radio);
		alcohol_radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				radioText = "alcohol";
				itemTextView.setText("What " + radioText + " did you consume?");
				quantityTextView.setText("How much did you consume?");
			}
		});

		caffeine_radio = (RadioButton) findViewById(R.id.caffeine_radio);
		caffeine_radio.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				radioText = "caffeine";
				itemTextView.setText("What " + radioText + " did you consume?");
				quantityTextView.setText("How much did you consume?");
			}
		});

		consumptionButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				item = itemEditView.getText().toString();
				quantity = quantityEditView.getText().toString();
				time = timeEditView.getText().toString();
				ParseObject consumption = new ParseObject(radioText);
				consumption.put("item", item);
				consumption.put("quantity", quantity);
				consumption.put("time", time);
				consumption.saveInBackground();
				Intent returnIntent = new Intent(AlcoholFood.this,
						ParseStarterProjectActivity.class);
				AlcoholFood.this.startActivity(returnIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alcohol_food, menu);
		return true;
	}

}
