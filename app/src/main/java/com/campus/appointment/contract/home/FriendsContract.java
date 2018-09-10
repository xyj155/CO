package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/9/009.
 */

public interface FriendsContract {
    interface Model {
        Observable<BaseGson<UserGson>> getContactList(String uid);
    }

    interface View {
        void showContactList(List<UserGson> userGsons);
    }

    interface Presenter {
     void getContactList(String uid);
    }
}
