package com.campus.appointment.presenter.home;

import android.util.Log;

import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.ConversationContract;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.model.home.ConversationModel;
import com.campus.appointment.util.IMUtils;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

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
    public void sendMessage(Message message) {
        if (message == null) {
            ToastUtil.showToastError("发送失败");
            return;
        }
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int code, String s) {
                if (code == IMUtils.CODE_SUCCESS) {

                } else {
                    ToastUtil.showToastError("发送失败,错误信息："+s+"错误代码："+code);
                }
            }
        });
        JMessageClient.sendMessage(message);
    }

    @Override
    public void conversionToUser(List<Conversation> conversations) {

    }

    @Override
    public void messageToEntity(List<Message> messages) {
        Log.i(TAG, "messageToEntity: " + messages.size());
        List<ConversationEntity> list = new ArrayList<>();
        for (Message message : messages) {
            if (message.getFromName().equals("123456")) {
                list.add(ConversationEntity.client(message));
            } else {
                list.add(ConversationEntity.service(message));
            }
        }
        view.getConversation(list);
    }

    @Override
    public void login() {
        IMUtils.login("123456", "123456", new BasicCallback() {
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
    public void getHistoryMessage() {
        Conversation conversation = JMessageClient.getSingleConversation("123456789");
        List<Message> allMessage = conversation.getAllMessage();
        for (Message m : allMessage) {
            MessageContent content = m.getContent();
            Log.i(TAG, "getHistoryMessage: "+ content.toJson());
        }
    }
}
