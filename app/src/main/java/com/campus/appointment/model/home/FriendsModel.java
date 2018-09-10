package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.FriendsContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/9/009.
 */

public class FriendsModel implements FriendsContract.Model {
    @Override
    public Observable<BaseGson<UserGson>> getContactList(String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getContactList(uid);
    }
}
