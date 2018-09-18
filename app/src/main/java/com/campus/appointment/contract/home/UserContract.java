package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/18/018.
 */

public interface UserContract {
    interface Model {
        Call<BaseGson<EmptyGson>> uploadAvatar(RequestBody id, MultipartBody.Part file);
        Observable<BaseGson<EmptyGson>> sendBugs(String uid, String content);
    }

    interface View {
        void loadAvatar(BaseGson<EmptyGson> upload);

        void sendBugs(BaseGson<EmptyGson> upload);
    }

    interface Presenter {
        void uploadAvatar(String id, String files);

        void sendBugs(String id, String content);
    }
}
