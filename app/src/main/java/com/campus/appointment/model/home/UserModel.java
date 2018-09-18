package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.UserContract;
import com.campus.appointment.http.RetrofitUtil;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/18/018.
 */

public class UserModel implements UserContract.Model {
    @Override
    public Call<BaseGson<EmptyGson>> uploadAvatar(RequestBody id, MultipartBody.Part file) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().updateUserAvatar(id,file);
    }

    @Override
    public Observable<BaseGson<EmptyGson>> sendBugs(String uid, String content) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().sendAppReport(uid,content);
    }
}
