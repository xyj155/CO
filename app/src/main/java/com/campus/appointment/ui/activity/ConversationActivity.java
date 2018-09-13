package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.adapter.ConversationAdapter;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.ConversationContract;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.presenter.home.ConversationPresenter;
import com.campus.appointment.util.IMUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

import static cn.jpush.im.android.api.JMessageClient.FLAG_NOTIFY_SILENCE;

public class ConversationActivity extends BaseActivity implements ConversationContract.View {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.view)
    View view;
    @InjectView(R.id.ry_conversation)
    RecyclerView ryConversation;
    @InjectView(R.id.et_comment)
    EditText etComment;
    @InjectView(R.id.btn_send)
    TextView btnSend;
    @InjectView(R.id.ll_input)
    LinearLayout llInput;
    private List<Message> messages = new ArrayList<>();
    private ConversationAdapter mChatAdapter = new ConversationAdapter(null);
    private Conversation mConversation;
    private Message message1;
    private ConversationPresenter presenter = new ConversationPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter.login();
        ButterKnife.inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
        JMessageClient.exitConversation();//退出会话页面启用通知栏
    }

    @Override
    public void initData() {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        JMessageClient.registerEventReceiver(this);
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_SILENCE);
    }

    @Override
    public void refreshMessage() {

    }

    @Override
    public void getConversation(List<ConversationEntity> conversationEntities) {
        ryConversation.smoothScrollToPosition(conversationEntities.size());
        mChatAdapter.replaceData(conversationEntities);
    }

    @Override
    public void loginSuccess() {
        Log.i(TAG, "loginSuccess: " + "登陆成功");
        showmDialog("数据加载中...");
        mConversation = Conversation.createSingleConversation("456789");
        String title = mConversation.getTitle();
        Log.i(TAG, "loginSuccess: "+ title);
        if (mConversation != null) {
            List<Message> allMessage = mConversation.getAllMessage();
            if (allMessage != null) {
                messages.addAll(allMessage);
                presenter.messageToEntity(messages);
                hidemDialog();
                Log.i(TAG, "initView: " + allMessage);
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(ConversationActivity.this);
        manager.setStackFromEnd(true);
        ryConversation.setLayoutManager(manager);
        ryConversation.setAdapter(mChatAdapter);
        mChatAdapter.bindToRecyclerView(ryConversation);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etComment.getText().toString();
                if (mConversation != null) {
                    if (!content.isEmpty()) {
                        message1 = IMUtils.sendTextMessage(mConversation, content);
                        etComment.setText("");
                        messages.add(message1);
                        presenter.sendMessage(message1);
                        presenter.messageToEntity(messages);
                    } else {
                        ToastUtil.showToastInfor("请输入发送信息！");
                    }
                }
            }
        });
    }

    @Override
    public void loadHistoryMessage(final List<Message> messages) {

    }

    @Override
    public void showDialog(String msg) {

    }
    /**
     类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
     即可实现离线消息的接收。
     **/
    public void onEvent(OfflineMessageEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        List<Message> newMessageList = event.getOfflineMessageList();//获取此次离线期间会话收到的新消息列表
        for (Message message:newMessageList){
            messages.add(message);
        }
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
    }
    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        messages.add(msg);
        presenter.messageToEntity(messages);
        Log.i(TAG, "onEvent: "+event.getMessage());
    }

    /**
     如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
     sdk会发送此事件通知上层。
     **/
    public void onEvent(ConversationRefreshEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是
        //MSG_ROAMING_COMPLETE
        ConversationRefreshEvent.Reason reason = event.getReason();
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
        System.out.println("事件发生的原因 : " + reason);
    }
    @Override
    public void hideDialog() {

    }
}
