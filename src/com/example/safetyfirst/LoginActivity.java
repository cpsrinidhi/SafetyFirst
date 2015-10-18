package com.example.safetyfirst;

import java.util.regex.Pattern;

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
import android.widget.Toast;

@SuppressLint("ShowToast")
public class LoginActivity extends Activity implements OnClickListener {
	Intent i;
	EditText userName, password;
	Button login, forgotPassword, signUp;
	public static String URL_LOGIN = "http://192.168.0.102:8888/android_login_api/login.php";
	public static String URL_REGISTER = "http://192.168.0.102:8888/android_login_api/register.php";

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
	private void checkLogin(String email, String password){
		if(validateEmail(email)){
//			RequestParams param = new RequestParams();
//			param.put("email", email);
//			param.put("password", password);
//			invokeLoginWS(param);
		}
	}
//	public void invokeLoginWS(RequestParams param){
//		AsyncHttpClient client = new AsyncHttpClient();
//        client.get("http://192.168.2.2:9999/useraccount/login/dologin",param ,new AsyncHttpResponseHandler(){
//        	public void onSuccess(String response){
//        		try{
//        			JSONObject obj = new JSONObject(response);
//        			if(obj["ERROR"]==true){
//        				//Pop out an dialogue with text = " Login failed"
//        			}else if(obj["ERROR"]==true){
//        				//successful login
//        				//navigate to home activity;
//        			}
//        		}catch(JSONException e){
//        			System.out.println("JSONException e:"+e);
//        		}
//        	}
//        	
//        });
//		
//	}

	private boolean validateEmail(String email) {
		final String coDomain = "mavs.uta.edu";
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ Pattern.quote(coDomain) + "$";
		return email.matches(EMAIL_PATTERN);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLogin:

			i = new Intent("com.example.safetyfirst.TABACTIONBARACTIVITY");
			System.out.println("Value of i" + i);
			startActivity(i);

			EditText uta_id_ET = (EditText) findViewById(R.id.editTextUtaID);
			String email_id_et = uta_id_ET.getText().toString();
			System.out.println(email_id_et);
			
			EditText uta_pass_ET = (EditText) findViewById(R.id.editTextPassword);
			String pass_uta_et = uta_pass_ET.getText().toString();
			System.out.println(pass_uta_et);

			if (!email_id_et.isEmpty() && !pass_uta_et.isEmpty()) {
				checkLogin(email_id_et, pass_uta_et);
			}
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
