package com.example.bookd;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class My_reviews extends ListActivity {
	databaseHelper db ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_my_reviews);
		Intent i = getIntent();
		int uid = i.getIntExtra("userid",0);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		db = new databaseHelper(getApplicationContext());
		String[] values = db.getBookReview(uid);
		if(values == null)
		{
			Toast.makeText(getApplicationContext(), "No Reviews to be displayed!", Toast.LENGTH_LONG).show();
			
		}
		else
		{
			 // use your own layout
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        R.layout.review_each, R.id.review, values);
		    setListAdapter(adapter);
		    
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
