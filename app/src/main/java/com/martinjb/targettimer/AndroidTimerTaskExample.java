package com.martinjb.targettimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class AndroidTimerTaskExample extends Activity {

	Timer timer;
	TimerTask timerTask;
	static long sTimeElapsed = 0L;
	private static String TIMER_LENGTH = "timer_length";
	private long mTimerLength;
	private final String TAG = "androidtimerexample";
	//we are going to use a handler to be able to run in our TimerTask
	final Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTimerLength = savedInstanceState.getLong(TIMER_LENGTH);
		if(savedInstanceState == null) {
			Bundle extras = getIntent().getExtras();
			if (extras == null) {
				//Extra bundle is null
			} else {
				String method = extras.getString("methodName");
				if (method.equals("startTimer")) {
					//Call method here!
				}else if (method.equals("stoptimertask")) {

				}else if (method.equals("restartTimerTask")) {

				}
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		startTimer();
	}

	public void startTimer() {

		timer = new Timer();
		//initialize the TimerTask's job
		initializeTimerTask();
		//schedule the timer, after the first 3000ms the TimerTask will run every 1000ms
		timer.schedule(timerTask, 3000, 1000);
	}

	public void stoptimertask() {
		//stop the timer, if it's not already null
		if (timer != null) {
			Log.v(TAG, "existing timer cancelled");
			timer.cancel();
			timer = null;
		}
		Log.v(TAG, "timer was null when stoptimertask() called");
	}

	public void restartTimerTask() {
		if (timer == null) {
			Log.e(TAG, "tried to restart nonexistant timer");
			return;
		} else {
			Log.v(TAG, "tried to restart nonexistant timer");
			timer.cancel();
			timer.purge();
			sTimeElapsed = 0;
			timer.schedule(timerTask, 0, 1000);
		}
	}
	public void initializeTimerTask() {
		
		timerTask = new TimerTask() {
			public void run() {
				
				//use a handler to run a toast that shows the current timestamp
				handler.post(new Runnable() {
					public void run() {
						sTimeElapsed += 1000;
						if(sTimeElapsed >= mTimerLength)
						{
							int duration = Toast.LENGTH_SHORT;
							Toast toast = Toast.makeText(getApplicationContext(), "Timer Complete", duration);
							toast.show();
							stoptimertask();
						}



						//get the current timeStamp
						Calendar calendar = Calendar.getInstance();
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
						final String strDate = simpleDateFormat.format(calendar.getTime());
						//show the toast
						int duration = Toast.LENGTH_SHORT;	
						Toast toast = Toast.makeText(getApplicationContext(), strDate, duration);
						toast.show();
					}
				});
			}
		};
	}
}