package com.parse.starter;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;

import com.parse.ParseUser;

import android.app.Application;
import android.util.Log;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "va1wU27V1s7VviOCY1PqI6oVPaZQzPeWu5i2rnk0", "d1M6zTO48t9xmsgmefOVi1qGXW8c6EtR9T7w98aC");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		Log.d("APPLICATION", "application launched");
	}

}
