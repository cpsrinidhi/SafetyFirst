package com.example.safetyfirst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.NullCipher;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MavAlertsFragment extends Fragment {

	HttpClient httpClient;
	HttpPost httppost;
	HttpResponse response;
	HttpEntity entity;
	String status, result, uname, pasw, resp;
	InputStream isr;
	JSONArray jArray;
	TextView txt_Error;
	InputStream res = null;
	JSONArray jsonArray;

	HashMap<String, String> map;
	ArrayList<HashMap<String, String>> arrayList;

	String id, time_stamp, subject, notification;
	private static final String ID = "u_id";
	private static final String TIME_STAMP = "time_stamp";
	private static final String SUBJECT = "subject";
	private static final String NOTIFICATION = "notification";

	String[] values = new String[] { "India", "java", "c++", "Ad.Java",
			"Linux", "Unix", "India", "java", "c++", "Ad.Java", "Linux",
			"Unix", "India", "java", "c++", "Ad.Java", "Linux", "Unix",
			"India", "java", "c++", "Ad.Java", "Linux", "Unix" };
	ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_tab_mavalerts, container,
				false);

		UserValidation validates = new UserValidation();
		validates.execute(uname, pasw);

		perform(v);
		return v;

		// return (RelativeLayout) inflater.inflate(
		// R.layout.activity_tab_mavalerts, container, false);

	}

	private class UserValidation extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			JSONArray jsonArray = null;
			String username = params[0];
			String pasword = params[1];
			// TODO Auto-generated method stub
			try {

				httpClient = new DefaultHttpClient();
				/*
				 * httppost = new HttpPost(
				 * "http://192.168.0.13/verify_password_local.php?" + params);
				 */
				httppost = new HttpPost(
						"http://omega.uta.edu/~sxc3409/postNotification.php?");
				// httppost = new
				// HttpPost("http://omega.uta.edu/~sxk7162/db_mysql_o.php?");
				System.out.println("httpPost is done");
				response = httpClient.execute(httppost);
				System.out.println(response);
				entity = response.getEntity();
				if (entity != null) {
					isr = entity.getContent();
					System.out.println("byte - " + isr.available());
				}
			} catch (UnsupportedEncodingException e) {
				Log.e("log_tag", " Error in UnsupportedEncodingException - "
						+ e.toString());
			} catch (ClientProtocolException e) {
				Log.e("log_tag",
						" Error in ClientProtocolException - " + e.toString());
			} catch (IOException e) {
				Log.e("log_tag", " Error in IOException - " + e.toString());
			} catch (Exception e) {
				Log.e("log_tag", " Error in Connection" + e.toString());
			}

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(isr, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				isr.close();

				result = sb.toString();
				System.out.println("result from ISR : " + result);
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}

			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			arrayList = new ArrayList<HashMap<String, String>>();

			super.onPostExecute(result);
			System.out.println("Printing the result " + result);

			try {
				jsonArray = new JSONArray(result);
			} catch (JSONException | NullPointerException e) {
				Log.e("log_tag", "Error parsing data " + e.toString());
			}

			try {
				for (int i = 0; i < jsonArray.length(); i++) {
					System.out.println("inside for");
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					id = jsonObject.getString("ID");
					System.out.println("ID=" + id);
					time_stamp = jsonObject.getString("time_stamp");
					System.out.println("time=" + time_stamp);
					subject = jsonObject.getString("subject");
					System.out.println("sub=" + subject);
					notification = jsonObject.getString("notification");
					System.out.println("notif=" + notification);
					map = new HashMap<String, String>();

					map.put("subject", subject);
					map.put("timestamp", time_stamp);
					for (String key : map.keySet()) {
						System.out.println(key + " " + map.get(key));
					}

					for (HashMap.Entry<String, String> entry : map.entrySet()) {
						String key = entry.getKey().toString();
						String value = entry.getValue();
						System.out.println("key, " + key + " value " + value);
					}

					arrayList.add(map);
				}
			} catch (JSONException e) {
				Log.e("log_tag", "JSONException Error parsing data in object "
						+ e.toString());
			} catch (NullPointerException e) {
				Log.e("log_tag",
						"NullPointerException Error parsing data in object "
								+ e.toString());
			}

		}

	}

	public void perform(View v) {
		lv = (ListView) v.findViewById(R.id.listViewNotifications);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		lv.setAdapter(adapter);
	}
}