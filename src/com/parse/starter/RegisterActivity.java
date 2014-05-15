package com.parse.starter;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.widget.EditText;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import android.text.TextUtils;

public class RegisterActivity extends Activity {

	String TAG = "RegisterActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		// listener to go to login screen
		TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
		loginScreen.setOnClickListener(new View.OnClickListener() {

		public void onClick(View arg0) {
			// Switching to login screen
			finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void registerCallback(View v) {
		final Context context = v.getContext();

		// Get fields from the form
		String username = ((EditText) findViewById(R.id.reg_fill_username))
				.getText().toString();
		String password = ((EditText) findViewById(R.id.reg_fill_password))
				.getText().toString();
		String fullName = ((EditText) findViewById(R.id.reg_fill_fullname))
				.getText().toString();

		// Set username and password
		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);


		// Sign up user with parse
		user.signUpInBackground(new SignUpCallback() {
			public void done(ParseException e) {
				if (e == null) {
					System.out.println("registration successful");
					Toast.makeText(context, "Registration successful!", 
							Toast.LENGTH_LONG).show();					
					Log.d(TAG, "Registration successful!");
					Intent intent = new Intent(context, ParseStarterProjectActivity.class);
					startActivity(intent);
				} else {
					Log.e(TAG, "Could not register user");
					if (e.getCode() == ParseException.USERNAME_TAKEN){
						Toast.makeText(context, "Username taken. Please select a new one.", 
								Toast.LENGTH_LONG).show();
						Log.e(TAG, "Username taken");
						System.out.println("username taken");
					}
					else{
						Toast.makeText(context, "Registration failed. Please try again.", 
								Toast.LENGTH_LONG).show();
						Log.e("REGISTRATION", e.getMessage());
						System.out.println("other error");
					}
				}
			}
		});

	}

}
