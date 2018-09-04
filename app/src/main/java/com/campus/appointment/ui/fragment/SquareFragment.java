package com.campus.appointment.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class SquareFragment extends BaseFragment {
    private static SquareFragment instance;

    public static synchronized  SquareFragment getInstance() {
        if (instance==null){
            return new SquareFragment();
        }else {
            return instance;
        }
    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_square;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

    }

    @Override
    protected void setUpData() {

    }
}
