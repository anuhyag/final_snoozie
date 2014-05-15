package com.parse.starter;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		
		TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
		// Listening to register new account link
		registerScreen.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void loginCallback(View v) {
		final Context context = v.getContext();
		String username = ((EditText) findViewById(R.id.void_username))
				.getText().toString();
		String password = ((EditText) findViewById(R.id.void_password))
				.getText().toString();

		ParseUser.logInInBackground(username, password, new LogInCallback() {
			public void done(ParseUser user, ParseException e) {
				if (e == null && user != null) {
					Intent i = new Intent(context, ParseStarterProjectActivity.class);
					startActivity(i);
				} else if (user == null){
					Toast.makeText(context, "Username or password is incorrect.", 
							Toast.LENGTH_LONG).show();
				}
				else{					
					Toast.makeText(context, "Something went wrong. Please try again.", 
							Toast.LENGTH_LONG).show();
				}
			}

	
		});
	}

}
