package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.gson.SquareGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public interface SquareContract {
    interface Model {
        Observable<BaseGson<SquareGson>> squareUserActive(String uid);

        Observable<BaseGson<EmptyGson>> sendReport(String uid,
                                                   String type,
                                                   String post_id);


    }

    interface View {
        void squareUserActive(List<SquareGson> squareGsons);

        void sendReport(List<EmptyGson> squareGsons);
    }

    interface Presenter {
        void squareUserActive(String uid);

        void sendReport(String uid,
                        String type,
                        String post_id);
    }
}
