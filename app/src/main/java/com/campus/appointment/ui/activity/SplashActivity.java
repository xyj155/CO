package com.campus.appointment.ui.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.luck.picture.lib.permissions.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity {

    private RxPermissions rxPermissions;
    public static SplashActivity instance;

    @Override
    public int intiLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
        instance = this;
        if (rxPermissions == null) {
            rxPermissions = new RxPermissions(SplashActivity.this);
        }
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    starActivity(HomeActivity.class);
                                    finish();
                                }
                            }, 1500);

                        } else {
                            ToastUtil.showToastInfor("请授予APP权限,否则无法正常使用");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToastError("授权出错");
                        finish();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void initData() {

    }
}
