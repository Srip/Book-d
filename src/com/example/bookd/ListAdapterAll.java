/*package com.example.bookd;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class ListAdapterAll  extends BaseAdapter
{
    
    private Context mContext;
    Cursor cursor;
    databaseHelper db;
    public ListAdapterAll(Context context,Cursor cur) 
    {
            super();
            mContext=context;
            cursor=cur;
           
    }
       
    public int getCount() 
    {
        // return the number of records in cursor
        return cursor.getCount();
    }

    // getView method is called for each item of ListView
    public View getView(int position,  View view, ViewGroup parent) 
    {
                    // inflate the layout for each item of listView
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.review_each_all, null);
                    Log.v("in ","list adapterall");
                    Log.v("the pos:","is"+position);
                    // move the cursor to required position 
                    cursor.moveToPosition(position);
                    
                    
                    String review=cursor.getString(3);
                    String bookname=databaseHelper.names[cursor.getInt(2)-1];
                    float rating = cursor.getFloat(4);
                    //to get username query to db 
                    int u = cursor.getInt(2);
                    String uname =  db.getUsername(u);
                   
                    // get the reference of textViews
                    TextView bookn=(TextView)view.findViewById(R.id.bookname);
                    TextView t_review=(TextView)view.findViewById(R.id.review);
                    RatingBar rate = (RatingBar)view.findViewById(R.id.rating);
                    TextView user = (TextView)view.findViewById(R.id.username);
                    
                    // Set the Sender number and smsBody to respective TextViews 
                    bookn.setText(bookname);
                    t_review.setText(review);
                    rate.setRating(rating);
                    user.setText("By:"+uname);
                    
        
                    return view;
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
  
}

*/
package com.example.bookd;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class ListAdapterAll  extends BaseAdapter
{
    
    private Context mContext;
    Cursor cursor;
    databaseHelper db;
    public ListAdapterAll(Context context,Cursor cur) 
    {
            super();
            mContext=context;
            cursor=cur;
           
    }
       
    public int getCount() 
    {
        // return the number of records in cursor
        return cursor.getCount();
    }

    // getView method is called for each item of ListView
    public View getView(int position,  View view, ViewGroup parent) 
    {
                    // inflate the layout for each item of listView
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.review_each_all, null);
                    Log.v("in ","list adapter");
                    Log.v("the pos:","is"+position);
                    // move the cursor to required position 
                    cursor.moveToPosition(position);
                    
                    
                    String review=cursor.getString(3);
                    String bookname=databaseHelper.names[cursor.getInt(2)-1];
                    float rating = cursor.getFloat(4);
                    //to get username query to db 
                   
                    TextView bookn=(TextView)view.findViewById(R.id.bookname);
                    TextView t_review=(TextView)view.findViewById(R.id.review);
                    RatingBar rate = (RatingBar)view.findViewById(R.id.rating);
                   
                    
                    // Set the Sender number and smsBody to respective TextViews 
                    bookn.setText(bookname);
                    t_review.setText(review);
                    rate.setRating(rating);
                           
                    return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}

