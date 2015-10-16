package com.example.safetyfirst;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteOpenHelper;

public class SOSFragment extends Fragment implements OnClickListener {
	SQLiteDatabase myDB;
	private static final int PICK_CONTACT = 1;
	ImageButton contact1, contact2, contact3;
	TextView textViewContact1, textViewContact2, textViewContact3;
	Button sendSos, stopSos;
	Intent intent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_tab_sos, container, false);

		// myDB = getActivity().openOrCreateDatabase("safetyfirst",
		// android.content.Context.MODE_PRIVATE, null);
		// DBAccess.createDB(myDB);

		contact1 = (ImageButton) v.findViewById(R.id.imageButtonContact1);
		contact2 = (ImageButton) v.findViewById(R.id.imageButtonContact2);
		contact3 = (ImageButton) v.findViewById(R.id.imageButtonContact3);
		textViewContact1 = (TextView) v.findViewById(R.id.textViewContact1);
		textViewContact2 = (TextView) v.findViewById(R.id.textViewContact2);
		textViewContact3 = (TextView) v.findViewById(R.id.textViewContact3);
		sendSos = (Button) v.findViewById(R.id.buttonSOS);
		stopSos = (Button) v.findViewById(R.id.buttonStopSos);
		stopSos.setEnabled(true);
		contact1.setOnClickListener(this);
		contact2.setOnClickListener(this);
		contact3.setOnClickListener(this);

		try {
			ArrayList<String> dbData = DBAccess.retrieveDB(myDB, null);

			if (dbData.size() == 1) {
				String contactName = dbData.get(0).split(";")[0];
				String contactPhone = dbData.get(0).split(";")[1];
				textViewContact1.setText(contactName + "\n" + contactPhone);
			}
		} catch (Exception e) {
			Log.e("SOS", e.getMessage());
		}

		return v;

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.imageButtonContact1:
			intent = new Intent(getActivity(), ContactActivity.class);
			intent.putExtra("contactNum", "1");
			startActivity(intent);

			break;

		case R.id.imageButtonContact2:
			intent = new Intent(getActivity(), ContactActivity.class);
			intent.putExtra("contactNum", "2");
			startActivity(intent);

			break;

		case R.id.imageButtonContact3:
			intent = new Intent(getActivity(), ContactActivity.class);
			intent.putExtra("contactNum", "3");
			startActivity(intent);

			break;

		case R.id.buttonSOS:
			sendSos.setEnabled(false);
			stopSos.setEnabled(true);

			break;

		}

	}
}