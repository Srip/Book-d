package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EachBook extends Activity {
	databaseHelper db ;
	TextView bname,bauthor,bdescription;
	Button review ;
	EditText et_review;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_each_book);
		
		 db = new databaseHelper(getApplicationContext());
		 bname = (TextView)findViewById(R.id.bookname);
		 bauthor = (TextView)findViewById(R.id.author);
		 bdescription = (TextView)findViewById(R.id.description);
		 review = (Button)findViewById(R.id.addreview);
		 et_review = (EditText)findViewById(R.id.et_review);
		
		 
		
		Intent intent = getIntent();
		int pos = intent.getIntExtra("position", 0);
		int uid = intent.getIntExtra("userid", -1);
		Cursor c = db.getBookDetails(pos); 
		c.moveToFirst();
		bname.setText(c.getString(1));
		bauthor.setText(c.getString(2));
		bdescription.setText(c.getString(3));
		
		//listener for add review
		review.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = getIntent();
				int pos = intent.getIntExtra("position", 0);
				int uid = intent.getIntExtra("userid", -1);
				String review =et_review.getText().toString();
				if(db.saveReview(uid,pos,review) >0 )
					{
					 
					
					   Toast.makeText(getApplicationContext(), "Review Saved!", Toast.LENGTH_SHORT).show();
					   Intent intent1 = new Intent(EachBook.this,My_reviews.class);
					   intent1.putExtra("userid",uid);
					   startActivity(intent1);
					}
				
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.each_book, menu);
		return true;
	}

}
