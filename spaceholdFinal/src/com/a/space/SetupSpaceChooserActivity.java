package com.a.space;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class SetupSpaceChooserActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		showSpacesChooser();
	}
	
	private void showSpacesChooser() {
		CharSequence[] items = getListOfSpaces();
		boolean[] states = new boolean[items.length];
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select spaces to join");
		builder.setMultiChoiceItems(items, states, new DialogInterface.OnMultiChoiceClickListener() {
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
	}
	
	private CharSequence[] getListOfSpaces() {
		CharSequence[] seq = new CharSequence[0];
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet("http://www.spartanjava.com"); // TODO change to our URL
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);
			
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(
							response.getEntity().getContent()
				    )
			);

			String line = null;
			List<String> tmp = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				tmp.add(line);
			}

			seq = new CharSequence[tmp.size()];
			for (int i = 0; i < tmp.size(); ++i) {
				seq[i] = tmp.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return seq;
	}
}
