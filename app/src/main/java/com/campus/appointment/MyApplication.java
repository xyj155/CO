package com.campus.appointment;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class MyApplication extends Application {
    public static MyApplication app = null;

    public static MyApplication getInstance() {
        if (app == null) {
            return new MyApplication();
        } else {
            return app;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        MobSDK.init(this);
    }
}
