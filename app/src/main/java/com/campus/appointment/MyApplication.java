package com.campus.appointment;

import android.support.multidex.MultiDexApplication;

import com.mob.MobSDK;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class MyApplication extends MultiDexApplication {
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
        JMessageClient.setDebugMode(true);
        JMessageClient.init(getApplicationContext());
        JPushInterface.setDebugMode(true);
        JMessageClient.setNotificationMode(JMessageClient.NOTI_MODE_DEFAULT);
    }
}
