package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.UserPostDetailContract;
import com.campus.appointment.gson.PostDetail;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class UserPostDetailModel implements UserPostDetailContract.Model {
    @Override
    public Observable<BaseGson<PostDetail>> querySinglePost(String puid, String id, String uid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().querySinglePost(puid,id,uid);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> postComment(String uid, String comment,String pid) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().postComment(uid,comment,pid);
    }
}
