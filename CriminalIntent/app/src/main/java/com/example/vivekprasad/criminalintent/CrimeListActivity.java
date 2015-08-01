package com.example.vivekprasad.criminalintent;

import android.app.Fragment;

/**
 * Created by vivekprasad on 3/28/15.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
