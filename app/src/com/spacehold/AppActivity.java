package com.spacehold;

import com.spacehold.MyLocation.LocationResult;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

public class AppActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    MyLocation myLocation = new MyLocation();
    private void locationClick() {
        myLocation.getLocation(this, locationResult);
    }

    public LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(final Location location){
            //Got the location!
        }
    };
}
