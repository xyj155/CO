package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.gson.PostDetail;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public interface UserPostDetailContract {
    interface Model {
        Observable<BaseGson<PostDetail>> querySinglePost( String puid,String id, String uid);
        Observable<BaseGson<EmptyGson>> postComment(String uid,String comment,String pid);
    }

    interface View {
        void showDialog(String msg);
        void hideDialog();

        void querySinglePost(List<PostDetail> list);

        void postComment(List<EmptyGson> list);
    }

    interface Presenter {
        void querySinglePost(String puid,String id, String uid);
        void postComment(String uid,String comment,String pid);
    }
}
