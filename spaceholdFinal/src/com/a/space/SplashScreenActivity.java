package com.a.space;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreenActivity extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		/** set time to splash out */
		final int welcomeScreenDelay = 1000;
		try {
			Thread.sleep(welcomeScreenDelay);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/**
			 * Called after splash times up. Do some action after splash
			 * times up. Here we moved to another main activity class
			 */
			SharedPreferences settings = getSharedPreferences(SHUtil.PREFS_NAME, 0);
			boolean hasRun = settings.getBoolean(SHUtil.HAS_RUN, false);
			if (!hasRun) {
				startActivity(new Intent(SplashScreenActivity.this, SetupWelcomeActivity.class));
			} else {
				startActivity(new Intent(SplashScreenActivity.this, MainScreenActivity.class));
			}
			finish();
		}
	}
}
