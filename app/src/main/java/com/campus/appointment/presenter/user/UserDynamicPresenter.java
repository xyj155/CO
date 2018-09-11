package com.campus.appointment.presenter.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.user.UserDynamicContract;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.model.user.UserDynamicModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public class UserDynamicPresenter implements UserDynamicContract.Presenter {
    private UserDynamicContract.View view;
    private UserDynamicContract.Model model = new UserDynamicModel();

    public UserDynamicPresenter(UserDynamicContract.View view) {
        this.view = view;
    }

    @Override
    public void querySingleUserPost(String uid) {
        model.querySingleUserPost(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SquareGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("获取个人动态出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SquareGson> squareGsonBaseGson) {
                        if (squareGsonBaseGson.isSuccess()) {
                            view.querySingleUserPost(squareGsonBaseGson.getData());
                        } else {
                            ToastUtil.showToastError("获取个人动态出错");

                        }
                    }
                });
    }
}
