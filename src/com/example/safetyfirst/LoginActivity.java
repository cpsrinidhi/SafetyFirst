package com.example.safetyfirst;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	Intent i;
	EditText userName, password;
	Button login, forgotPassword, signUp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		userName = (EditText) findViewById(R.id.editTextUtaID);
		password = (EditText) findViewById(R.id.editTextPassword);

		login = (Button) findViewById(R.id.buttonLogin);
		forgotPassword = (Button) findViewById(R.id.buttonForgotPassword);
		signUp = (Button) findViewById(R.id.buttonSignUp);

		login.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressLint("ShowToast")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLogin:
			i = new Intent("com.example.safetyfirst.TABACTIONBARACTIVITY");
			startActivity(i);
			
			finish();
			break;

		case R.id.buttonForgotPassword:
			i = new Intent("com.example.safetyfirst.FORGOTPASSWORDACTIVITY");
			startActivity(i);
			
			finish();
			break;

		case R.id.buttonSignUp:
			Toast.makeText(getApplicationContext(), "Good job signing up",
					Toast.LENGTH_LONG);
			i = new Intent("com.example.safetyfirst.SIGNUPACTIVITY");
			startActivity(i);
			finish();
			break;
		}

	}
}
