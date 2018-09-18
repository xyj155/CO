package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.MatchUserContract;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.model.home.MatchUserModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/15/015.
 */

public class MatchUserPresenter implements MatchUserContract.Presenter {
    private MatchUserContract.View view;
    private MatchUserModel matchUserModel = new MatchUserModel();
    private static final String TAG = "MatchUserPresenter";

    public MatchUserPresenter(MatchUserContract.View view) {
        this.view = view;
    }

    @Override
    public void addNewFriends(String pid, String uid) {
        Log.i(TAG, "addNewFriends: "+pid+"---"+uid);
        matchUserModel.addNewFriends(pid, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: " + error);
                        ToastUtil.showToastError("添加失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> userGsonBaseGson) {
                        Log.i(TAG, "onNext: " + userGsonBaseGson.getData());
                        if (userGsonBaseGson.isSuccess()) {
                            ToastUtil.showToastSuccess("添加成功");
                        } else {
                            ToastUtil.showToastError("添加失败");
                        }

                    }
                });
    }

    @Override
    public void getMatherUserPost(String uid, String pid) {
        view.showDialog("数据加载中...");
        matchUserModel.getMatherUserPost(uid, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<MatherPostGson>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError("获取个人信息出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<MatherPostGson> squareGsonBaseGson) {
                        view.hideDialog();
                        Log.i(TAG, "onNext: "+squareGsonBaseGson);
                        if (squareGsonBaseGson.isSuccess()) {
                            view.isObserve(squareGsonBaseGson.isObserve());
//                            view.isFriends(squareGsonBaseGson.isObserve());
                            view.loadMatcherUserPost(squareGsonBaseGson.getData());
                            if (squareGsonBaseGson.isFriend()==0){
                                view.isFriends(false);
                            }else {
                                view.isFriends(true);
                            }

                        } else {
                            ToastUtil.showToastError("获取个人信息出错");
                        }
                    }
                });
    }

    @Override
    public void setObserve(String uid, String pid, String delete) {
        matchUserModel.setObserve(uid, pid, delete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("信息出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> squareGsonBaseGson) {
                        if (squareGsonBaseGson.isSuccess()) {
                            view.isDeleteObserve(squareGsonBaseGson.getCode());
                        }
                    }
                });
    }
}
