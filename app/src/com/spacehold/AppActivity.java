package com.spacehold;

import com.spacehold.MyLocation.LocationResult;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AppActivity extends Activity {
    /** Called when the activity is first created. */
	TextView location;
	String locationText;
	MyLocation myLocation = new MyLocation();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		location=(TextView)findViewById(R.id.locationText);
	}
    
    private void locationClick() {
        myLocation.getLocation(this, locationResult);
    }

    public void showNewLocation(){
		locationClick();	
		location.setText(locationText);
	}
    public LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(final Location location){
        	locationText = location.getLatitude() + " - " + location.getLongitude();
        }
    };
}
