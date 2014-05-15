package com.parse.starter;

import com.parse.ParseObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Sleep extends Activity {
	Button sleep_button;
	EditText nap_edit, nap_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep);
		sleep_button = (Button) findViewById(R.id.save_sleep_btn);
		nap_edit = (EditText) findViewById(R.id.void_nap);
		nap_time = (EditText) findViewById(R.id.void_nap_time);

		sleep_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				ParseObject sleepReading = new ParseObject("Sleep");
				sleepReading.put("type", nap_edit.getText().toString());
				sleepReading.put("time", nap_time.getText().toString());
				sleepReading.saveInBackground();
				Intent sleepIntent = new Intent(Sleep.this,
						ParseStarterProjectActivity.class);
				Sleep.this.startActivity(sleepIntent);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sleep, menu);
		return true;
	}

}
