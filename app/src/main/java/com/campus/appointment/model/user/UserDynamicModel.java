package com.campus.appointment.model.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.user.UserDynamicContract;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public class UserDynamicModel implements UserDynamicContract.Model {
    @Override
    public Observable<BaseGson<SquareGson>> querySingleUserPost(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().querySingleUserPost(uid);
    }
}
