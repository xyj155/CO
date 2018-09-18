package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class DecideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences editor=getSharedPreferences("user",MODE_PRIVATE);
        boolean login = editor.getBoolean("login", false);
        if (login){
            startActivity(new Intent(DecideActivity.this,SplashActivity.class));
            finish();
        }else {
            startActivity(new Intent(DecideActivity.this,AppIndexActivity.class));
            finish();
        }
    }
}
