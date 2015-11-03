package com.example.safetyfirst;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SignUpActivity extends Activity {
	
	Button register;
	EditText f_name,l_name,uta_email,password;
	CheckBox chkStu;
	CheckBox chkPol;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		f_name = (EditText)findViewById(R.id.editTextFirstName);
		l_name = (EditText)findViewById(R.id.editTextLastName);
		uta_email = (EditText)findViewById(R.id.editTextUtaEmailId);
		password = (EditText)findViewById(R.id.editTextSign);
		chkStu = (CheckBox)findViewById(R.id.checkBox1);
		chkPol = (CheckBox)findViewById(R.id.checkBox2);
		chkStu.setOnClickListener(checkBoxStuClicked);
		chkPol.setOnClickListener(checkBoxPolClicked);
		register = (Button) findViewById(R.id.buttonRegister);
		register.setOnClickListener(registerHandler);
	}
	View.OnClickListener checkBoxStuClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			chkPol.setChecked(false);
			chkStu.setChecked(true);
		}
	};
	View.OnClickListener checkBoxPolClicked = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			chkPol.setChecked(true);
			chkStu.setChecked(false);
		}
	};
	View.OnClickListener registerHandler = new View.OnClickListener() {
	    public void onClick(View v) {
	      // it was the 1st button
	    	System.out.println("Register Button clicked");
			String f_name_string = f_name.getText().toString();
			
			String l_name_string = l_name.getText().toString();
			
			String ut_email_string = uta_email.getText().toString();
			
			String password_string = password.getText().toString();
			
			String isChecked;
			if(chkStu.isChecked()){
				isChecked="1";
			}else{
				isChecked="0";
			}
			
			
			if(!f_name_string.isEmpty() && !l_name_string.isEmpty() && !ut_email_string.isEmpty() && !password_string.isEmpty()){
//				doSignUp(f_name_string,l_name_string,ut_email_string,password_string);
				new SignUp(SignUpActivity.this).execute(f_name_string,l_name_string,ut_email_string,password_string,isChecked);
				
			}
	    }
	    
	  };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}
	public void showSuccessfulRegistration(){
		System.out.println("golmal");
		AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
		alertDialog.setTitle("Success");
		alertDialog.setMessage("Successful Login.");
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
		    new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            dialog.dismiss();
		        }
		    });
		alertDialog.show();
	}
	public void userAlreadyExists(){
		System.out.println("mast chene");
		AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
		alertDialog.setTitle("Problem");
		alertDialog.setMessage("Email address already exists.");
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
		    new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            dialog.dismiss();
		        }
		    });
		alertDialog.show();
	}
	public void invalideEmail(){
		System.out.println("123");
		AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
		alertDialog.setTitle("Problem");
		alertDialog.setMessage("Please enter valid email address.");
		alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
		    new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		            dialog.dismiss();
		        }
		    });
		alertDialog.show();
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
}
