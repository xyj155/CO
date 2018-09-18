package com.campus.appointment.contract.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.gson.Face;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import rx.Observable;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public interface ConversationContract {
    interface Model {
        Observable<BaseGson<Face>> queryFace();
    }

    interface View {

        void showFace(List<Face> faces);

        /**
         * 刷新消息
         */
        void refreshMessage();

        /**
         * 获取会话
         */
        void getConversation(List<ConversationEntity> conversationEntities);

        /**
         * 登录是否成功
         */
        void loginSuccess();

        void loadHistoryMessage(List<Message> messages);

        void showDialog(String msg);

        void hideDialog();
    }

    interface Presenter {

        void queryFace();
        /**
         * 发送消息
         *
         * @param message
         */
        void sendMessage(Message message);

        /**
         * 会话列表
         *
         * @param conversations
         */
        void conversionToUser(List<Conversation> conversations);

        void messageToEntity(List<Message> message,String username);

        /**
         * im登录
         */
        void login(String username,String password);

        void getHistoryMessage(String username);
    }
}
