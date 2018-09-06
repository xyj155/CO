package com.campus.appointment.model.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.login.AppIndexContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class AppIndexModel implements AppIndexContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> loginWithQQ(String qqid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().loginWithQQ(qqid);
    }
}
