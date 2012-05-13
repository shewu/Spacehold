package com.a.space;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreenActivity extends Activity  implements View.OnClickListener{
	WifiManager wifi;
	ListView lv;
    TextView textStatus;
    Button buttonScan;
    int size = 0;
    List<ScanResult> results;

    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;
    
    
	/** Called when the activity is first created. */
	
	private TextView t,t2,t3;
	boolean occupied = false;
	boolean first = true;
	public Integer[] mImageIds = {
	            R.drawable.icon,
	            R.drawable.icon,
	            R.drawable.icon,
	            R.drawable.icon,
	  
	    };
	Gallery gallery;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.planets_array, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spinner.setAdapter(adapter);
	    spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	    
	    
	    
	    wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }  
	    registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent) 
            {
               results = wifi.getScanResults();
               size = results.size();
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));  
	    Button getBSSID = (Button) findViewById(R.id.buttonBSSID);
	    getBSSID.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View view){
	    		//Toast.makeText(getApplicationContext(),"BSSID",Toast.LENGTH_SHORT).show();
	    		
	    		//List<ScanResult> results = wifi.getScanResults();
	    		//String bleh = Integer.toString(results.size());
	    		//Toast.makeText(getApplicationContext(),bleh,Toast.LENGTH_SHORT).show();
	    		arraylist.clear();          
	            wifi.startScan();
	            String tst = "";
	            int lvl = -200;
	            String strongest = "";
	            Toast.makeText(getApplicationContext(), "Scanning...." + size, Toast.LENGTH_SHORT).show();
	            try 
	            {
	                size = size - 1;
	                while (size >= 0) 
	                {   
	                    //HashMap<String, String> item = new HashMap<String, String>();                       
	                    //item.put(ITEM_KEY, results.get(size).SSID + "  " + results.get(size).capabilities);

	                    //arraylist.add(item);
	                	tst = tst + results.get(size).BSSID + "\n";
	                	if (results.get(size).level > lvl){
	                		lvl = results.get(size).level;
	                		strongest = results.get(size).BSSID;
	                		
	                	}
	                    size--;
	                    //adapter.notifyDataSetChanged();                 
	                } 
	                Toast.makeText(getApplicationContext(), "Strongest BSSID: " + strongest, Toast.LENGTH_SHORT).show();
	            }
	            catch (Exception e)
	            { }      
	    	}
	    });
	    
	    
	    t=new TextView(this); 
	    t2=new TextView(this); 
	    t3=new TextView(this); 
	    t=(TextView)findViewById(R.id.textView1);
	    t2=(TextView)findViewById(R.id.textView2);
	    t3=(TextView)findViewById(R.id.textView3);
	    t3.setText("Last seen 5 hours ago");
	     gallery = (Gallery) findViewById(R.id.gallery1);
	    gallery.setAdapter(new ImageAdapter(this));

	    gallery.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView parent, View v, int position, long id) {
	            //Toast.makeText(MainScreenActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            
	        }
	    });
	    
		//tv1 = (TextView) findViewById(R.id.textView3); 
		//tv1.setOnClickListener(this);
		/*Button btnOpenNewActivity = (Button) findViewById(R.id.button1); 
		btnOpenNewActivity .setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 

			Intent myIntent = new Intent(MainScreenActivity.this,MitersActivity.class); 

			MainScreenActivity.this.startActivity(myIntent); 

			} 

			}); 
	*/
		
		
		}
	catch(Exception e){
	}
		
		
	}
	
	public class ImageAdapter extends BaseAdapter {
	    int mGalleryItemBackground;
	    private Context mContext;

	  

	    public ImageAdapter(Context c) {
	        mContext = c;
	        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.HelloGallery);
	        mGalleryItemBackground = attr.getResourceId(
	                R.styleable.HelloGallery_android_galleryItemBackground, 0);
	        attr.recycle();
	    }

	    public int getCount() {
	        return mImageIds.length;
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }

	   
	    /*
	    public Bitmap getRemoteImage(String url){
	    	try{
	    URL aURL = new URL(url);
	    final URLConnection conn = aURL.openConnection();
	    conn.connect();
	    final BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
	    final Bitmap bm = BitmapFactory.decodeStream(bis);
	    bis.close();
	    return bm;
	    	}
	    	catch(Exception e){
	    		return null;
	    	}
	    }*/
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView = new ImageView(mContext);

	        //imageView.setImageResource(mImageIds[position]);
	        String ur = "";
	        switch(position){
	        case 0: ur = "http://farm3.staticflickr.com/2001/5779101174_fee1cbc6ee_m.jpg";
	        	break;
	        case 1: ur = "http://farm6.staticflickr.com/5185/5779100862_69bb2e70c5_m.jpg";
        	break;
	        case 2: ur = "http://farm6.staticflickr.com/5188/5778556195_a3eaa7a370_m.jpg";
        	break;
	        case 3: ur = "http://farm6.staticflickr.com/5030/5778555917_a85f012d5f_m.jpg";
        	break;
	        case 4: ur = "http://farm6.staticflickr.com/5302/5631113669_1554b89b63_m.jpg";
        	break;
	        	default:
	        		break;
	        };
	        
	        try{
	        Bitmap b = BitmapFactory.decodeStream((InputStream)new URL(ur).getContent());
	        
	        imageView.setImageBitmap(b);
	        //imageView.setLayoutParams(new Gallery.LayoutParams(425, 319));
	        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
	        imageView.setBackgroundResource(mGalleryItemBackground);
	        }
	        catch(Exception e){
	        	
	        }
	        return imageView;
	    }
	}
	
	public void onClick(View v){
		//Toast toast=Toast.makeText(this, "Hello toast", 2000);
	     //toast.setGravity(Gravity.TOP, -30, 50);
	     //toast.show();
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
	      //Toast.makeText(parent.getContext(), "The pl2e44ane is " +
	        //  parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	      t.setText(parent.getItemAtPosition(pos).toString());
	      if (parent.getItemAtPosition(pos).toString().equals("MITERS")){
	    	  mImageIds[0] = R.drawable.miters1;
	    	  mImageIds[1] = R.drawable.miters2;	  
	    	  mImageIds[2] = R.drawable.miters3;
	    	  mImageIds[3] = R.drawable.miters4;
	    	  if(occupied){
	    		  t2.setText("Currently Occupied");
	    		  t3.setText("Occupied for 1 hour");
	    	  }
	    	  else{
	    		  t2.setText("Not Occupied");
	    		  if (first)
	    		  {
	    		  t3.setText("Last seen 5 hours ago");
	    		  }
	    		  else
	    		  {
	    			  t3.setText("Last seen 0 hours ago");
	    		  }
	    	  }
	      }
	      else{
	    	  mImageIds[0] = R.drawable.m1;
	    	  mImageIds[1] = R.drawable.m2;
	    	  mImageIds[2] = R.drawable.m3;
	    	  mImageIds[3] = R.drawable.m4;
	 t2.setText("Currently Occupied");
	 t3.setText("Occupied for 86 hours");
	      }
	     t2.setText("");
	     t3.setText("");
          doit();
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
	}
	
	
	public void doit(){
		gallery.setAdapter(new ImageAdapter(this));
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
		   startActivity(new Intent(MainScreenActivity.this,
					MitersActivity.class));
			
	      return true;
	   case R.id.help:
		   
	      return true;
	   case R.id.Keyhold:
		      //Toast.makeText(this, "Keyhold", Toast.LENGTH_SHORT).show();
		   first = false;
		   if(occupied){
			   occupied = false;
			   //t2.setText("Currently Unoccupied");
			   
		   }
		   else{
			   occupied = true;
			   //t2.setText("Currently Occupied");
			   
		   }
		   
		      return true;

	   default:
	      return super.onOptionsItemSelected(item);
	}
	}
}