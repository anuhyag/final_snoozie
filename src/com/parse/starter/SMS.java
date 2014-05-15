package com.parse.starter;

import com.parse.ParseObject;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
// used http://www.itcuties.com/android/read-sms/ as a resource
public class SMS extends Activity {
	String message, number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		Log.d("SMS", "on create");
		getSMSData();
	}

	private void getSMSData() {
		Uri uri = Uri.parse("content://sms/inbox");
		Cursor c = getContentResolver().query(uri, null, null, null, null);

		// Read the sms data and store it in the list
		if (c.moveToFirst()) {
			for (int i = 0; i < c.getCount(); i++) {
				ParseObject messageData = new ParseObject("SMS");
				message = c.getString(c.getColumnIndexOrThrow("body")).toString();
				number = c.getString(c.getColumnIndexOrThrow("address")).toString();
				messageData.put("message", message);
				messageData.put("number", number);
				c.moveToNext();
			}
		}
		c.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sm, menu);
		return true;
	}

}
