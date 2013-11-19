package com.example.bookd;

import java.sql.SQLTimeoutException;
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
		private static final String BOOK_RATING = "rating";
		public static String []names= {"Harry Potter and the Socerer's stone","The Alchemist","The Da Vinci Code",
				"Pride and Prejudice","How to kill a mocking bird","Lif of Pi","Water for elephants",
				"Kite Runner","Harry Potter and the goblet of fire","Stephen Hawking-A brief history of time",
				"Android application development for dummies","The hobbit","Angels and demons","Memoirs of Geisha",
				"The adventures of Huckleberry finn","Wuthering Heights"};
		
		

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
				+" INTEGER, "+BOOK_ID_FOR+" INTEGER, "+REVIEWS+" TEXT ,"+BOOK_RATING+
				" REAL ,FOREIGN KEY ("+USER_ID_FOR
				+") REFERENCES "+TABLE_LOGIN+"("+LOGIN_ID+"), FOREIGN KEY ("+BOOK_ID_FOR
				+") REFERENCES "+TABLE_BOOKS+"("+BOOK_ID+") );";		
				db.execSQL(CREATE_REVIEW_TABLE);
				
				
				insertIntoBooks(db);
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
	
	public int saveReview(int uid,int pos_book,String review,float rating,boolean review_given)
	{
		if(review_given)
		{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("user_id",uid);
		values.put("book_id", pos_book);
		values.put("reviews",review);
		values.put("rating", rating);
		long row_id = db.insert(TABLE_REVIEW, null, values);
		return (int)row_id;
		}
		else 
			return -1;
	}
	
	public Cursor getBookReview(int uid)
	{
		int i =0;
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * from "+TABLE_REVIEW+" where "+USER_ID_FOR+" = "+uid+" ;";
		Cursor c = db.rawQuery(query, null);
		if(c.getCount()==0)
			return null;
		
		
		return c;
	}
	
	public void insertIntoBooks(SQLiteDatabase db)
	{
		//SQLiteDatabase db = getWritableDatabase();
		Log.v("insert books","called");
		
		
		
		
		String []author ={"J K Rowling","Paulo Coelho","Dan Brown","Jane Austin","Harper Lee","Yann Martel","Sara Gruen",
				"Khaled Hosseini","J K Rowling","Stephen Hawking","Donn Felker","J R R Tolkien","Dan Brown","Arthur Golden",
				"Mark Twain","Emily Bronte"};
		
		
		String []description = {"Harry Potter has never played a sport while flying on a broomstick. He's never worn a Cloak of Invisibility, befriended a giant, or helped hatch a dragon. " +
				"All Harry knows is a miserable life with the Dursleys, his horrible aunt and uncle, and their abominable son, Dudley. " +
				"Harry's room is a tiny cupboard under the stairs, and he hasn't had a birthday party in ten years.",
				"Paulo Coelho's enchanting novel has inspired a devoted following around the world." +
				" This story, dazzling in its powerful simplicity and inspiring wisdom, is about an Andalusian shepherd boy named Santiago" +
				" who travels from his homeland in Spain to the Egyptian desert in search of a treasure buried in the Pyramids",
				"An ingenious code hidden in the works of Leonardo da Vinci. A desperate race through the cathedrals and castles of Europe." +
				" An astonishing truth concealed for centuries . . . unveiled at last.",
				"It is a truth universally acknowledged, that a single man in possession of a good fortune must be in want of a wife."+
				"So begins Pride and Prejudice, Jane Austen's witty comedy of manners--one of the most popular novels of all time--that features splendidly civilized sparring between the proud " +
				"Mr. Darcy and the prejudiced Elizabeth Bennet as they play out their spirited courtship in a series of eighteenth-century drawing-room intrigues",
				"You never really understand a person until you consider things from his point of view .. until you climb into his skin and walk around in it."+
				"Tomboy Scout Finch comes of age in a small Alabama town during a crisis in 1935. She admires her father Atticus, how he deals with issues of racism, injustice," +
				" intolerance and bigotry, his courage and his love.",
				"Life of Pi is a fantasy adventure novel by Yann Martel published in 2001. The protagonist, Piscine Molitor Pi" +
				" Patel, a Tamil boy from Pondicherry, explores issues of spirituality and practicality from an early age. He survives 227 days after a shipwreck while " +
				"stranded on a boat in the Pacific Ocean with a Bengal tiger named Richard Parker.",
				"Though he may not speak of them, the memories still dwell inside Jacob Jankowski's ninety-something-year-old mind. Memories of himself as a young man, " +
				"tossed by fate onto a rickety train that was home to the Benzini Brothers Most Spectacular Show on Earth. Memories of a world filled with freaks and clowns, with wonder and pain and anger and passion; a world with its own narrow, " +
				"irrational rules, its own way of life, and its own way of death. The world of the circus: to Jacob it was both salvation and a living hell.",
				"The Kite Runner of Khaled Hosseini's deeply moving fiction debut is an illiterate Afghan boy with an uncanny instinct for predicting exactly where a downed kite will land. Growing up in the city of Kabul in the early 1970s, Hassan was narrator Amir's " +
				"closest friend even though the loyal 11-year-old with a face like a Chinese doll was the son of Amir's father's servant and a member of Afghanistan's despised Hazara minority. But in 1975, on the day of Kabul's annual kite-fighting tournament, something unspeakable happened between the two boys",
				"The summer holidays are dragging on and Harry Potter can't wait for the start of the school year. It is his fourth year at Hogwarts School of Witchcraft and Wizardry, and there are spells to be learnt, potions to be brewed and Divination lessons (sigh) to be attended. Harry is expecting these: however," +
				" other quite unexpected events are already on the march ...",
				"A Brief History of Time attempts to explain a range of subjects in cosmology, including the Big Bang, black holes and light cones, to the nonspecialist reader. Its main goal is to give an overview of the subject but, " +
				"unusual for a popular science book, it also attempts to explain some complex mathematics. The 1996 edition of the book and subsequent editions discuss the possibility of time travel and wormholes and explore the possibility of having a" +
				" universe without a quantum singularity at the beginning of time.",
				"No matter your level of programming experience, Android Application Development For Dummies is an ideal guide for getting started with developing " +
				"applications for the Android platform",
				"In a hole in the ground there lived a hobbit. Not a nasty, dirty, wet hole, filled with the ends of worms and an oozy smell, nor yet a dry, bare, sandy hole with nothing in it to sit down on or to eat: it was a hobbit-hole, and that means comfort."+
				"Written for J.R.R. Tolkien’s own children, The Hobbit met with instant critical acclaim when it was first published in 1937.",
				"When world-renowned Harvard symbologist Robert Langdon is summoned to a Swiss research facility to analyze a mysterious symbol—seared into the chest of a murdered physicist—he discovers evidence of the unimaginable: " +
				"the resurgence of an ancient secret brotherhood known as the Illuminati...the most powerful underground organization ever to walk the earth",
				"In this literary tour de force, novelist Arthur Golden enters a remote and shimmeringly exotic world. For the protagonist of this peerlessly observant first novel is Sayuri, one of Japan's most celebrated geisha, a woman who is both performer " +
				"and courtesan, slave and goddess.",
				"Of all the contenders for the title of The Great American Novel, none has a better claim than The Adventures of Huckleberry Finn. Intended at first as a simple story of a boy's adventures in the Mississippi Valley - " +
				"a sequel to The Adventures of Tom Sawyer - the book grew and matured under Twain's hand into a work of immeasurable richness and complexity. More than a century after its publication, the critical debate over the symbolic significance of Huck's and Jim's voyage is still fresh, and it remains a major work that " +
				"can be enjoyed at many levels: as an incomparable adventure story and as a classic of American humor.",
				"For the Fourth Edition, the editor collated the 1847 text with the two modern texts (Norton s William J. Sale collation and the Clarendon), and found a great number of variants, including accidentals. This discovery led to changes in the body of the Norton Critical Edition text that are explained in the preface." +
				" New to Backgrounds and Contexts are additional letters, a compositional chronology, related prose, and reviews of the 1847 text. Criticism collects five important assessments of Wuthering Heights, three of them new to the Fourth Edition, including Lin Haire-Sargeant s essay on film adaptations of the novel."
		};
		
		for(int i =0;i<16;i++)
		{
			String query = "insert into "+TABLE_BOOKS+" ("+BOOK_ID+" , "+BOOK_NAME+" , "+BOOK_AUTHOR+" , "+BOOK_DESCR+
					") values( "+i+1+" , \""+names[i]+"\" , \""+author[i]+"\" , \""+description[i]+"\" );";
			ContentValues values = new ContentValues();
			values.put("book_id", i+1);
			values.put("bookname", names[i]);
			values.put("bookauthor", author[i]);
			values.put("bookdescription", description[i]);
			db.insert(TABLE_BOOKS, null, values);
			
		}
		
	}
	
	public Cursor getAllBookReview()
	{
		int i =0;
		SQLiteDatabase db = this.getReadableDatabase();
		String query = "SELECT * from "+TABLE_REVIEW+" ;";
		Cursor c = db.rawQuery(query, null);
		if(c.getCount()==0)
			return null;
		
		
		return c;
	}
	
	

}
