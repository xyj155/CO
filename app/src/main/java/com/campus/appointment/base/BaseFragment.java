package com.campus.appointment.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campus.appointment.R;
import com.campus.appointment.weight.AppleDialog;

/**
 * Created by Administrator on 2018/7/9.
 */

public abstract class BaseFragment extends Fragment {

    private View mContentView;

    public static BaseFragment basefragment;

    public Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(), container, false);

        init();
        setUpView(mContentView, savedInstanceState);
        setUpData();
        return mContentView;
    }

    public void showmDialog(String msg) {
        dialog = AppleDialog.createLoadingDialog(getActivity(), msg);
        dialog.show();
    }

    public void hidemDialog() {
        dialog.dismiss();
    }
    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView(View view, Bundle bundle);

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {
    }

    public View getContentView() {
        return mContentView;
    }

    public void starActivity(Class var) {
        Intent intent1 = new Intent(getContext(),var);
        startActivity(intent1);
        getActivity().overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
    }
}

