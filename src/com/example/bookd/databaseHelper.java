package com.example.bookd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class databaseHelper extends SQLiteOpenHelper {
	// All Static variables
		// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "BookdDatabase";

		// Contacts table name
		private static final String TABLE_LOGIN = "login";

		// Contacts Table Columns names
		private static final String LOGIN_ID = "id";
		private static final String USERNAME = "username";
		private static final String PASSWORD = "password";

	public databaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//create table if doesnt exist
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ LOGIN_ID + " INTEGER PRIMARY KEY autoincrement ," + USERNAME + " TEXT,"
				+ PASSWORD + " TEXT" + ")";
		db.execSQL(CREATE_LOGIN_TABLE);
		Log.v("database","created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

				// Create tables again
				onCreate(db);
	}
	
	public boolean insertToLogin(String u,String p)
	{
		ContentValues values = new ContentValues();
		values.put(USERNAME, u);
		values.put(PASSWORD, p);
		SQLiteDatabase db = this.getWritableDatabase();
		long row_id = db.insert(TABLE_LOGIN, null, values);
		if(row_id > 0)
		return true;
		else
			return false;
	}
	
	public boolean loginAuthenticated(String u,String p)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM "+TABLE_LOGIN + " WHERE "+USERNAME+ " =? AND "+PASSWORD+" =? ;";
		Cursor c = db.rawQuery(query, new String[]{u,p});
		c.moveToFirst();
		if(c.getCount()!= 0)
		 return true;
		else
			return false;
	}
	

}
