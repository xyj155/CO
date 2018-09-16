package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public interface MyObserveContract {
    interface Model {
        Observable<BaseGson<UserGson>> getUserObservers(String uid);
    }

    interface View {
        void showDialog(String msg);
        void hideDialog();
        void showUserObserves(List<UserGson> userGsons);
    }

    interface Presenter {
        void getUserObservers(String uid);
    }
}
