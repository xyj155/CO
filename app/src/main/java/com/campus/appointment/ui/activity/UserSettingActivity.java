package com.campus.appointment.ui.activity;

import android.os.Bundle;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;

/**
 * @author Administrator
 */
public class UserSettingActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_user_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
    }

    @Override
    public void initData() {

    }
}
