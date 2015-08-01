package com.example.vivekprasad.criminalintent;

import java.util.UUID;
import java.util.Date;

/**
 * Created by vivekprasad on 3/15/15.
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    public Crime() {
        mId= UUID.randomUUID();
        mDate=new Date();
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String toString(){
        return mTitle;
    }


}
