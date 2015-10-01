package com.example.safetyfirst;

import java.util.Locale;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabActionBarActivity extends Activity {
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        ActionBar actionBar = getActionBar();
 
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        String SOS = getResources().getString(R.string.sos);
        Tab tab = actionBar.newTab();
        tab.setText("SOS");
        TabListener<SOSFragment> tl = new TabListener<SOSFragment>(this,
                SOS, SOSFragment.class);
        tab.setTabListener(tl);
        actionBar.addTab(tab);
 
        String pickMe = getResources().getString(R.string.pickMe);
        tab = actionBar.newTab();
        tab.setText("Pick Me");
        TabListener<PickmeFragment> tl2 = new TabListener<PickmeFragment>(this,
                pickMe, PickmeFragment.class);
        tab.setTabListener(tl2);
        actionBar.addTab(tab);
        
        String MavAlerts = getResources().getString(R.string.mavAlerts);
        tab = actionBar.newTab();
        tab.setText("Mav Alerts");
        TabListener<MavAlertsFragment> tl3 = new TabListener<MavAlertsFragment>(this,
                MavAlerts, MavAlertsFragment.class);
        tab.setTabListener(tl3);
        actionBar.addTab(tab);
 
    }
 
    private class TabListener<T extends Fragment> implements
            ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
 
        /**
         * Constructor used each time a new tab is created.
         * 
         * @param activity
         *            The host Activity, used to instantiate the fragment
         * @param tag
         *            The identifier tag for the fragment
         * @param clz
         *            The fragment's Class, used to instantiate the fragment
         */
        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }
 
        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            // Check if the fragment is already initialized
            if (mFragment == null) {
                // If not, instantiate and add it to the activity
                mFragment = Fragment.instantiate(mActivity, mClass.getName());
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                // If it exists, simply attach it in order to show it
                ft.attach(mFragment);
            }
        }
 
        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                // Detach the fragment, because another one is being attached
                ft.detach(mFragment);
            }
        }
 
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            // User selected the already selected tab. Usually do nothing.
        }
    }
 
}