package com.campus.appointment.contract.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public interface AppIndexContract {
    interface Model {
        Observable<BaseGson<UserGson>> loginWithQQ(String qqid);
    }

    interface View {
        void showDialog(String msg);

        void hideDialog();

        void loginWithQQ(BaseGson<UserGson> list);
    }

    interface Presenter {
        void loginWithQQ(String qqid);
    }
}
