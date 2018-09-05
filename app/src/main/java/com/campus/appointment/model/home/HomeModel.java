package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> queryAroundByGEO(String id, String city, String latitude, String longitude) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryByGeo(id,city,latitude,longitude);
    }
}
