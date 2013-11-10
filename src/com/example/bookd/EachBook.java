package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.TextureView;
import android.widget.TextView;

public class EachBook extends Activity {
	databaseHelper db ;
	TextView bname,bauthor,bdescription;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_each_book);
		
		 db = new databaseHelper(getApplicationContext());
		 bname = (TextView)findViewById(R.id.bookname);
		 bauthor = (TextView)findViewById(R.id.author);
		 bdescription = (TextView)findViewById(R.id.description);
		 
		 
		
		Intent intent = getIntent();
		int pos = intent.getIntExtra("position", 0);
		Cursor c = db.getBookDetails(pos); 
		c.moveToFirst();
		bname.setText(c.getString(1));
		bauthor.setText(c.getString(2));
		bdescription.setText(c.getString(3));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.each_book, menu);
		return true;
	}

}
