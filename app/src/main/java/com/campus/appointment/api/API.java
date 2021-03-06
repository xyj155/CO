package com.campus.appointment.api;


import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.gson.Face;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.gson.PostDetail;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.gson.UserInfromationGson;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/28.
 */

public interface API {

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Around/searchUserAround")
    Observable<BaseGson<UserGson>> queryByGeo(@Field("id") String id,
                                              @Field("latitude") double latitude,
                                              @Field("longitude") double longitude);


    //注册
    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/UserLogin")
    Observable<BaseGson<UserGson>> loginWithUserName(@Field("username") String username,
                                                     @Field("password") String password);

    //QQ登陆
    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/loginByQQ")
    Observable<BaseGson<UserGson>> loginWithQQ(@Field("qqid") String qqid);    //登录


    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/queryPost")
    Observable<BaseGson<SquareGson>> queryPost(@Field("uid") String uid);    //获取post列表


    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Contact/getContactList")
    Observable<BaseGson<UserGson>> getContactList(@Field("id") String uid);    //获取联系人列表


    @Multipart
    @POST("/CO/public/index.php/index/Post/PublishBlog")
    Observable<BaseGson<EmptyGson>> uploadPost(
            @PartMap() Map<String, RequestBody> partMap,
            @Part List<MultipartBody.Part> file);


    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/sendReport")
    Observable<BaseGson<EmptyGson>> sendReport(@Field("uid") String uid,
                                               @Field("type") String type,
                                               @Field("post_id") String post_id);    //获取联系人列表

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/sendAppReport")
    Observable<BaseGson<EmptyGson>> sendAppReport(@Field("uid") String uid,
                                                  @Field("content") String content);    //上传错误

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/querySingleUserPost")
    Observable<BaseGson<SquareGson>> querySingleUserPost(@Field("uid") String uid);    //个人动态


    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/updateThumb")
    Observable<BaseGson<EmptyGson>> updateThumb(@Field("uid") String uid,
                                                @Field("pid") String pid);    //个人动态

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/querySinglePost")
    Observable<BaseGson<PostDetail>> querySinglePost(@Field("puid") String puid,
                                                     @Field("id") String id,
                                                     @Field("uid") String uid);    //动态详情    @FormUrlEncoded

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/postComment")
    Observable<BaseGson<EmptyGson>> postComment(@Field("uid") String uid,
                                                @Field("comment") String comment,
                                                @Field("pid") String pid);    //动态详情

    @GET("/CO/public/index.php/index/Face/queryFace")
    Observable<BaseGson<Face>> getFaces();

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/Post/getMatherUserPost")
    Observable<BaseGson<MatherPostGson>> getMatherUserPost(@Field("uid") String uid,
                                                           @Field("pid") String pid);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/setObserve")
    Observable<BaseGson<EmptyGson>> setObserve(@Field("uid") String uid,
                                               @Field("pid") String pid,
                                               @Field("delete") String delete);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/getUserObservers")
    Observable<BaseGson<UserGson>> getUserObservers(@Field("uid") String uid);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/getUserInformation")
    Observable<BaseGson<UserInfromationGson>> getUserInformation(@Field("uid") String uid);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/register")
    Observable<BaseGson<EmptyGson>> register(@Field("username") String username,
                                             @Field("password") String password,
                                             @Field("tel") String tel,
                                             @Field("age") String age,
                                             @Field("sex") String sex,
                                             @Field("sign") String sign,
                                             @Field("tag") String tag
    );
    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/updateLatin")
    Observable<BaseGson<UserGson>> updateLatin(@Field("uid") String uid,
                                             @Field("city") String city,
                                             @Field("latitude") String latitude,
                                             @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/isHaveUsername")
    Observable<BaseGson<EmptyGson>> isHaveUsername(@Field("tel") String uid);

    @Streaming
    @Multipart
    @POST("/CO/public/index.php/index/User/updateUserAvatar")
    Call<BaseGson<EmptyGson>> updateUserAvatar(@Part("id") RequestBody id, @Part MultipartBody.Part part);

    @FormUrlEncoded
    @POST("/CO/public/index.php/index/User/addNewFriends")
    Observable<BaseGson<EmptyGson>> addNewFriends(@Field("pid") String pid,
                                                  @Field("uid") String uid);
}
