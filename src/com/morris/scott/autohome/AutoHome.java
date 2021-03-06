package com.morris.scott.autohome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AutoHome extends Activity {
    /** Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }*/
	GridView lobbyGridView;
	private String[] cNames = {"Televison", "Climate", "Kitchen"}; 
	private int[] mIcons = {R.drawable.pvr_unpressed, R.drawable.climate_unpressed, R.drawable.kitchen_pan};
	private int[] mPIcons = {R.drawable.pvr_pressed, R.drawable.climate_pressed};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		lobbyGridView = (GridView)findViewById(R.id.CommonTasks);
		lobbyGridView.setAdapter(new iconAdapter(this));
		lobbyGridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				Intent chosenScreen;
				switch (position) {
				case 0:
					chosenScreen = new Intent(v.getContext(), PVRActivity.class);
					startActivityForResult(chosenScreen, 0);
					break;
				case 1:
					chosenScreen = new Intent(v.getContext(), ClimateActivity.class);
					startActivityForResult(chosenScreen, 0);
					break;
				case 2:
					chosenScreen = new Intent(v.getContext(), KitchenActivity.class);
					startActivityForResult(chosenScreen, 0);
					break;
				}
					
				/*if(position == 0) {
					chosenScreen = new Intent(v.getContext(), PVRActivity.class);
					//chosenScreen.setClassName("com.morris.scott.autohome", "com.morris.scott.autohome.PVRActivity");
					//startActivity(chosenScreen);
					//Intent myIntent = new Intent(view.getContext(), Activity2.class);
	                startActivityForResult(chosenScreen, 0);
				}*/
				
				//ImageView imageView = (ImageView)lobbyGridView.getAdapter();
				//imageView.setImageResource(mPIcons[position]);
				//lobbyGridView.
			}
			
		});
	}
	
	public class iconAdapter extends BaseAdapter {
		private Context mContext;
		
		
		public iconAdapter(Context c) {
			mContext = c;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return mIcons.length;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			/*ImageView imageView;
	        if (convertView == null) {  // if it's not recycled, initialize some attributes
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(96, 96));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageResource(mIcons[position]);

			return imageView;*/
			View v;
			if (convertView == null) {  // if it's not recycled, initialize some attributes
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.main_icons, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(cNames[position]);
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(mIcons[position]);
			} else
				v = convertView;
			return v;
		}
		

	}
}