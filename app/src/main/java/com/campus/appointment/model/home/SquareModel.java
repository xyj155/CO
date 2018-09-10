package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.SquareContract;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquareModel implements SquareContract.Model {
    @Override
    public Observable<BaseGson<SquareGson>> squareUserActive(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().queryPost(uid);
    }
}
