package com.campus.appointment.presenter.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.SquareContract;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.model.home.SquareModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquarePresenter implements SquareContract.Presenter {
    private SquareContract.Model model = new SquareModel();
    private SquareContract.View view;

    public SquarePresenter(SquareContract.View view) {
        this.view = view;
    }

    private static final String TAG = "SquarePresenter";

    @Override
    public void squareUserActive(String uid) {
        model.squareUserActive(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<SquareGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("请求错误" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<SquareGson> listBaseGson) {
                        if (listBaseGson.isSuccess()) {
                            view.squareUserActive(listBaseGson.getData());
                        }
                    }
                });
    }

    @Override
    public void updateThumb(String uid, String pid) {
        model.updateThumb(uid,pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("点赞失败" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> listBaseGson) {
                        if (listBaseGson.isSuccess()) {
                            view.updateThumb(listBaseGson.getData());
                        }
                    }
                });
    }

    @Override
    public void sendReport(String uid, String type, String post_id) {
        model.sendReport(uid,type,post_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("系统错误" + error);
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> listBaseGson) {

                        if (listBaseGson.isSuccess()) {
                            view.sendReport(listBaseGson.getData());
                        }
                    }
                });
    }
}
