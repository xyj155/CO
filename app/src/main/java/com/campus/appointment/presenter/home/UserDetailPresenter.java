package com.campus.appointment.presenter.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.UserDetailContract;
import com.campus.appointment.gson.UserInfromationGson;
import com.campus.appointment.model.home.UserDetailModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserDetailPresenter implements UserDetailContract.Presenter {
    private UserDetailContract.View view;
    private UserDetailModel model = new UserDetailModel();

    public UserDetailPresenter(UserDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void querySingleUser(String uid) {
        view.showDialog("数据加载中..");
        model.querySingleUser(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserInfromationGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError("获取个人信息出错！");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserInfromationGson> userInfromationGsonBaseGson) {
                        view.hideDialog();
                        if (userInfromationGsonBaseGson.isSuccess()) {
                            view.loadUserinfor(userInfromationGsonBaseGson.getData().get(0));
                        }else {
                            ToastUtil.showToastError("获取个人信息出错！");
                        }
                    }
                });
    }
}
