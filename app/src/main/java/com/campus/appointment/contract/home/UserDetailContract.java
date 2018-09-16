package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserInfromationGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public interface UserDetailContract {
    interface Model {
        Observable<BaseGson<UserInfromationGson>> querySingleUser(String uid);
    }

    interface View {
        void showDialog(String msg);
        void hideDialog();
        void loadUserinfor(UserInfromationGson getSingleUser);
    }

    interface Presenter {
        void querySingleUser(String uid);
    }
}
