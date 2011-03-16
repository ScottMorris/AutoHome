package com.morris.scott.autohome;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PVRActivity extends Activity {

	ListView recordedShowList;
	String[] shows = {"The Littlest Hobo S01E04", "Big Bang THeory S03E05"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pvr);
		
		recordedShowList = (ListView)findViewById(R.id.pvrShowList);
		recordedShowList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shows));
		
	}
	

}

/*public class TestListActivities extends ListActivity {
	String[] listItems = {"exploring", "android", 
		"list", "activities"};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setListAdapter(new ArrayAdapter(this, 
		android.R.layout.simple_list_item_1, listItems));
	}
}*/