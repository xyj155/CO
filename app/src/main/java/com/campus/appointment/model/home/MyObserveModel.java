package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.MyObserveContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class MyObserveModel implements MyObserveContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> getUserObservers(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getUserObservers(uid);
    }
}
