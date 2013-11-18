package com.example.bookd;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
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
			        if(user !="" && pass != "")
			        {
			        	if(db.insertToLogin(user,pass))
			        	{
			        	Toast.makeText(getApplicationContext(), "Successful signup!!", Toast.LENGTH_SHORT).show();
			        	}
			        }
			        else
			        {
			        	Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
			        }
			}
		});
        
        login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 String user = username.getText().toString();
			     String pass = password.getText().toString();
			     int uid;
			     if((uid = db.loginAuthenticated(user,pass)) >0)
			     {
			    	 Toast.makeText(getApplicationContext(), "Successful login!!", Toast.LENGTH_SHORT).show();
			    	 //move to a new intent
			    	 Intent intent = new Intent(MainActivity.this,Tab_try.class);
			    	
			    	 intent.putExtra("userid", uid);
			    	 startActivity(intent);
			    	 
			     }
			     else
			     {
			    	 Toast.makeText(getApplicationContext(), "Please check credentials", Toast.LENGTH_SHORT).show();
			     }
				
				
			}
		});
      
        
    }

    
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        System.out.println("----main activity---onStart---");
    overridePendingTransition(R.anim.anim_in, R.anim.anim_out);     
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
