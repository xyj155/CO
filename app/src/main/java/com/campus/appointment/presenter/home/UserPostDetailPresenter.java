package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.UserPostDetailContract;
import com.campus.appointment.gson.PostDetail;
import com.campus.appointment.model.home.UserPostDetailModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class UserPostDetailPresenter implements UserPostDetailContract.Presenter {
    private UserPostDetailContract.View view;
    private UserPostDetailContract.Model model=new UserPostDetailModel();

    public UserPostDetailPresenter(UserPostDetailContract.View view) {
        this.view = view;
    }

    private static final String TAG = "UserPostDetailPresenter";
    @Override
    public void querySinglePost(String puid, String id, String uid) {
        view.showDialog("数据加载中....");
        Log.i(TAG, "querySinglePost: "+puid+"------"+id+"------"+uid);
        model.querySinglePost(puid, id, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<PostDetail>>() {
                    @Override
                    public void onError(String error) {
                        view.hideDialog();
                        ToastUtil.showToastError("获取详情出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<PostDetail> postDetailBaseGson) {
                        view.hideDialog();
                        if (postDetailBaseGson.isSuccess()) {
                            view.querySinglePost(postDetailBaseGson.getData());
                        }else {
                            ToastUtil.showToastError("请求出错");
                        }
                    }
                });
    }

    @Override
    public void postComment(String uid,String comment, String pid) {
        model.postComment(uid,comment, pid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<EmptyGson>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("评论失败");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<EmptyGson> postDetailBaseGson) {
                        view.hideDialog();
                        if (postDetailBaseGson.isSuccess()) {
                            view.postComment(postDetailBaseGson.getData());
                        }else {
                            ToastUtil.showToastError("评论失败");
                        }
                    }
                });
    }
}
