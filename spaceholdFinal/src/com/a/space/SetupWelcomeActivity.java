package com.a.space;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SetupWelcomeActivity extends Activity {
	private Button mNextButton;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup_welcome_activity);
		
		mNextButton = (Button)findViewById(R.id.button1);
		assert mNextButton != null;
		mNextButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
				startActivity(new Intent(arg0.getContext(), SetupAccountChooserActivity.class));
			}
		});
	}
}
