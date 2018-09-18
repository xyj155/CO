package com.campus.appointment.model.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.login.UserRegisterInformationContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserRegisterInformationModel implements UserRegisterInformationContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> userReister(String username, String password, String tel, String age, String sex,String sign,String tag) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().register(username,password,tel,age,sex,sign,tag);
    }

    @Override
    public Observable<BaseGson<UserGson>> loginByUsername(String username, String password) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().loginWithUserName(username,password);
    }
}
