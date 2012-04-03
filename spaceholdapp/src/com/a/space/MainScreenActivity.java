package com.a.space;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainScreenActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Button btnOpenNewActivity = (Button) findViewById(R.id.button1); 
		btnOpenNewActivity .setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 

			Intent myIntent = new Intent(MainScreenActivity.this,MitersActivity.class); 

			MainScreenActivity.this.startActivity(myIntent); 

			} 

			}); }
	catch(Exception e){
	}
	}
	
	
	// Inflate the menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.options, menu);
	   return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   switch (item.getItemId()) {
	   case R.id.add:
	      Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
	      return true;
	   case R.id.help:
	      Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
	      return true;

	   default:
	      return super.onOptionsItemSelected(item);
	}
	}
}