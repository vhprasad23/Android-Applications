package com.example.vivekprasad.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


/**
 * Created by vivekprasad on 3/28/15.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getFragmentManager();
        Fragment fragment  =fm.findFragmentById(R.id.fragmentContainer);

        if(fragment==null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();
        }

    }
}
