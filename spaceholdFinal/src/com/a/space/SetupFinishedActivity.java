package com.a.space;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SetupFinishedActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup_finished_activity);		

		SharedPreferences settings = getSharedPreferences(SHUtil.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(SHUtil.HAS_RUN, true);
		editor.commit();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			startActivity(new Intent(SetupFinishedActivity.this, MainScreenActivity.class));
		}
	}
}
