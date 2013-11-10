package com.example.bookd;

import java.util.ArrayList;
import java.util.List;

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
		private static final String TABLE_BOOKS = "booklist";
		private static final String TABLE_REVIEW = "reviews";

		// Contacts Table Columns names
		private static final String LOGIN_ID = "id";
		private static final String USERNAME = "username";
		private static final String PASSWORD = "password";
		
		private static final String BOOK_ID = "book_id";
		private static final String BOOK_NAME = "bookname";
		private static final String BOOK_AUTHOR = "bookauthor";
		private static final String BOOK_DESCR = "bookdescription";
		
		private static final String REVIEW_ID ="review_id";
		private static final String USER_ID_FOR = "user_id";
		private static final String BOOK_ID_FOR = "book_id";
		private static final String REVIEWS = "reviews";
		
		

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
		
		String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
				+ BOOK_ID + " INTEGER PRIMARY KEY  ," + BOOK_NAME + " TEXT,"
				+ BOOK_AUTHOR + " TEXT, " + BOOK_DESCR + " TEXT )";
		db.execSQL(CREATE_BOOKS_TABLE);
		
		String CREATE_REVIEW_TABLE ="CREATE TABLE "+TABLE_REVIEW + "("+REVIEW_ID+" INTEGER PRIMARY KEY autoincrement,"+USER_ID_FOR
				+" INTEGER, "+BOOK_ID_FOR+" INTEGER, "+REVIEWS+" TEXT, FOREIGN KEY ("+USER_ID_FOR
				+") REFERENCES "+TABLE_LOGIN+"("+LOGIN_ID+"), FOREIGN KEY ("+BOOK_ID_FOR
				+") REFERENCES "+TABLE_BOOKS+"("+BOOK_ID+") );";		
				db.execSQL(CREATE_REVIEW_TABLE);
		Log.v("database","created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEW);

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
	
	public int loginAuthenticated(String u,String p) 
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM "+TABLE_LOGIN + " WHERE "+USERNAME+ " =? AND "+PASSWORD+" =? ;";
		Cursor c = db.rawQuery(query, new String[]{u,p});
		c.moveToFirst();
		if(c.getCount()!= 0)
		 return c.getInt(0);
		else
			return -1;
	}
	
	public Cursor getBookDetails(int pos)
	{
		
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * FROM "+TABLE_BOOKS+" WHERE "+BOOK_ID+ "= "+pos+" ;";
		Cursor c = db.rawQuery(query, null);
		
		return c;
		
	}
	
	public int saveReview(int uid,int pos_book,String review)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "insert into "+TABLE_REVIEW+ "(" +USER_ID_FOR+" , "+BOOK_ID_FOR+" , "+REVIEWS
				+" ) values ("+uid+" , "+pos_book+" , \""+review+"\");";
		ContentValues values = new ContentValues();
		values.put("user_id",uid);
		values.put("book_id", pos_book);
		values.put("reviews",review);
		long row_id = db.insert(TABLE_REVIEW, null, values);
		return (int)row_id;
	}
	
	public String[] getBookReview(int uid)
	{
		int i =0;
		SQLiteDatabase db = this.getReadableDatabase();
		
		String query = "SELECT * from "+TABLE_REVIEW+" where "+USER_ID_FOR+" = "+uid+" ;";
		Cursor c = db.rawQuery(query, null);
		
		ArrayList<String> review =new ArrayList<String>();
		c.moveToFirst();
		
		do
		{
			
			String q = "SELECT * FROM "+TABLE_BOOKS+" WHERE "+BOOK_ID+" = "+c.getInt(2)+";";
			Cursor bn = db.rawQuery(q, null);
			bn.moveToFirst();
		  review.add(bn.getString(1)+"::\n"+c.getString(3));
		 
		}while(c.moveToNext());
		
		String[] review_array = new String[review.size()];
		review.toArray(review_array);
		return review_array;
	}
	


}
