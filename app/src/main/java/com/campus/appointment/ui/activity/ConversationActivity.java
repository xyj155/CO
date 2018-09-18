package com.campus.appointment.ui.activity;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.adapter.ConversationAdapter;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.ConversationContract;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.gson.Face;
import com.campus.appointment.presenter.home.ConversationPresenter;
import com.campus.appointment.util.IMUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqr.audio.AudioRecordManager;
import com.lqr.audio.IAudioRecordListener;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
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
    @InjectView(R.id.iv_camera)
    ImageView ivCamera;
    @InjectView(R.id.iv_pic)
    ImageView ivPic;
    @InjectView(R.id.iv_voice)
    ImageView ivVoice;
    @InjectView(R.id.iv_video)
    ImageView ivVideo;
    @InjectView(R.id.iv_music)
    ImageView ivMusic;
    @InjectView(R.id.iv_face)
    ImageView ivFace;
    @InjectView(R.id.bottom_view)
    LinearLayout bottomView;
    @InjectView(R.id.gv_face)
    RecyclerView gvFace;
    @InjectView(R.id.ll_voice)
    LinearLayout llVoice;
    @InjectView(R.id.ll_face)
    LinearLayout llFace;
    @InjectView(R.id.iv_voice_onclick)
    ImageView ivVoiceOnclick;
    @InjectView(R.id.tv_voice)
    TextView tvVoice;

    private List<Message> messages = new ArrayList<>();
    private ConversationAdapter mChatAdapter = new ConversationAdapter(null, ConversationActivity.this);
    private Conversation mConversation;
    private Message message1;
    private ConversationPresenter presenter = new ConversationPresenter(this);
    private FaceAdapter faceAdapter;

    private boolean videoisShow = true;
    private boolean emojiisShow = true;

    @Override
    public int intiLayout() {
        return R.layout.activity_conversation;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
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
        presenter.login(getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""), getSharedPreferences("user", MODE_PRIVATE).getString("password", ""));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        JMessageClient.registerEventReceiver(this);
        JMessageClient.setNotificationFlag(FLAG_NOTIFY_SILENCE);

    }

    @Override
    public void showFace(List<Face> faces) {
        faceAdapter = new FaceAdapter(faces);
        GridLayoutManager manager = new GridLayoutManager(ConversationActivity.this, 5);
        gvFace.setLayoutManager(manager);
        gvFace.setAdapter(faceAdapter);
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
        showmDialog("数据加载中...");
        Log.i(TAG, "loginSuccess: " + getIntent().getStringExtra("tel"));
        presenter.getHistoryMessage(getIntent().getStringExtra("tel"));
        mConversation = Conversation.createSingleConversation(getIntent().getStringExtra("tel"));
        if (mConversation != null) {
            List<Message> allMessage = mConversation.getAllMessage();
            if (allMessage != null) {
                messages.addAll(allMessage);
                presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
                hidemDialog();
                Log.i(TAG, "initView: " + allMessage);
            }
        }
        LinearLayoutManager manager = new LinearLayoutManager(ConversationActivity.this);
        manager.setStackFromEnd(true);
        ryConversation.setLayoutManager(manager);
        ryConversation.setAdapter(mChatAdapter);
        ryConversation.setHasFixedSize(false);
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
                        presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
                        presenter.sendMessage(message1);
                        Message latestMessage = mConversation.getLatestMessage();
                        Intent intent = new Intent();
                        intent.setAction("com.campus.appointment.broadcast.JIM_MESSAGE");
                        MessageContent messageContent = latestMessage.getContent();
                        TextContent textContent = (TextContent) messageContent;
                        intent.putExtra("message", textContent.getText());
                        sendBroadcast(intent);
                    } else {
                        ToastUtil.showToastInfor("请输入发送信息！");
                    }
                }
            }
        });
    }

    @Override
    public void loadHistoryMessage(final List<Message> messages) {
        for (Message message : messages) {
            messages.add(message);
        }
        presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
    }

    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        messages.add(msg);
        presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
        Log.i(TAG, "onEventMainThread: "+msg.getContent());
//        switch (msg.getContentType()) {
//            case text:
//                // 处理文字消息
//                TextContent textContent = (TextContent) msg.getContent();
//                textContent.getText();
//                txt_sms.append(textContent.getText());
//                break;
//        }
    }


    @Override
    public void hideDialog() {
        hidemDialog();
    }

    private Date curDate;
    private long diff;

    @OnClick({R.id.iv_camera, R.id.iv_pic, R.id.iv_voice, R.id.iv_video, R.id.iv_music, R.id.iv_face})
    public void onViewClicked(final View view) {
        switch (view.getId()) {
            case R.id.iv_camera:
                PictureSelector.create(ConversationActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.iv_pic:
                PictureSelector.create(ConversationActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.iv_voice:
                if (videoisShow) {
                    AudioRecordManager.getInstance(ConversationActivity.this).setMaxVoiceDuration(120);
                    File mAudioDir = new File(Environment.getExternalStorageDirectory(), "CO");
                    if (!mAudioDir.exists()) {
                        mAudioDir.mkdirs();
                    }
                    AudioRecordManager.getInstance(this).setAudioSavePath(mAudioDir.getAbsolutePath());
                    llVoice.setVisibility(View.VISIBLE);
                    ivVoiceOnclick.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case KeyEvent.ACTION_DOWN:
                                    curDate = new Date(System.currentTimeMillis());
                                    AudioRecordManager.getInstance(ConversationActivity.this).startRecord();
                                    Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                                    vibrator.vibrate(500);  //设置手机振动
                                    vibrator.cancel();//关闭振动
                                    tvVoice.setText("手指松开录音结束");
                                    break;
                                case KeyEvent.ACTION_UP:
                                    Date endDate = new Date(System.currentTimeMillis());
                                    diff = endDate.getTime() - curDate.getTime();
                                    Log.i(TAG, "onTouch: " + (int) diff / 1000);
                                    AudioRecordManager.getInstance(ConversationActivity.this).stopRecord();
                                    tvVoice.setText("请按下说话");
                                    break;

                            }
                            return true;
                        }
                    });
                    AudioRecordManager.getInstance(ConversationActivity.this).setAudioRecordListener(new IAudioRecordListener() {
                        @Override
                        public void initTipView() {

                        }

                        @Override
                        public void setTimeoutTipView(int i) {

                        }

                        @Override
                        public void setRecordingTipView() {

                        }

                        @Override
                        public void setAudioShortTipView() {

                        }

                        @Override
                        public void setCancelTipView() {

                        }

                        @Override
                        public void destroyTipView() {

                        }

                        @Override
                        public void onStartRecord() {

                        }

                        @Override
                        public void onFinish(Uri uri, int i) {
                            String path = uri.getPath();
                            Log.i(TAG, "onFinish: " + path);
                            try {
                                final Message imageMessage = IMUtils.createVoiceMessage(getIntent().getStringExtra("tel"), new File(path), (int) diff / 1000);
                                messages.add(imageMessage);
                                presenter.sendMessage(imageMessage);
                                presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onAudioDBChanged(int i) {

                        }
                    });
                    if (llFace.isShown()) {
                        llFace.setVisibility(View.GONE);
                    }
                    videoisShow = false;
                } else {
                    llVoice.setVisibility(View.GONE);
                    videoisShow = true;
                }
                break;
            case R.id.iv_video:

                break;
            case R.id.iv_music:
                break;
            case R.id.iv_face:
                if (emojiisShow) {
                    presenter.queryFace();
                    if (llVoice.isShown()) {
                        llVoice.setVisibility(View.GONE);
                    }
                    llFace.setVisibility(View.VISIBLE);
                    emojiisShow = false;
                } else {
                    llFace.setVisibility(View.GONE);
                    emojiisShow = true;
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    Log.i(TAG, "onActivityResult: " + selectList.get(0).getCompressPath());
                    try {
                        Message imageMessage = IMUtils.createImageMessage(getIntent().getStringExtra("tel"), selectList.get(0).getCompressPath());
                        messages.add(imageMessage);
                        presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
                        presenter.sendMessage(imageMessage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private class FaceAdapter extends BaseQuickAdapter<Face, BaseViewHolder> {


        public FaceAdapter(@Nullable List<Face> data) {
            super(R.layout.conversation_face_item, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, final Face face) {
            ImageView view = baseViewHolder.getView(R.id.iv_face);
            baseViewHolder.setOnClickListener(R.id.iv_face, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                final Message imageMessage = IMUtils.createURLImageMessage(getIntent().getStringExtra("tel"), face.getFace());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        messages.add(imageMessage);
                                        presenter.sendMessage(imageMessage);
                                        presenter.messageToEntity(messages, getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""));
                                    }
                                });

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                }
            });
            Glide.with(ConversationActivity.this).load(face.getFace()).into(view);
        }
    }

}
