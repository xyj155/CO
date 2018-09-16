package com.campus.appointment;

import android.os.Environment;
import android.support.multidex.MultiDexApplication;

import com.mob.MobSDK;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

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
        Bugly.init(getApplicationContext(), "7523ab59d4", false);
        Beta.autoInit = true;
        Beta.autoCheckUpgrade = true;
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        JMessageClient.setDebugMode(true);
        JMessageClient.init(getApplicationContext());
        JPushInterface.setDebugMode(true);
        JMessageClient.setNotificationMode(JMessageClient.NOTI_MODE_DEFAULT);
    }
}
