package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.UserDetailContract;
import com.campus.appointment.gson.UserInfromationGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserDetailModel implements UserDetailContract.Model {
    @Override
    public Observable<BaseGson<UserInfromationGson>> querySingleUser(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getUserInformation(uid);
    }
}
