package com.example.bookd;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;



public class Tab_try extends TabActivity 
{
            /** Called when the activity is first created. */
            @Override
            public void onCreate(Bundle savedInstanceState)
            {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.trytabs);
                    
                    Intent i = getIntent();
                    int uid = i.getIntExtra("userid", 0);
                   

                    // create the TabHost that will contain the Tabs
                    TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


                    TabSpec tab1 = tabHost.newTabSpec("Bookstab");
                    TabSpec tab2 = tabHost.newTabSpec("My reviews");
                    TabSpec tab3 = tabHost.newTabSpec("All reviews");

                   // Set the Tab name and Activity
                   // that will be opened when particular Tab will be selected
                    tab1.setIndicator("Books");
                    Intent book_intent = new Intent(this,BookList.class);
                    book_intent.putExtra("userid", uid);
                    tab1.setContent(book_intent);
                    
                    
                    Intent review_intent = new Intent(this,My_reviews.class);
                    review_intent.putExtra("userid", uid);
                    tab2.setIndicator("My Reviews");
                    tab2.setContent(review_intent);
                    
                    Intent all_review_intent = new Intent(this,All_reviews.class);
                    review_intent.putExtra("userid", uid);
                    tab3.setIndicator("All Reviews");
                    tab3.setContent(all_review_intent);

                   
                    
                    /** Add the tabs  to the TabHost to display. */
                    tabHost.addTab(tab1);
                    tabHost.addTab(tab2);
                    tabHost.addTab(tab3);
                   

            }
} 