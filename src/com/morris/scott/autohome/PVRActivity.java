package com.morris.scott.autohome;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class PVRActivity extends Activity {

	ListView recordedShowList;
	String[] shows = {"The Littlest Hobo S01E04", "Big Bang Theory S03E05"};
	
	Button buttonRecord, buttonRewind, buttonPlayPause, buttonFastForward, buttonStop;
	TextView mediaStatus;
	SeekBar mediaPosition;
	EditText channelNumber;
	
	
	Handler timerHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pvr);
		
		/*
		 * Runnables for the Handler 
		 */
		final Runnable updateMediaPostion = new Runnable() {
			
			public void run() {
				int progress = mediaPosition.getProgress();
				if(!(progress >= mediaPosition.getMax())) {
					mediaPosition.setProgress(progress + 10);
				}
				timerHandler.postDelayed(this, 500);
			}
		};
		final Runnable updateMediaPostionRewind = new Runnable() {
			
			public void run() {
				int progress = mediaPosition.getProgress();
				if(!(progress >= mediaPosition.getMax())) {
					mediaPosition.setProgress(progress - 50);
				}
				timerHandler.postDelayed(this, 500);
			}
		};
		final Runnable updateMediaPostionFastForward = new Runnable() {
			
			public void run() {
				int progress = mediaPosition.getProgress();
				if(!(progress >= mediaPosition.getMax())) {
					mediaPosition.setProgress(progress + 50);
				}
				timerHandler.postDelayed(this, 500);
			}
		};
		/*
		 * Show List
		 */
		recordedShowList = (ListView)findViewById(R.id.pvrShowList);
		recordedShowList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shows));
		
		/*
		 * Buttons
		 */
		buttonRecord = (Button)findViewById(R.id.recordButton);
		buttonRewind = (Button)findViewById(R.id.rewindButton);
		buttonPlayPause = (Button)findViewById(R.id.playPauseButton);
		buttonPlayPause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
			}
		});
		buttonFastForward = (Button)findViewById(R.id.fastForwardButton);
		buttonStop = (Button)findViewById(R.id.stopButton);
		
		/*
		 * Media Info
		 */
		mediaStatus = (TextView)findViewById(R.id.pvr_mediaStatus);
		mediaPosition = (SeekBar)findViewById(R.id.pvr_mediaPosition);
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