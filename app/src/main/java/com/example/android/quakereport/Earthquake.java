package com.example.android.quakereport;

import static com.example.android.quakereport.R.id.date;

public class Earthquake{

    private double mMagnitude;

    private String mLocation;

    private long mTimeInMilliseconds;

    private String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public double getMagnitude() {return mMagnitude;}

    public String getLocation() {return mLocation;}

    public long getTimeInMilliseconds() {return  mTimeInMilliseconds;}

    public String getUrl() {return mUrl;}
}