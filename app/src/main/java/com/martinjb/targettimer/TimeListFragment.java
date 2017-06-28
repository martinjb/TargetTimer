package com.martinjb.targettimer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.martinjb.targettimer.TimeLab;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;


/**
 * Created by martinjb on 6/26/2017.
 */

public class TimeListFragment extends Fragment {
    private static final String TAG = "TimeListFragment";
    private RecyclerView mTimeRecyclerView;
    private TimeAdapter mAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_list, container, false);
        mTimeRecyclerView = (RecyclerView)view.findViewById(R.id.time_recycler_view);
        mTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        TimeLab timeLab = TimeLab.get(getActivity());
        List<myTimer> timers = timeLab.getTimers();
        if( mAdapter == null ) {
            mAdapter = new TimeAdapter(timers);
            mTimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTimers(timers);
            mAdapter.notifyDataSetChanged();
        }
    }

    /**-----------------------------------------------------------------
     * These methods are used in the MENU, specifically the + button to add crimes.
    -----------------------------------------------------------------*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_time_list, menu);
        Log.v(TAG, "onCreateOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.new_timer:
                Log.v(TAG, "clicked add@@@");
                myTimer timer = new myTimer();
                TimeLab.get(getActivity()).addTimer(timer);
                Intent intent = TimePagerActivity.newIntent(getActivity(), timer.getID());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**-----------------------------------------------------------------
     * TimeHolder and TimerAdapter are NESTED CLASSES set up to help our RecyclerView.
     *
     * DO NOT put anything below here to avoid confusion.
     * -----------------------------------------------------------------*/
    private class TimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private myTimer mTimer;
        private ProgressBar mProgressBar;
        private TextView mTitleTextView;
        private TextView mWorkedTextView;
        private ImageButton mPlayImageButton;
        public TimeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.time_list_item, parent, false));
            itemView.setOnClickListener(this);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            mTitleTextView = (TextView) itemView.findViewById(R.id.time_list_item_title);
            mWorkedTextView = (TextView) itemView.findViewById(R.id.time_list_item_worked);
            mPlayImageButton = (ImageButton) itemView.findViewById(R.id.time_list_play_button);
        }
        @Override
        public void onClick(View view) {
            //TODO
            //Intent intent = TimerActivity.newIntent(getActivity(), mCrime.getId());
            //startActivity(intent);
        }
        public void bind(myTimer timer){
            mTimer = timer;
            mTitleTextView.setText(mTimer.getTitle());
            mWorkedTextView.setText(mTimer.getWorked());
        }
    }

    private class TimeAdapter extends RecyclerView.Adapter <TimeHolder>{
        private List<myTimer> mTimers;

        public TimeAdapter(List<myTimer> timers)
        {
            mTimers = timers;
        }

        @Override
        public TimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(TimeHolder holder, int position) {
            myTimer timer = mTimers.get(position);
            holder.bind(timer);
        }

        @Override
        public int getItemCount() {
            return mTimers.size();
        }

        public void setTimers(List<myTimer> timers){
            mTimers = timers;
        }

    }
}
