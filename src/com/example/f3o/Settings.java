package com.example.f3o;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends Activity {
	private int OHRM = 0, DLRM = 0, BPRM = 0, SQRM = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Intent info = getIntent();
		OHRM = info.getIntExtra("OH", 0);
		DLRM = info.getIntExtra("DL", 0);
		BPRM = info.getIntExtra("BP", 0);
		SQRM = info.getIntExtra("SQ", 0);
		setCurrentMaxViews(OHRM,DLRM,BPRM,SQRM);
		addListenerOnSaveButton();
		addListenerOnCancelButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		getActionBar().setTitle("");
		return true;
	}
	public void setCurrentMaxViews(int oh, int dl, int bp, int sq) {
		((TextView) findViewById(R.id.ohMax)).setText("Current Max: "+String.valueOf(oh));
		((TextView) findViewById(R.id.dlMax)).setText("Current Max: "+String.valueOf(dl));
		((TextView) findViewById(R.id.bpMax)).setText("Current Max: "+String.valueOf(bp));
		((TextView) findViewById(R.id.sqMax)).setText("Current Max: "+String.valueOf(sq));
	}
	
	public void addListenerOnSaveButton() {
	    	final Button saveButton = (Button) findViewById(R.id.saveButton);
	    	saveButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	String newOH,newDL,newBP,newSQ;
	            	//grab edit text info, if one isn't filled out use old max
	            	newOH = ((EditText) findViewById(R.id.newOhMax)).getText().toString();
	            	if (newOH.equals("")) {
	            		newOH = Integer.toString(OHRM);
	            	}
	            	newDL = ((EditText) findViewById(R.id.newDlMax)).getText().toString();
	            	if (newDL.equals("")) {
	            		newDL = Integer.toString(DLRM);
	            	}
            		newBP = ((EditText) findViewById(R.id.newBpMax)).getText().toString();
	            	if (newBP.equals("")) {
	            		newBP = Integer.toString(BPRM);
	            	}
            		newSQ = ((EditText) findViewById(R.id.newSqMax)).getText().toString();
	            	if (newSQ.equals("")) {
	            		newSQ = Integer.toString(SQRM);
	            	}
	            	//check the values
	            	//dbh.updateMaxes(Integer.valueOf(newOh),Integer.valueOf(newDl),Integer.valueOf(newBp),Integer.valueOf(newSq));
	            	Intent i = new Intent(getBaseContext(), MainActivity.class);
	            	i.putExtra("OH", Integer.valueOf(newOH));
	            	i.putExtra("DL", Integer.valueOf(newDL));
	            	i.putExtra("BP", Integer.valueOf(newBP));
	            	i.putExtra("SQ", Integer.valueOf(newSQ));
	            	startActivity(i);
	            	finish();
	            }
	    	});
	}

	public void addListenerOnCancelButton() {
	    	final Button cancelButton = (Button) findViewById(R.id.cancelButton);
	    	cancelButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	Intent i = new Intent(getBaseContext(), MainActivity.class);
	            	startActivity(i);
	            	finish();
	            }
	    	});
	}
}
