package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.gson.SquareGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public interface SquareContract {
    interface Model {
        Observable<BaseGson<SquareGson>> squareUserActive(String uid);
    }

    interface View {
        void squareUserActive(List<SquareGson> squareGsons);
    }

    interface Presenter {
        void squareUserActive(String uid);
    }
}
