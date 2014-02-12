package com.example.f3o;

import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private ViewPager vPager;
	private TabPagerAdapter mAdapter;
	private ActionBar actionBar;
	private dbHelper dbH;
	private static final String[] tabs = { "1", "2", "3", "Deload" };
	private List<Integer> currentMaxes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// try {
		Intent i = getIntent();
		int overhead = i.getIntExtra("OH", 0);
		int deadlift = i.getIntExtra("DL", 0);
		int bench = i.getIntExtra("BP", 0);
		int squat = i.getIntExtra("SQ", 0);
		// }

		vPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		dbH = new dbHelper(this);
		if (dbH.checkSize()) {// if db is empty then go in here
			if (overhead != 0 && deadlift != 0 && bench != 0 && squat != 0) {// if
																				// user
																				// is
																				// coming
																				// from
																				// settings
																				// activity
				mAdapter = new TabPagerAdapter(getSupportFragmentManager(),
						overhead, deadlift, bench, squat);
				dbH.addMaxes(overhead, deadlift, bench, squat);
			} else {// if user first opened the app
				Toast enterWeights = Toast.makeText(this, "Need to Enter 1RMs",
						Toast.LENGTH_SHORT);
				enterWeights.show();
				Intent j = new Intent(this, Settings.class);
				startActivity(j);
				finish();
			}
		} else {
			if (overhead != 0 && deadlift != 0 && bench != 0 && squat != 0) {// if
																				// user
																				// reopened
																				// app
																				// but
																				// already
																				// updated
																				// maxes
				dbH.updateMaxes(overhead, deadlift, bench, squat);
			}
			currentMaxes = dbH.getMaxes();
			mAdapter = new TabPagerAdapter(getSupportFragmentManager(),
					currentMaxes.get(0), currentMaxes.get(1),
					currentMaxes.get(2), currentMaxes.get(3));
		}

		vPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		vPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		getActionBar().setTitle("");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent i = new Intent(this, Settings.class);
			i.putExtra("OH", currentMaxes.get(0));
        	i.putExtra("DL", currentMaxes.get(1));
        	i.putExtra("BP", currentMaxes.get(2));
        	i.putExtra("SQ", currentMaxes.get(3));
			startActivity(i);
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void updateWorkout(int oh, int dl, int bp, int sq) {
		mAdapter.updateMaxes(oh, dl, bp, sq);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		vPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
