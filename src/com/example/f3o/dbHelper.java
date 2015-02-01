package com.example.f3o;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "f3o";
	private final static String TABLE_NAME = "repmax",
								id_col = "id",
								oh_col = "overhead",
								dl_col = "deadlift",
								bp_col = "benchpress",
								sq_col = "squat";
	private final static int DATABASE_VERSION = 1;
	private String CREATE_DATABASE;
	private static SQLiteDatabase db;
	
	public dbHelper(Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
		CREATE_DATABASE = "create table if not exists " 
				+ TABLE_NAME + " ( " + 
				id_col + " INTEGER PRIMARY KEY, " +
				oh_col + " INTEGER not null, " + 
				dl_col + " INTEGER not null, " +
				bp_col + " INTEGER not null, " +
				sq_col + " INTEGER not null);";
		db = getWritableDatabase();
		onCreate(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_DATABASE);
		//updateMaxes(300,300,300,300);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//eh
	}
	
	public static List<Integer> getMaxes() {
		List<Integer> maxes = new ArrayList<Integer>();
		String selectQuery = "SELECT  * FROM " + TABLE_NAME;
		 
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    cursor.moveToFirst();
	    maxes.add(cursor.getInt(1));
	    maxes.add(cursor.getInt(2));
	    maxes.add(cursor.getInt(3));
	    maxes.add(cursor.getInt(4));
	      
	    return maxes;
	}
	
	public boolean checkSize() {
		try {
			List<Integer> elements = getMaxes();
		}
		catch (CursorIndexOutOfBoundsException e) {
			return true;
		}
		return false;
	}
	
	public void addMaxes(int oh, int dl, int bp, int sq) {	 
	    ContentValues values = new ContentValues();
	    values.put(id_col, 999);
	    values.put(oh_col, oh);
	    values.put(dl_col, dl); 
	    values.put(bp_col, bp);
	    values.put(sq_col, sq);

	    db.insert(TABLE_NAME, null, values);
	}
	
	public void updateMaxes(int oh, int dl, int bp, int sq) {
		String where = id_col + "=?";
		String whereArgs[] = {"999"};
		ContentValues args = new ContentValues();
		args.put(oh_col, oh);
		args.put(dl_col, dl);
		args.put(bp_col, bp);
		args.put(sq_col, sq);
		db.update(TABLE_NAME, args, where, whereArgs);
	}
	
	public void destroyTable() {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	}
}
