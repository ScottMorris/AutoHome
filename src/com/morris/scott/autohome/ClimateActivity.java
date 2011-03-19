package com.morris.scott.autohome;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ClimateActivity extends Activity {

	/*
	 * Widget Variables
	 */
	TextView setTempText, currentTempText;
	Button upTempButton, downTempButton;
	ImageView furnaceStatus, acStatus;
	ToggleButton furnaceEnable, acEnable;
	
	/*
	 * Other Variables
	 */
	boolean	furnaceState, acState;
	int setTemp = 20, currentTemp = 20;
	
	Handler handler = new Handler();
	String tempSuffix = "°C";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.climate);
		
		/*
		 * Runnables for the Handler 
		 */
		final Runnable resetTempPosition = new Runnable() {
			
			public void run() {
				currentTempText.setText(currentTemp+tempSuffix);
				setTempText.setText(setTemp+tempSuffix);
			};
		};
		
		setTempText = (TextView)findViewById(R.id.climate_setTemp);
		currentTempText = (TextView)findViewById(R.id.climate_currentTemp);
		currentTempText.setText(currentTemp+tempSuffix);
		
		upTempButton = (Button)findViewById(R.id.climate_upTemp);
		upTempButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				handler.removeCallbacks(resetTempPosition);
				setTempText.setText(currentTemp+tempSuffix);
				setTemp += 1;
				currentTempText.setText(setTemp+tempSuffix);
				handler.postDelayed(resetTempPosition, 2000);
			}
		});
		downTempButton = (Button)findViewById(R.id.climate_downTemp);
		downTempButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				handler.removeCallbacks(resetTempPosition);
				setTempText.setText(currentTemp+tempSuffix);
				setTemp -= 1;
				currentTempText.setText(setTemp+tempSuffix);
				handler.postDelayed(resetTempPosition, 2000);
			}
		});
		
		furnaceStatus = (ImageView)findViewById(R.id.climate_furnaceStateIcon);
		acStatus = (ImageView)findViewById(R.id.climate_acStateIcon);
		
		furnaceEnable = (ToggleButton)findViewById(R.id.climate_enableFurnace);
		acEnable = (ToggleButton)findViewById(R.id.climate_enableAC);
		
		
	}
	

}
