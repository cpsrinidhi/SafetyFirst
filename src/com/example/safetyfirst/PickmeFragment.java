package com.example.safetyfirst;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.conn.HttpHostConnectException;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.HttpResponseException;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;

import com.example.safetyfirst.R.id;

import android.app.Fragment;

import android.content.DialogInterface;

import android.os.AsyncTask;

import android.os.Bundle;

import android.util.Log;

import android.view.LayoutInflater;

import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.Button;

import android.widget.EditText;

import android.widget.LinearLayout;

import android.widget.RelativeLayout;

import android.widget.Toast;

public class PickmeFragment extends Fragment implements OnClickListener {

	EditText Edittext1, Edittext2;

	Button Requestride, Cancelride;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_tab_pickme, container,
				false);

		Edittext1 = (EditText) v.findViewById(R.id.editText1);

		Edittext2 = (EditText) v.findViewById(R.id.editText2);

		Requestride = (Button) v.findViewById(R.id.Requestride);

		Cancelride = (Button) v.findViewById(R.id.Cancelride);

		Requestride.setOnClickListener(this);

		Cancelride.setOnClickListener(this);

		return v;

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.Requestride:

			String et1 = Edittext1.getText().toString();

			String et2 = Edittext2.getText().toString();

			new RequestRide().execute(et1, et2);

			break;

		case R.id.Cancelride:

			Toast.makeText(getActivity(), "Ride Cancelled", Toast.LENGTH_LONG)
					.show();
			;

			break;

		}

	}

	private class RequestRide extends AsyncTask<String, String, String> {

		HttpClient httpClient;

		HttpResponse httpResponse;

		HttpPost httpPost;

		@Override
		protected String doInBackground(String... params) {

			// Create Http request and response objects to connect to Omega

			try {

				String pl = URLEncoder.encode(params[0], "UTF-8").replace("+",
						"%20");

				String dl = URLEncoder.encode(params[1], "UTF-8").replace("+",
						"%20");

				String email = "m@mavs.uta.edu";

				httpClient = new DefaultHttpClient();

				Log.i("PickMeFragment - ", "Created httpClient");

				String toPHP = "email=" + email + "&" + "pickup=" + pl + "&"
						+ "dropoff=" + dl;

				httpPost = new HttpPost(
						"http://omega.uta.edu/~sxc3409/SafetyFirst/handleRideRequest.php?"
								+ toPHP);

				Log.i("PickmeUp - ", "Created httpPost to omega");

				httpResponse = httpClient.execute(httpPost);

				Log.i("PickMeFragment - ", "Created httpResponse");

			} catch (UnsupportedEncodingException e) {

				Log.e("PickMeFragment - ",
						"Error in UnsupportedEncodingException - "
								+ e.toString());

			} catch (IllegalArgumentException e) {

				Log.e("PickMeFragment - ",
						"Error in IllegalArgumentException - " + e.toString());

			} catch (HttpResponseException e) {

				Log.e("PickMeFragment - ", "Error in HttpResponseException - "
						+ e.toString());

			} catch (ClientProtocolException e) {

				Log.e("PickMeFragment - ",
						"Error in ClientProtocolException - " + e.toString());

			} catch (HttpHostConnectException e) {

				Log.e("PickMeFragment - ",
						"Error in HttpHostConnectException - " + e.toString());

			} catch (IOException e) {

				Log.e("PickMeFragment - ",
						"Error in IOException - " + e.toString());

			} catch (Exception e) {

				Log.e("PickMeFragment - ",
						"Error in Connection - " + e.toString());

			}

			return null;

		}

	}

}