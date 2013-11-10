package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.TextureView;
import android.view.View;

public class BookList extends Activity {
	
	GridView gridView;
	 
	static final String[] BOOKS_NAMES = new String[] { 
		"harrypotterandthesocererstone", "thealchemist","davincicode", "prideandprejudice","howtokillamockingbird"
		,"lifeofpi" ,"waterforelephants","kiterunner"};
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		Intent in = getIntent();
		final int uid =in.getIntExtra("userid", -1);
		
		gridView = (GridView) findViewById(R.id.booklist);
		 
		gridView.setAdapter(new Grid_image_adapter(this, BOOKS_NAMES));
		
		 gridView.setOnItemClickListener(new OnItemClickListener() {
		        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		        	Toast.makeText(getApplicationContext(), "position is,"+position, Toast.LENGTH_LONG).show();
		        	Intent intent = new Intent(BookList.this,EachBook.class);
		        	intent.putExtra("position", position+1);
		        	intent.putExtra("userid", uid);
		        	startActivity(intent);
		            
		        }
		    });
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list, menu);
		return true;
	}

}




