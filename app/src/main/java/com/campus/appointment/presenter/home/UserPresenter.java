package com.campus.appointment.presenter.home;

import android.graphics.Bitmap;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.UserContract;
import com.campus.appointment.model.home.UserModel;
import com.campus.appointment.util.BitmapUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/18/018.
 */

public class UserPresenter implements UserContract.Presenter {
    private UserModel model = new UserModel();
    private UserContract.View view;

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    @Override
    public void uploadAvatar(String id, String files) {
        Bitmap getimage = BitmapUtils.getimage(files);
        File file = BitmapUtils.Bitmap2File(getimage);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        model.uploadAvatar(toRequestBody(id), part)
                .enqueue(new Callback<BaseGson<EmptyGson>>() {
                    @Override
                    public void onResponse(Call<BaseGson<EmptyGson>> call, Response<BaseGson<EmptyGson>> response) {
                        if (response.isSuccessful()){
                            view.loadAvatar(response.body());
                        }else {
                            ToastUtil.showToastInfor("头像上传失败！");
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseGson<EmptyGson>> call, Throwable throwable) {
                        ToastUtil.showToastInfor("头像上传失败！");
                    }
                });
    }

    private static final String TAG = "UserPresenter";
    @Override
    public void sendBugs(String id, String content) {
        model.sendBugs(id, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("提交失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> baseGson) {
                        if (baseGson.isSuccess()) {
                            view.sendBugs(baseGson);
                        } else {
                            ToastUtil.showToastError("提交失败");
                        }
                    }
                });

    }
}
