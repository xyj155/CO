package com.campus.appointment.contract.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.SquareGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public interface UserDynamicContract {
    interface Model {
        Observable<BaseGson<SquareGson>> querySingleUserPost(String uid);
    }

    interface View {
        void querySingleUserPost(List<SquareGson> squareGsons);
    }

    interface Presenter {
      void   querySingleUserPost(String uid);
    }
}
