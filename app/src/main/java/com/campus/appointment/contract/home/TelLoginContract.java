package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/17/017.
 */

public interface TelLoginContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> isHaveUsername(String tel);
    }

    interface View {
        void isHavaTel();

        void notHave();
    }

    interface Presenter {
        void isHaveUsername(String tel);

    }
}
