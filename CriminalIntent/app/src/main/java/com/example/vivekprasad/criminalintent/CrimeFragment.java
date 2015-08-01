package com.example.vivekprasad.criminalintent;

//import android.app.Fragment;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vivekprasad on 3/15/15.
 */
public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID = "com.example.vivekprasad.criminalintent.crime_id";
    public static final String DIALOG_DATE="date";
    private Crime mCrime;
    private EditText mtitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private SimpleDateFormat mDateFormat = new SimpleDateFormat("LLL dd, yyyy");
    private static final int REQUEST_DATE=0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //mCrime=new Crime();
        //UUID crimeID = (UUID) getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        UUID crimeID = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_crime,parent,false);

        mtitleField = (EditText)v.findViewById(R.id.crime_title);
        mtitleField.setText(mCrime.getTitle());
        mtitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(mCrime.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton=(Button)v.findViewById(R.id.crime_date);
        updateDate();
        //mDateButton.setText(mDateFormat.format(mCrime.getDate()));
        //mDateButton.setEnabled(false);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);

            }
        });

        mSolvedCheckBox=(CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_DATE){
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }
    }

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void updateDate(){
        mDateButton.setText(mCrime.getDate().toString());
    }
}
