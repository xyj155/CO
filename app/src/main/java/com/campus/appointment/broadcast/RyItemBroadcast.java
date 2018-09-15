package com.campus.appointment.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;


/**
 * Created by Administrator on 2018/9/13/013.
 */

public class RyItemBroadcast extends BroadcastReceiver {
    private IReceive recever;



    public RyItemBroadcast() {
    }

    public RyItemBroadcast(IReceive recever) {
        this.recever = recever;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "onReceive2: ");
        if (action.equals("com.campus.appointment.broadcast.RY_BROADCAST")) {
            recever.onReceive(intent.getIntExtra("item", 2));
            Log.i(TAG, "onReceive: " + intent.getIntExtra("item", 2));
        }

    }

    public interface IReceive {
        void onReceive(int item);
    }



}
