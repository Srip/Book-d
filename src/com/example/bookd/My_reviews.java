package com.example.bookd;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class My_reviews extends ListActivity {
	databaseHelper db ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_my_reviews);
		Intent i = getIntent();
		int uid = i.getIntExtra("userid",0);
		
		db = new databaseHelper(getApplicationContext());
		String[] values = db.getBookReview(uid);
		
		
		
		
		/*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
		        "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
		        "Linux", "OS/2" };*/
		    // use your own layout
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.review_each, R.id.review, values);
		    setListAdapter(adapter);
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_reviews, menu);
		return true;
	}

}
