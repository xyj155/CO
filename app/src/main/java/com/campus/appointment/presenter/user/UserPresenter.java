package com.campus.appointment.presenter.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.user.UserContract;
import com.campus.appointment.model.user.UserModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public class UserPresenter implements UserContract.Presenter {
    private UserContract.View view;
    private UserContract.Model model = new UserModel();

    public UserPresenter(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void sendBugs(String uid, String content) {
        model.sendBugs(uid, content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("反馈失败:"+error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> emptyGsonBaseGson) {
                        if (emptyGsonBaseGson.isSuccess()) {
                            ToastUtil.showToastSuccess("反馈成功");
                        } else {
                            ToastUtil.showToastError("反馈失败");
                        }
                    }
                });
    }
}
