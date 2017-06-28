package com.martinjb.targettimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by martinjb on 6/26/2017.
 */

public class TimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_TIMER_ID = "com.martinjb.targettimer.timer_id";
    private ViewPager mViewPager;
    private List<myTimer> mTimers;

    public static Intent newIntent(Context packageContext, UUID timerID){
        Intent intent = new Intent(packageContext, TimePagerActivity.class);
        intent.putExtra(EXTRA_TIMER_ID, timerID);
        return intent;
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_pager);

        UUID timerID = (UUID) getIntent().getSerializableExtra(EXTRA_TIMER_ID);
        mViewPager = (ViewPager) findViewById(R.id.timer_view_pager);

        mTimers = TimeLab.get(this).getTimers();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                myTimer timer = mTimers.get(position);
                return TimerFragment.newInstance(timer.getID());
            }
            @Override
            public int getCount() {
                return mTimers.size();
            }
        });

        for (int i = 0; i < mTimers.size(); i++){
            if (mTimers.get(i).getID().equals(timerID));
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
