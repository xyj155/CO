package com.campus.appointment.model.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.MatchUserContract;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

import static android.content.ContentValues.TAG;

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
    @Override
    public Observable<BaseGson<EmptyGson>> addNewFriends(String pid, String uid) {
        Log.i(TAG, "addNewFriends: "+pid+"----"+uid);
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().addNewFriends(pid,uid);
    }
}
