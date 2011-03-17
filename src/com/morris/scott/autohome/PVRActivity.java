package com.morris.scott.autohome;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
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
	
	int playStatus = 0;
	boolean channelBoxClicked = false;
	
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
					timerHandler.postDelayed(this, 500);
				};
			}
		};
		final Runnable updateMediaPostionRewind = new Runnable() {
			
			public void run() {
				int progress = mediaPosition.getProgress();
				if(!(progress >= mediaPosition.getMax())) {
					mediaPosition.setProgress(progress - 50);
					timerHandler.postDelayed(this, 500);
				}
				
			}
		};
		final Runnable updateMediaPostionFastForward = new Runnable() {
			
			public void run() {
				int progress = mediaPosition.getProgress();
				if(!(progress >= mediaPosition.getMax())) {
					mediaPosition.setProgress(progress + 50);
					timerHandler.postDelayed(this, 500);
				}
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
		buttonRecord.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (playStatus == 0){
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					timerHandler.postDelayed(updateMediaPostion, 500);
					playStatus = 10;
					mediaStatus.setTextColor(Color.RED);
					mediaStatus.setText("Recording");
				}
			}
		});
		buttonRewind = (Button)findViewById(R.id.rewindButton);
		buttonRewind.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if(playStatus != 0 || playStatus != 10){
					timerHandler.removeCallbacks(updateMediaPostion);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					timerHandler.postDelayed(updateMediaPostionRewind, 500);
					playStatus = 3;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Rewinding");
				}
			}
		});
		buttonPlayPause = (Button)findViewById(R.id.playPauseButton);
		buttonPlayPause.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				switch(playStatus){
				case 0:
				case 2:
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					timerHandler.postDelayed(updateMediaPostion, 500);
					playStatus = 1;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Playing");
					break;
				case 1:
					timerHandler.removeCallbacks(updateMediaPostion);
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					playStatus = 2;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Paused");
					break;
				case 3:
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					timerHandler.postDelayed(updateMediaPostion, 500);
					playStatus = 1;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Playing");
					break;
				case 4:
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.removeCallbacks(updateMediaPostionFastForward);
					timerHandler.postDelayed(updateMediaPostion, 500);
					playStatus = 1;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Fast Forwarding");
					break;
				}
			}
		});
		buttonFastForward = (Button)findViewById(R.id.fastForwardButton);
		buttonFastForward.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				if(playStatus != 0 || playStatus != 10){
					timerHandler.removeCallbacks(updateMediaPostion);
					timerHandler.removeCallbacks(updateMediaPostionRewind);
					timerHandler.postDelayed(updateMediaPostionFastForward, 500);
					playStatus = 4;
					mediaStatus.setTextColor(Color.BLACK);
					mediaStatus.setText("Fast Forwarding");
				}
			}
		});
		buttonStop = (Button)findViewById(R.id.stopButton);
		buttonStop.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				timerHandler.removeCallbacks(updateMediaPostion);
				timerHandler.removeCallbacks(updateMediaPostionRewind);
				timerHandler.removeCallbacks(updateMediaPostionFastForward);
				playStatus = 0;
				mediaPosition.setProgress(0);
				mediaStatus.setTextColor(Color.BLACK);
				mediaStatus.setText("Not Playing");
			}
		});
		
		/*
		 * Media Info
		 */
		mediaStatus = (TextView)findViewById(R.id.pvr_mediaStatus);
		mediaPosition = (SeekBar)findViewById(R.id.pvr_mediaPosition);
		mediaPosition.setMax(1000);
		channelNumber = (EditText)findViewById(R.id.pvrChannelEntry);
		channelNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				if(!hasFocus) {
					channelBoxClicked = false;
				} else {
					if(!channelBoxClicked){
						channelBoxClicked = true;
						channelNumber.setText("");
					}
				}
			}
		});
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