package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.model.home.HomeModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.Model model = new HomeModel();
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    private static final String TAG = "HomePresenter";

    @Override
    public void queryAroundByGEO(String id, double latitude, double longitude) {
        view.showDialog("加载中...");
        model.queryAroundByGEO(id, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: " + error);
                        view.hideDialog();
                        ToastUtil.showToastError("附近的人信息列表获取出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        view.hideDialog();
                        Log.i(TAG, "onNext: " + userGsonBaseGson.getData());
                        if (userGsonBaseGson.isSuccess()) {
                            if (userGsonBaseGson.getData().size()==0){
                                ToastUtil.showToastUsual("你附近还没有人哦，找小伙伴一起来玩啊！");
                            }else {
                                view.showTags(userGsonBaseGson);

                            }

                        } else {
                            System.out.println("error");
                            ToastUtil.showToastError("附近的人信息列表获取出错");
                        }

                    }
                });
    }

    @Override
    public void updateLatin(String id, String city, String latitude, String longitude) {
        model.updateLatin(id, city, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: " + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        Log.i(TAG, "onNext: " + userGsonBaseGson.getData());
                        if (userGsonBaseGson.isSuccess()) {
                            view.showLatin(userGsonBaseGson.getData().get(0));
                        } else {
                            System.out.println("error");
                        }

                    }
                });
    }


}
