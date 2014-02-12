package com.example.f3o;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WeekFrag extends Fragment {
	private int mainXReps[] = {0,0,0};
	private float calcPercentages[] = {0, 0, 0};
	private String percentages[] = {"%","%","%"}; 
	private int OHRM = 0, DLRM = 0, BPRM = 0, SQRM = 0;
	private View rootView;
	public WeekFrag(int id, int OH, int DL, int BP, int SQ) {
		OHRM = OH; DLRM = DL; BPRM = BP; SQRM = SQ;
		switch (id) {
		case 1:
			calcPercentages[0]= (float)0.65; calcPercentages[1]= (float)0.75; calcPercentages[2]= (float)0.85;
			percentages[0]="65%"; percentages[1]="75%"; percentages[2]="85%";
			mainXReps[0]=5; mainXReps[1]=5; mainXReps[2]=5;
			break;
		case 2:
			calcPercentages[0]= (float)0.7; calcPercentages[1]= (float)0.8; calcPercentages[2]= (float)0.9;
			percentages[0]="70%"; percentages[1]="80%"; percentages[2]="90%";
			mainXReps[0]=3; mainXReps[1]=3; mainXReps[2]=3;
			break;
		case 3:
			calcPercentages[0]= (float)0.75; calcPercentages[1]= (float)0.85; calcPercentages[2]= (float)0.95;
			percentages[0]="75%"; percentages[1]="85%"; percentages[2]="95%";
			mainXReps[0]=5; mainXReps[1]=3; mainXReps[2]=1;
			break;
		case 4:
			calcPercentages[0]= (float)0.4; calcPercentages[1]= (float)0.5; calcPercentages[2]= (float)0.6;
			percentages[0]="40%"; percentages[1]="50%"; percentages[2]="60%";
			mainXReps[0]=5; mainXReps[1]=5; mainXReps[2]=5;			
			break;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.week_layout,
				container, false);
		((TextView) rootView.findViewById(R.id.percentOne)).setText(percentages[0]);
		((TextView) rootView.findViewById(R.id.percent1One)).setText(percentages[0]);
		((TextView) rootView.findViewById(R.id.percent2One)).setText(percentages[0]);
		((TextView) rootView.findViewById(R.id.percent3One)).setText(percentages[0]);
		((TextView) rootView.findViewById(R.id.percentTwo)).setText(percentages[1]);
		((TextView) rootView.findViewById(R.id.percent1Two)).setText(percentages[1]);
		((TextView) rootView.findViewById(R.id.percent2Two)).setText(percentages[1]);
		((TextView) rootView.findViewById(R.id.percent3Two)).setText(percentages[1]);
		((TextView) rootView.findViewById(R.id.percentThree)).setText(percentages[2]);
		((TextView) rootView.findViewById(R.id.percent1Three)).setText(percentages[2]);
		((TextView) rootView.findViewById(R.id.percent2Three)).setText(percentages[2]);
		((TextView) rootView.findViewById(R.id.percent3Three)).setText(percentages[2]);
		
		
		new calculateWeight().execute();
		return rootView;
	}
	
	public void updateMaxes(int oh, int dl, int bp, int sq) {
		OHRM = oh; DLRM = dl; BPRM = bp; SQRM = sq;
		new calculateWeight().execute();
	}
	
	private class calculateWeight extends AsyncTask<String, String, String[]> {

		@Override
		protected String[] doInBackground(String... params) {
			String[] weightReps = {"","","","","","","","","","","",""};
			int i = 0;
			for (i = 0; i<3; i++) {
				float temp = calcPercentages[i]*OHRM;
				int weight = 5*(Math.round(temp/5));
				String weightRep = String.valueOf(weight) + "x" + mainXReps[i];
				weightReps[i] = weightRep;
			}
			for (i = 3; i<6; i++) {
				float temp = calcPercentages[i-3]*DLRM;
				int weight = 5*(Math.round(temp/5));
				String weightRep = String.valueOf(weight) + "x" + mainXReps[i-3];
				weightReps[i] = weightRep;
			}
			for (i = 6; i<9; i++) {
				float temp = calcPercentages[i-6]*BPRM;
				int weight = 5*(Math.round(temp/5));
				String weightRep = String.valueOf(weight) + "x" + mainXReps[i-6];
				weightReps[i] = weightRep;
			}
			for (i = 9; i<12; i++) {
				float temp = calcPercentages[i-9]*SQRM;
				int weight = 5*(Math.round(temp/5));
				String weightRep = String.valueOf(weight) + "x" + mainXReps[i-9];
				weightReps[i] = weightRep;
			}
			
			return weightReps;
		}
		
		@Override
		protected void onPostExecute(String[] weightReps) {
			int i = 0;
			((TextView) rootView.findViewById(R.id.main1XsetOne)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main1XsetTwo)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main1XsetThree)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main2XsetOne)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main2XsetTwo)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main2XsetThree)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main3XsetOne)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main3XsetTwo)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main3XsetThree)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main4XsetOne)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main4XsetTwo)).setText(weightReps[i++]);
			((TextView) rootView.findViewById(R.id.main4XsetThree)).setText(weightReps[i]);
			//find views and setText to weights and reps
			//toast done calculating
		}
	}
}
