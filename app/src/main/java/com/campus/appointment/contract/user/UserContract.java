package com.campus.appointment.contract.user;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public interface UserContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> sendBugs(String uid, String content);
    }

    interface View {
        void sendBugs();
    }

    interface Presenter {
        void sendBugs(String uid, String content);
    }
}
