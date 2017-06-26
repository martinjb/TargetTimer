package com.martinjb.targettimer;

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

/**
 * Created by mitch on 6/26/2017.
 */

public class TimerFragment extends Fragment{
    private EditText mTitleField;
    private myTimer mTimer;
    private Button mStartButton;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
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
        mStartButton = (Button) v.findViewById( R.id.timer_start_button);
        mStartButton.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString( R.string.crime_report_subject));
                i = Intent.createChooser(i, getString( R.string.send_report));
                startActivity(i);
            }
        });
        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);

        mSuspectButton = (Button) v.findViewById( R.id.crime_suspect);
        mSuspectButton.setOnClickListener( new View.OnClickListener() {
            public void onClick( View v) {
                startActivityForResult( pickContact, REQUEST_CONTACT);
            }
        });
        return v;
    }
}
