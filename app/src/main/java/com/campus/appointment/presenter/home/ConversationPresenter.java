package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.BaseObserver;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.ConversationContract;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.gson.Face;
import com.campus.appointment.model.home.ConversationModel;
import com.campus.appointment.util.IMUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class ConversationPresenter implements ConversationContract.Presenter {
    private ConversationContract.View view;
    private ConversationModel model = new ConversationModel();

    public ConversationPresenter(ConversationContract.View view) {
        this.view = view;
    }

    @Override
    public void queryFace() {
        model.queryFace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseGson<Face>>() {
                    @Override
                    public void onError(String error) {
                        ToastUtil.showToastError("获取表情包出错");
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(BaseGson<Face> faceBaseGson) {
                        view.showFace(faceBaseGson.getData());
                    }
                });
    }

    @Override
    public void sendMessage(Message message) {
        if (message == null) {
            ToastUtil.showToastError("发送不可为空");
            return;
        }
        JMessageClient.sendMessage(message);
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int code, String s) {
                if (code == IMUtils.CODE_SUCCESS) {

                } else {
                    ToastUtil.showToastError("发送失败,错误信息：" + s + "错误代码：" + code);
                }
            }
        });
    }

    @Override
    public void conversionToUser(List<Conversation> conversations) {
    }

    @Override
    public void messageToEntity(List<Message> messages, String username) {
        Log.i(TAG, "messageToEntity: " + messages.size());
        List<ConversationEntity> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getFromName().equals(username)) {
                list.add(ConversationEntity.client(message));
            } else {
                list.add(ConversationEntity.service(message));
            }
        }
        view.getConversation(list);
    }

    @Override
    public void login(String username, String password) {
        view.showDialog("登陆中...");
        IMUtils.login(username, password, new BasicCallback() {
            @Override
            public void gotResult(int i, final String s) {
                if (i == 0) {
                    view.hideDialog();
                    view.loginSuccess();
                } else {
                    view.hideDialog();
                    ToastUtil.showToastError("未知错误：请联系开发者,错误详情：" + s);
                }
            }
        });
    }


    @Override
    public void getHistoryMessage(String username) {
        Conversation conversation = JMessageClient.getSingleConversation(username);
        List<Message> allMessage = conversation.getAllMessage();
        List<ConversationEntity> list = new ArrayList<>();
        for (Message message : allMessage) {
            if (message.getFromName().equals(username)) {
                list.add(ConversationEntity.client(message));
            } else {
                list.add(ConversationEntity.service(message));
            }
        }
        view.getConversation(list);
    }
}
