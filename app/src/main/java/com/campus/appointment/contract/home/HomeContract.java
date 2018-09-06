package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.UserGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public interface HomeContract {
    interface Model {
        Observable<BaseGson<UserGson>> queryAroundByGEO(String id, String  city, String latitude, String longitude);
    }

    interface View {
        void showTags(BaseGson<UserGson>  list);
    }

    interface Presenter {
        void queryAroundByGEO(String id, String  city, String latitude, String longitude);
    }
}
