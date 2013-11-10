package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EachBook extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_each_book);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.each_book, menu);
		return true;
	}

}
