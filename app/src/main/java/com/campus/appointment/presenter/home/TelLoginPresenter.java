package com.campus.appointment.presenter.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.TelLoginContract;
import com.campus.appointment.model.home.TelLoginModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/17/017.
 */

public class TelLoginPresenter implements TelLoginContract.Presenter {
    private TelLoginContract.Model model = new TelLoginModel();
    private TelLoginContract.View view;

    public TelLoginPresenter(TelLoginContract.View view) {
        this.view = view;
    }

    @Override
    public void isHaveUsername(String tel) {
        model.isHaveUsername(tel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("请求出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> baseGson) {
                        if (baseGson.isSuccess()) {
                            view.isHavaTel();
                        } else {
                         view.notHave();
                        }
                    }
                });
    }
}
