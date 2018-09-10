package com.campus.appointment.presenter.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.EditPostContract;
import com.campus.appointment.model.home.EditPostModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/10/010.
 */

public class EditPostPresenter implements EditPostContract.Presenter {
    private EditPostContract.View view;
    private EditPostContract.Model model = new EditPostModel();

    public EditPostPresenter(EditPostContract.View view) {
        this.view = view;
    }

    @Override
    public void uploadPost(Map<String, RequestBody> partMap,
                           List<MultipartBody.Part> file) {
        view.showLoading("传输中...");
        model.uploadPost(partMap, file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("发布失败");
                        view.hideLoading();
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        view.hideLoading();
                        if (emptyGsonBaseGson.isSuccess()) {
                            view.isUploadSuccess(emptyGsonBaseGson);
                        } else {
                            ToastUtil.showToastError("发布失败");
                        }
                    }
                });
    }
}
