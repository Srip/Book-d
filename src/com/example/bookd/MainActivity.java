package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	databaseHelper db ;
	EditText username ,password;
	Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = new databaseHelper(getApplicationContext());
        SQLiteDatabase database = db.getWritableDatabase();
        
        //get the parameters 
        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);
        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 String user = username.getText().toString();
			        String pass = password.getText().toString();
			        if(db.insertToLogin(user,pass))
			        {
			        	Toast.makeText(getApplicationContext(), "Successful signup!!", Toast.LENGTH_LONG).show();
			        }
			}
		});
        
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 String user = username.getText().toString();
			     String pass = password.getText().toString();
			     
			     if(db.loginAuthenticated(user,pass))
			     {
			    	 Toast.makeText(getApplicationContext(), "Successful login!!", Toast.LENGTH_LONG).show();
			    	 //move to a new intent
			     }
			     else
			     {
			    	 Toast.makeText(getApplicationContext(), "Please check credentials", Toast.LENGTH_LONG).show();
			     }
				
				
			}
		});
      
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
