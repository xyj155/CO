package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
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
    public void queryAroundByGEO(String id, String city, String latitude, String longitude) {
        model.queryAroundByGEO(id, city, latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<UserGson>>() {
                    @Override
                    public void onError(String error) {
                        Log.i(TAG, "onError: "+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<UserGson> userGsonBaseGson) {
                        if (userGsonBaseGson.isSuccess()){
                            view.showTags(userGsonBaseGson);
                        }else {
                            System.out.println("error");
                        }

                    }
                });
    }
}
