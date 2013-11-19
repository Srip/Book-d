package com.example.bookd;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class My_reviews extends ListActivity {
	databaseHelper db ;
	ListView myreview;
	ListAdapter la;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_reviews);
		context = this;
		 myreview=(ListView)findViewById(android.R.id.list);
		
		Intent i = getIntent();
		int uid = i.getIntExtra("userid",0);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		db = new databaseHelper(getApplicationContext());
		Cursor c = db.getBookReview(uid);
		if(c == null)
		{
			Toast.makeText(getApplicationContext(), "No Reviews to be displayed!", Toast.LENGTH_LONG).show();
			
		}
		else
		{
			 // use your own layout
		   /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.review_each, R.id.review, values);
		    setListAdapter(adapter);*/
			 // Create the Adapter
            la=new ListAdapter(this,c);
            
            // Set The Adapter to ListView
            myreview.setAdapter(la);
			
			
		    
		}

		   
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_reviews, menu);
		return true;
	}
	public void onResume()
	{
		super.onResume();
	}


}
