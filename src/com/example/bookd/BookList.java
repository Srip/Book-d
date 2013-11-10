package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;
import android.widget.Toast;

public class BookList extends Activity {
	
	GridView gridView;
	 
	static final String[] BOOKS_NAMES = new String[] { 
		"harrypotterandthesocererstone", "thealchemist","davincicode", "prideandprejudice","howtokillamockingbird"
		,"lifeofpi" ,"waterforelephants","kiterunner"};
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_list);
		
		gridView = (GridView) findViewById(R.id.booklist);
		 
		gridView.setAdapter(new Grid_image_adapter(this, BOOKS_NAMES));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book_list, menu);
		return true;
	}

}




