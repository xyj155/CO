package com.campus.appointment.presenter.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.login.UserRegisterInformationContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.model.login.UserRegisterInformationModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserRegisterInformationPresenter implements UserRegisterInformationContract.Presenter {
    private UserRegisterInformationContract.View view;
    private UserRegisterInformationModel model = new UserRegisterInformationModel();

    public UserRegisterInformationPresenter(UserRegisterInformationContract.View view) {
        this.view = view;
    }

    @Override
    public void userReister(String username, String password, String tel, String age, String sex, String sign, String tag) {
        view.showDialog("注册中...");
        model.userReister(username, password, tel, age, sex, sign, tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError("注册失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> baseGson) {
                        view.hideDialog();
                        if (baseGson.isSuccess()) {
                            view.registerSuccesss(baseGson);
                        } else {
                            ToastUtil.showToastError("注册失败");
                        }
                    }
                });
    }

    @Override
    public void loginByUsername(String username, String password) {
        view.showDialog("登陆中...");
        model.loginByUsername(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("登陆失败");
                        view.hideDialog();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideDialog();
                        if (userGsonBaseGson.isSuccess()) {
                            view.loginByUsername(userGsonBaseGson.getData().get(0));
                        }else {
                            ToastUtil.showToastError("登陆失败");
                        }
                    }
                });
    }
}
