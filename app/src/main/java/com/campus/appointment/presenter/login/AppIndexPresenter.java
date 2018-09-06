package com.campus.appointment.presenter.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.login.AppIndexContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.model.login.AppIndexModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class AppIndexPresenter implements AppIndexContract.Presenter {
    private AppIndexContract.Model model = new AppIndexModel();
    private AppIndexContract.View view;

    public AppIndexPresenter(AppIndexContract.View view) {
        this.view = view;
    }

    @Override
    public void loginWithQQ(String qqid) {
        view.showDialog("加载中...");
        model.loginWithQQ(qqid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError("登录失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideDialog();
                        view.loginWithQQ(userGsonBaseGson);
                    }
                });
    }
}
