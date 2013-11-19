package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class All_reviews extends ListActivity {
	databaseHelper db ;
	ListView myreview;
	ListAdapterAll la;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_reviews);
		context = this;
		 myreview=(ListView)findViewById(android.R.id.list);
		
		Intent i = getIntent();
		int uid = i.getIntExtra("userid",0);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		db = new databaseHelper(getApplicationContext());
		Cursor c = db.getAllBookReview();
		if(c == null)
		{
			Toast.makeText(getApplicationContext(), "No Reviews to be displayed!", Toast.LENGTH_LONG).show();
			
		}
		else
		{
			
            la=new ListAdapterAll(this,c);
            
            // Set The Adapter to ListView
            myreview.setAdapter(la);
			
			
		    
		}

		   
		   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_reviews, menu);
		return true;
	}
	public void onResume()
	{
		super.onResume();
	}


}

