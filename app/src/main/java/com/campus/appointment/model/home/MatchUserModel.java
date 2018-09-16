package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.MatchUserContract;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/15/015.
 */

public class MatchUserModel implements MatchUserContract.Model {
    @Override
    public Observable<BaseGson<MatherPostGson>> getMatherUserPost(String uid, String pid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getMatherUserPost(uid, pid);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> setObserve(String uid, String pid, String delete) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().setObserve(uid, pid, delete);
    }
}
