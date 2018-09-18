package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public interface HomeContract {
    interface Model {
        Observable<BaseGson<UserGson>> queryAroundByGEO(String id,  double latitude, double longitude);

        Observable<BaseGson<UserGson>> updateLatin(String id, String city, String latitude, String longitude);


    }

    interface View {
        void showTags(BaseGson<UserGson> list);

        void showDialog(String msg);

        void hideDialog();

        void showLatin(UserGson userGson);
    }

    interface Presenter {
        void queryAroundByGEO(String id,  double latitude, double longitude);
        void updateLatin(String id, String city, String latitude, String longitude);
    }
}
