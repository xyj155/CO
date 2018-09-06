package com.campus.appointment.api;


import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public interface API {

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Around/searchUserAround")
    Observable<BaseGson<UserGson>> queryByGeo(@Field("id") String id,
                                              @Field("city") String city,
                                              @Field("latitude") String latitude,
                                              @Field("longitude") String longitude);


    //注册
    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/UserLogin")
    Observable<BaseGson<UserGson>> loginWithUserName(@Field("username") String username,
                                                     @Field("password") String password);    //注册

    //注册
    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/loginByQQ")
    Observable<BaseGson<UserGson>> loginWithQQ(@Field("qqid") String qqid);    //登录
}
