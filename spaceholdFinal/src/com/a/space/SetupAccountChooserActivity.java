package com.a.space;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SetupAccountChooserActivity extends Activity {
	private Account mAccount;
	private Button mPickAccountButton;
	private Button mContinueButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setup_account_chooser);
		
		mPickAccountButton = (Button)findViewById(R.id.button1);
		mPickAccountButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				showAccountsChooser();
			}
		});
		mContinueButton = (Button)findViewById(R.id.button2);
		mContinueButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (mAccount == null) {
					showNoAccountChosenWarning();
					return;
				}
				
				SharedPreferences settings = getSharedPreferences(SHUtil.PREFS_NAME, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(SHUtil.ACCOUNT_NAME, mAccount.name);
				editor.commit();
				
				finish();
				startActivity(new Intent(SetupAccountChooserActivity.this, SetupSpaceChooserActivity.class));
			}
		});
	    
	    showAccountsChooser();
	}
	
	private void showNoAccountChosenWarning() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Spacehold");
		builder.setMessage("Choose an account before proceeding.");
		builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		
		builder.create().show();
	}
	
	private void showAccountsChooser() {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setTitle("Select a Google account");
	    final Account[] accounts = AccountManager.get(this).getAccountsByType("com.google");
	    final int size = accounts.length;
	    String[] names = new String[size];
	    for (int i = 0; i < size; i++) {
	        names[i] = accounts[i].name;
	    }
	    builder.setItems(names, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	          useAccount(accounts[which]);
	        }
	    });
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

	    builder.create().show();
	}
	
	private void useAccount(Account account) {
		mAccount = account;
		
		TextView accountNameField = (TextView)findViewById(R.id.textView3);
		accountNameField.setText(account.name);
	}
}
