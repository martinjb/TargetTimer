package com.martinjb.targettimer;

import android.os.CountDownTimer;
import android.util.Log;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.UUID;

/**
 * Created by martinjb on 6/26/2017.
 */

public class myTimer extends Timer {
    private static final String TAG = "myTimer";


    private String mTitle;
    private String mWorked;
    private static long sSavedMS;
    private final Date mDate;
    private final int mMinutes;
    private final int mHours;

    UUID mID;
    CountDownTimer mCD;

    public myTimer(){
        this(UUID.randomUUID());
    }

    public myTimer(UUID id){
        Calendar calendar = Calendar.getInstance();
        mID = id;
        mDate = calendar.getTime();
        mMinutes = calendar.get(Calendar.MINUTE);
        mHours = calendar.get(Calendar.HOUR_OF_DAY);
    }

    private void buildTimer(){
        /**TODO
         * I would add something to the onTick handler to save the progress of the timer in your class (number of milliseconds left).

         In the onPause() method for the activity call cancel() on the timer.

         In the onResume() method for the activity create a new timer with the saved number of milliseconds left.
         */

        mCD = new CountDownTimer(5000, 1000) {

            public void pause(){

            }

            public void onTick(long millisUntilFinished) {
                sSavedMS += 1000;
                //TODO add the length of tick to worked. track this in MS i think is best.
                Log.v(TAG, "Mississippi");
                //then convert MS later.
                //send a broadcast?
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Log.v(TAG, "CDTimer finished");
                //send broadcast?
                //TODO Notification toast?
                //TODO add completed image
                //TODO update completed image when timer doine
                //mTextField.setText("done!");
            }
        };

    }
    public void startTimer(){
        if(mCD == null)
        {
            Log.e(TAG, "Tried startTimer() before buildTimer(), called buildTimer()");
            buildTimer();
            mCD.start();
        }
        else
        {
            Log.v(TAG, "Called startTimer()");
            mCD.start();
        }

    }

    public Date getDate() {
        return mDate;
    }


    public int getMinutes() {
        return mMinutes;
    }

    public int getHours() {
        return mHours;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getWorked() {
        return mWorked;
    }

    public void setWorked(String mWorked) {
        this.mWorked = mWorked;
    }

    public UUID getID() {
        return mID;
    }

    public void setID(UUID mID) {
        this.mID = mID;
    }

}
