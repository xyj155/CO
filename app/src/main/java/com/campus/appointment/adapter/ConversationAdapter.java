package com.campus.appointment.adapter;


import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.entity.ConversationEntity;
import com.campus.appointment.weight.InformationDialog;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lqr.audio.AudioPlayManager;
import com.lqr.audio.IAudioPlayListener;

import java.io.File;
import java.util.List;

import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.model.Message;


/**
 * Created by Administrator on 2018/7/11.
 */
public class ConversationAdapter extends BaseMultiItemQuickAdapter<ConversationEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context context;

    public ConversationAdapter(List<ConversationEntity> data, Context context) {
        super(data);
        this.context = context;
        addItemType(ConversationEntity.TYPE_SERVICES_MESSAGE, R.layout.conversation_ry_item_chat_chatfriends);
        addItemType(ConversationEntity.TYPE_CLIENT_MESSAGE, R.layout.conversation_ry_item_chat_user);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ConversationEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case ConversationEntity.TYPE_SERVICES_MESSAGE:
                //客服消息
                break;
            case ConversationEntity.TYPE_CLIENT_MESSAGE:
                //客户消息
                break;
            default:
        }
        final Message data = item.getData();
        //时间
        String time = String.format("%tF %tT", data.getCreateTime(), data.getCreateTime());
        //用户名
        String fromName = data.getFromName();
        final MessageContent messageContent = data.getContent();
//        TextContent textContent = (TextContent) messageContent;
//        helper.setText(R.id.tv_main_item_content,textContent.getText());

//
//        //设置不可见
//        helper.getView(R.id.tv_main_item_content).setVisibility(View.GONE);
//        helper.getView(R.id.iv_main_item_content).setVisibility(View.GONE);
//        helper.getView(R.id.ll_main_item_content).setVisibility(View.GONE);
        switch (messageContent.getContentType()) {
            case text:
                TextContent textContent = (TextContent) messageContent;
                helper.setText(R.id.tv_main_item_content, textContent.getText())
                        .setVisible(R.id.tv_main_item_content, true);
                ImageView view = helper.getView(R.id.iv_image);
                view.setVisibility(View.GONE);
                break;
            case image:
                //处理图片消息
                ImageContent imageContent = (ImageContent) messageContent;
                //图片本地地址
                imageContent.getLocalPath();
                //图片对应缩略图的本地地址
                String thumbnailPath = imageContent.getLocalThumbnailPath();
                Glide.with(context).load(thumbnailPath).into((ImageView) helper.getView(R.id.iv_image));
                helper.setVisible(R.id.iv_image, true);
                TextView textView = helper.getView(R.id.tv_main_item_content);
                textView.setVisibility(View.GONE);
                break;
            case voice:
                final VoiceContent voiceContent = (VoiceContent) messageContent;
                voiceContent.getLocalPath();
                int duration = voiceContent.getDuration();
                TextView content = helper.getView(R.id.tv_main_item_content);
                ImageView image = helper.getView(R.id.iv_image);
                image.setVisibility(View.GONE);
                TextView tvVoice = helper.getView(R.id.tv_voice);
                tvVoice.setVisibility(View.VISIBLE);
                tvVoice.setText(duration + "''");
                helper.setOnClickListener(R.id.tv_voice, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = InformationDialog.createLoadingDialog(context);
                        voiceContent.downloadVoiceFile(data, new DownloadCompletionCallback() {
                            @Override
                            public void onComplete(int i, String s, File file) {
                                AudioPlayManager.getInstance().startPlay(context, Uri.fromFile(file), new IAudioPlayListener() {
                                    @Override
                                    public void onStart(Uri var1) {
                                        dialog.show();
                                        //开播（一般是开始语音消息动画）
                                    }

                                    @Override
                                    public void onStop(Uri var1) {
                                        //停播（一般是停止语音消息动画）
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onComplete(Uri var1) {
                                        //播完（一般是停止语音消息动画）
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });
                content.setVisibility(View.GONE);
                break;
            case custom:
                //处理自定义消息
                CustomContent customContent = (CustomContent) messageContent;
                //获取自定义的值
                customContent.getNumberValue("custom_num");
                customContent.getBooleanValue("custom_boolean");
                customContent.getStringValue("custom_string");
                break;
            default:
        }

//        //设置时间
//        helper.setText(R.id.tv_item_main_time, time)
//                .setText(R.id.tv_main_item_name, fromName);
    }


}