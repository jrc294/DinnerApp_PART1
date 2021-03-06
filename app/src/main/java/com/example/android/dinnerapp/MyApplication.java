package com.example.android.dinnerapp;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.TagManager;

/**
 * Created by jonathan.cook on 11/29/2015.
 */
public class MyApplication extends Application {

    Tracker mTracker;
    TagManager mTagManager;
    ContainerHolder mContainerHolder;

    public void startTracking() {

        if (mTracker == null) {
            // Get the GoogleAnalytics instance
            GoogleAnalytics ga = GoogleAnalytics.getInstance(this);

            // Create a new tracker
            mTracker = ga.newTracker(R.xml.track_app);

            // Enable automatic reports
            ga.enableAutoActivityReports(this);

            // Set the log level to verbose
            ga.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
        }
    }

    public Tracker getTracker() {

        startTracking();
        return mTracker;
    }

    public TagManager getTagManager() {
        if (mTagManager == null) {
            mTagManager = TagManager.getInstance(this);
        }
        return mTagManager;
    }

    public void setTagManager(TagManager mTagManager) {
        this.mTagManager = mTagManager;
    }

    public ContainerHolder getContainerHolder() {
        return mContainerHolder;
    }

    public void setContainerHolder(ContainerHolder mContainerHolder) {
        this.mContainerHolder = mContainerHolder;
    }
}
