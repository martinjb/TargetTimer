package com.martinjb.targettimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by mitch on 6/26/2017.
 */

public class TimerFragment extends Fragment{
    private static final String ARG_TIMER_ID = "timer_id";
    private EditText mTitleField;
    private myTimer mTimer;
    private Button mStartButton;
    private static final int START_TIMER = 0;

    /**
     * Use this instead of constructor.
     * @param timerID
     * @return
     */
    /**public static CrimeFragment newInstance(UUID timerID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }TODO*/

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_timer, container, false);
        mTitleField = (EditText) v.findViewById(R.id.timer_title);
        mTitleField.setText (mTimer.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // left blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTimer.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // left blank
            }
        });
        mStartButton = (Button) v.findViewById(R.id.timer_start_button);
        mStartButton.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                /** onActivityResult */
                startActivityForResult(pickContact, REQUEST_CONTACT);
                startActivityForResult(, START_TIMER);
            }
        });
        return v;
    }
    final Intent startTimer = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

    /**
     * I think this recieves intents.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == START_TIMER) {
            mTimer.startTimer();
        }
        /**
        else if (requestCode == REQUEST_CONTACT && data != null){
            Uri contactUri = data.getData();
            queryFields = new String[]{
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            Cursor c = getActivity().getContentResolver().query(contactUri, queryFields, null, null, null);
            try {
                if (c.getCount() = =0){
                    return;
                }
                c.moveToFirst();
                String suspect = c.getString(0);
                mCrime.setSuspect(suspect);
                mSuspectButton.setText(suspect);
            } finally {
                c.close();
            }*/
        else if (requestCode == STOP_TIMER){
            Uri uri = FileProvider.getUriForFile(getActivity(), "com.bignerdranch.android.criminalintent.fileprovider", mPhotoFile);
            getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            updatePhotoView();
        }
    }
}
