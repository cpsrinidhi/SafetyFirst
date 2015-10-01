package com.example.safetyfirst;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MavAlertsFragment extends Fragment {
	String[] values = new String[] { "India", "java", "c++", "Ad.Java",
			"Linux", "Unix" };
	ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_tab_mavalerts, container,
				false);
		perform(v);

		return v;

		// return (RelativeLayout) inflater.inflate(
		// R.layout.activity_tab_mavalerts, container, false);

	}

	public void perform(View v) {
		lv = (ListView) v.findViewById(R.id.listViewNotifications);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		lv.setAdapter(adapter);
	}

}