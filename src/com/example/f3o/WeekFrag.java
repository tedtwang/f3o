package com.example.f3o;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class WeekFrag extends Fragment {
	private int mainXReps[] = {0,0,0};
	private String percentages[] = {"%","%","%"}; 
	private int ORM = 0;
	public WeekFrag(int id, int rm) {
		ORM = rm;
		switch (id) {
		case 1:
			percentages[0]="65%"; percentages[1]="75%"; percentages[2]="85%";
			mainXReps[0]=5; mainXReps[1]=5; mainXReps[2]=5;
			break;
		case 2:
			percentages[0]="70%"; percentages[1]="80%"; percentages[2]="90%";
			mainXReps[0]=3; mainXReps[1]=3; mainXReps[2]=3;
			break;
		case 3:
			percentages[0]="75%"; percentages[1]="85%"; percentages[2]="95%";
			mainXReps[0]=5; mainXReps[1]=3; mainXReps[2]=1;
			break;
		case 4:
			//percentages[0]="70%"; percentages[1]="80%"; percentages[2]="90%";
			//mainXReps[0]=3; mainXReps[1]=3; mainXReps[2]=3;
			//TBD
			break;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.week_layout,
				container, false);
		new calculateWeight().execute();
		return rootView;
		
	}
	
	private class calculateWeight extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			String s = "";
			
			return s;
		}
		
		@Override
		protected void onPostExecute(String s) {
			//toast done calculating
		}
	}
}
