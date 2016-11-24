package com.alexeymorozua.codesample;

import com.arellomobile.mvp.MvpApplication;

import timber.log.Timber;

/**
 * Created by john on 24.11.2016.
 */

public class CodeSampleApplication extends MvpApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
