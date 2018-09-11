package com.campus.appointment.model.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.user.UserContract;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public class UserModel implements UserContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> sendBugs(String uid, String content) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().sendAppReport(uid,content);
    }
}
