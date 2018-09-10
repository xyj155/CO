package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.EditPostContract;
import com.campus.appointment.http.RetrofitUtil;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/10/010.
 */

public class EditPostModel implements EditPostContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> uploadPost(Map<String, RequestBody> partMap, List<MultipartBody.Part> file) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().uploadPost(partMap, file);
    }
}
