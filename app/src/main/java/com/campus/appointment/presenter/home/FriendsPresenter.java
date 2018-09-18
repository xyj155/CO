package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.FriendsContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.model.home.FriendsModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/9/009.
 */

public class FriendsPresenter implements FriendsContract.Presenter {
    private FriendsContract.Model model = new FriendsModel();
    private FriendsContract.View view;

    public FriendsPresenter(FriendsContract.View view) {
        this.view = view;
    }

    private static final String TAG = "FriendsPresenter";

    @Override
    public void getContactList(String uid) {
        model.getContactList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("获取联系人列表出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        Log.i(TAG, "onNext: " + userGsonBaseGson);
                        view.showContactList(userGsonBaseGson.getData());
                    }
                });
    }
}
