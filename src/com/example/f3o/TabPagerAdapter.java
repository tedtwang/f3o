package com.example.f3o;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class TabPagerAdapter extends FragmentPagerAdapter {
	private int OHRM, DLRM, BPRM, SQRM;
	private List<WeekFrag> weeks = new ArrayList<WeekFrag>();
	
	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		List<Integer> currentMaxes = dbHelper.getMaxes();
		OHRM = currentMaxes.get(0);
		DLRM = currentMaxes.get(1);
		BPRM = currentMaxes.get(2);
		SQRM = currentMaxes.get(3);
		addWeekFrags();
	}
	
	void addWeekFrags() {
		weeks.add(new WeekFrag(1,OHRM,DLRM,BPRM,SQRM));
		weeks.add(new WeekFrag(2,OHRM,DLRM,BPRM,SQRM));
		weeks.add(new WeekFrag(3,OHRM,DLRM,BPRM,SQRM));
		weeks.add(new WeekFrag(4,OHRM,DLRM,BPRM,SQRM));
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
        	weeks.add(new WeekFrag(1,OHRM,DLRM,BPRM,SQRM));
            return weeks.get(0);
        case 1:            
            return weeks.get(1);
        case 2:            
            return weeks.get(2);
        case 3:
        	return weeks.get(3);
        }
        return null;
	}

	public void updateMaxes(int oh, int dl, int bp, int sq) {
		for (int i = 0; i < 4; i++) {
			(weeks.get(i)).updateMaxes(oh, dl, bp, sq);
		}
	}
	
	@Override
	public int getCount() {
		return 4;
	}

}
