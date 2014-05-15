package com.parse.starter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Phone extends Activity {

	Button call_button;
	Button screen_button;
	Button sms_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone);
		addListenersOnButtons();
		Log.d("PHONE", "on create");

	}

	private void addListenersOnButtons() {
		call_button = (Button) findViewById(R.id.call_button);
		call_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent callIntent = new Intent(Phone.this, Call.class);
				Phone.this.startActivity(callIntent);
			}
			
		});
		
		screen_button = (Button) findViewById(R.id.screen_button);
		screen_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent screenIntent = new Intent(Phone.this, Screen.class);
				Phone.this.startActivity(screenIntent);
			}
			
		});
		
		sms_button = (Button) findViewById(R.id.sms_button);
		sms_button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent smsIntent = new Intent(Phone.this, SMS.class);
				Phone.this.startActivity(smsIntent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phone, menu);
		return true;
	}

}
