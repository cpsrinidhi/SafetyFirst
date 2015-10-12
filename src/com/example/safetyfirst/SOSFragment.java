package com.example.safetyfirst;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SOSFragment extends Fragment implements OnClickListener {

	private static final int PICK_CONTACT = 1;
	ImageButton contact1, contact2, contact3;
	TextView textViewContact1, textViewContact2, textViewContact3;
	Button sendSos, stopSos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_tab_sos, container, false);

		contact1 = (ImageButton) v.findViewById(R.id.imageButtonContact1);
		contact2 = (ImageButton) v.findViewById(R.id.imageButtonContact2);
		contact3 = (ImageButton) v.findViewById(R.id.imageButtonContact3);
		textViewContact1 = (TextView) v.findViewById(R.id.textViewContact1);
		textViewContact2 = (TextView) v.findViewById(R.id.textViewContact2);
		textViewContact3 = (TextView) v.findViewById(R.id.textViewContact3);
		sendSos = (Button) v.findViewById(R.id.buttonSOS);
		stopSos = (Button) v.findViewById(R.id.buttonStopSos);

		contact1.setOnClickListener(this);
		contact2.setOnClickListener(this);
		contact3.setOnClickListener(this);
		return v;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageButtonContact1:
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
			startActivityForResult(intent, PICK_CONTACT);

			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		try {

			if (requestCode == PICK_CONTACT) {
				Cursor cursor = getActivity().getContentResolver().query(
						intent.getData(), null, null, null, null);
				cursor.moveToNext();
				String contactId = cursor.getString(cursor
						.getColumnIndex(ContactsContract.Contacts._ID));
				String name = cursor
						.getString(cursor
								.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
				String phone = cursor
						.getString(cursor
								.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				String phoneNumber = "test";

				if (phone.equalsIgnoreCase("1"))
					phone = "true";
				else
					phone = "false";

				if (Boolean.parseBoolean(phone)) {
					Cursor phones = getActivity().getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactId, null, null);
					while (phones.moveToNext()) {
						phoneNumber = phones
								.getString(phones
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					}
					phones.close();
				}
				Toast.makeText(getActivity(),
						"You selected Contact name " + name, Toast.LENGTH_LONG)
						.show();
				// if (textViewContact1.getText().length() != 0) {
				// textViewContact1.setText(textViewContact1.getText()
				// .toString() + "," + phoneNumber);
				// } else {
				// textViewContact1.setText(phoneNumber);
				// }
				textViewContact1.setText(name + "\n" + phoneNumber);

			}
		} catch (Exception e) {
			Toast.makeText(getActivity(), "F**c this shit!", Toast.LENGTH_LONG)
					.show();
			Log.e("contact list", e.getMessage());
		}
	}
}