package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/10/010.
 */

public interface EditPostContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> uploadPost(
                Map<String, RequestBody> partMap,
                List<MultipartBody.Part> file);
    }

    interface View {
        void isUploadSuccess(BaseGson<EmptyGson> baseGson);

        void showLoading(String msg);

        void hideLoading();
    }

    interface Presenter {
        void uploadPost(
                Map<String, RequestBody> partMap,
                List<MultipartBody.Part> file);
    }
}
