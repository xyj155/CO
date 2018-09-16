package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.gson.MatherPostGson;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/15/015.
 */

public interface MatchUserContract {
    interface Model {
        Observable<BaseGson<MatherPostGson>> getMatherUserPost(String uid,String pid);

        Observable<BaseGson<EmptyGson>> setObserve(String uid, String pid, String delete);

    }

    interface View {
        void showDialog(String msg);

        void hideDialog();

        void isObserve(boolean isObserve);

        void loadMatherUserPost(List<MatherPostGson> squareGsons);

        void isDeleteObserve(int code);
    }

    interface Presenter {
        void getMatherUserPost(String uid,String pid);
        void setObserve(String uid,String pid,String delete);
    }
}
