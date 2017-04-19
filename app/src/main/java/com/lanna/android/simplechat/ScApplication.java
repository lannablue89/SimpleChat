package com.lanna.android.simplechat;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lanna on 4/19/17.
 */

public class ScApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
