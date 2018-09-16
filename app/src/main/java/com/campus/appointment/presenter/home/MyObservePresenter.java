package com.campus.appointment.presenter.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.MyObserveContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.model.home.MyObserveModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class MyObservePresenter implements MyObserveContract.Presenter {
    private MyObserveContract.View view;
    private MyObserveContract.Model model = new MyObserveModel();

    public MyObservePresenter(MyObserveContract.View view) {
        this.view = view;
    }

    @Override
    public void getUserObservers(String uid) {
        view.showDialog("数据加载中...");
        model.getUserObservers(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastWarning("获取列表出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideDialog();
                        if (userGsonBaseGson.isSuccess()) {
                            view.showUserObserves(userGsonBaseGson.getData());
                        }else {
                            ToastUtil.showToastWarning("获取列表出错");
                        }
                    }
                });
    }
}
