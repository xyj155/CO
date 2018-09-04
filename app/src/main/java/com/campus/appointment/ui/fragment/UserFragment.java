package com.campus.appointment.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class UserFragment extends BaseFragment {
    private static UserFragment instance;

    public static synchronized  UserFragment getInstance() {
        if (instance==null){
            return new UserFragment();
        }else {
            return instance;
        }
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {

    }
}
