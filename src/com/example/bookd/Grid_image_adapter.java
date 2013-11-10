package com.example.bookd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

 

 
public class Grid_image_adapter extends BaseAdapter {
	private Context context;
	private final String[] mobileValues;
 
	public Grid_image_adapter(Context context, String[] mobileValues) {
		this.context = context;
		this.mobileValues = mobileValues;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			// get layout from mobile.xml
			gridView = inflater.inflate(R.layout.grid_book, null);
 
 
			// set image based on selected text
			ImageView imageView = (ImageView) gridView
					.findViewById(R.id.grid_item_image);
 
			String mobile = mobileValues[position];
 
			if (mobile.equals("harrypotterandthesocererstone")) {
				imageView.setImageResource(R.drawable.one);
			} else if (mobile.equals("thealchemist")) {
				imageView.setImageResource(R.drawable.two);
			} else if (mobile.equals("davincicode")) {
				imageView.setImageResource(R.drawable.three);
			} else if (mobile.equals("prideandprejudice")) {
				imageView.setImageResource(R.drawable.four);
			} else if (mobile.equals("howtokillamockingbird")) {
				imageView.setImageResource(R.drawable.five);
			} else if (mobile.equals("lifeofpi")) {
				imageView.setImageResource(R.drawable.six);
			} else if (mobile.equals("waterforelephants")) {
				imageView.setImageResource(R.drawable.seven);
			} else if (mobile.equals("kiterunner")) {
				imageView.setImageResource(R.drawable.eight);
			}
			else {
				imageView.setImageResource(R.drawable.ic_launcher);
			}
 
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return mobileValues.length;
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}


