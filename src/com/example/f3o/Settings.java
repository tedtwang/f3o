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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Intent info = getIntent();
		setCurrentMaxViews(info.getIntExtra("OH", 0),
				info.getIntExtra("DL", 0), info.getIntExtra("BP", 0),
				info.getIntExtra("SQ", 0));
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
	            	//grab edit text info, do checking for if one isnt filled out
	            	String newOh = ((EditText) findViewById(R.id.newOhMax)).getText().toString();
	            	String newDl = ((EditText) findViewById(R.id.newDlMax)).getText().toString();
	            	String newBp = ((EditText) findViewById(R.id.newBpMax)).getText().toString();
	            	String newSq = ((EditText) findViewById(R.id.newSqMax)).getText().toString();
	            	//check the values
	            	//dbh.updateMaxes(Integer.valueOf(newOh),Integer.valueOf(newDl),Integer.valueOf(newBp),Integer.valueOf(newSq));
	            	Intent i = new Intent(getBaseContext(), MainActivity.class);
	            	i.putExtra("OH", Integer.valueOf(newOh));
	            	i.putExtra("DL", Integer.valueOf(newDl));
	            	i.putExtra("BP", Integer.valueOf(newBp));
	            	i.putExtra("SQ", Integer.valueOf(newSq));
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
