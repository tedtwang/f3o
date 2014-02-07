package com.example.f3o;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
            return new WeekFrag(1,145,345,235,295);
        case 1:
            return new WeekFrag(2,145,345,235,295);
        case 2:
            return new WeekFrag(3,145,345,235,295);
        case 3:
        	return new WeekFrag(4,145,345,235,295);
        }
        return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

}
