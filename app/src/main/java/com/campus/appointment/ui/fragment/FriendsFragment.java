package com.campus.appointment.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.FriendsContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.FriendsPresenter;
import com.campus.appointment.ui.activity.ConversationActivity;
import com.campus.appointment.util.IMUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.wave.MultiWaveHeader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class FriendsFragment extends BaseFragment implements FriendsContract.View {
    private static FriendsFragment instance;
    @InjectView(R.id.tv_friend)
    TextView tvFriend;
    @InjectView(R.id.ry_conversation)
    RecyclerView ryConversation;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.gifView)
    GifImageView gifView;
    @InjectView(R.id.sl_friends)
    SmartRefreshLayout slFriends;
    @InjectView(R.id.friend)
    RelativeLayout friend;
    @InjectView(R.id.tv_nodata)
    TextView tvNodata;
    private FriendsPresenter friendsPresenter;

    public static synchronized FriendsFragment getInstance() {
        if (instance == null) {
            return new FriendsFragment();
        } else {
            return instance;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        friendsPresenter.getContactList(String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)));
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_friends;
    }

    private static final String TAG = "FriendsFragment";
    @Override
    protected void setUpView(View view, Bundle bundle) {
        ButterKnife.inject(this, view);
        friendsPresenter = new FriendsPresenter(this);
        slFriends.autoRefresh();
        slFriends.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                slFriends.finishRefresh(500);
                friendsPresenter.getContactList(String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 8)));
            }
        });
    }

    @Override
    protected void setUpData() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showContactList(List<UserGson> userGsons) {
        if (userGsons!=null){
            if (userGsons.size()==0){
                tvNodata.setVisibility(View.VISIBLE);
                ryConversation.setVisibility(View.GONE);
            }else {
                tvNodata.setVisibility(View.GONE);
                ryConversation.setVisibility(View.VISIBLE);
                ryConversation.setNestedScrollingEnabled(false);
                ryConversation.setLayoutManager(new LinearLayoutManager(getActivity()));
                ConversationAdapter adapter = new ConversationAdapter(userGsons);
                ryConversation.setAdapter(adapter);
            }
        }
    }


    private class ConversationAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public ConversationAdapter(@Nullable List<UserGson> data) {
            super(R.layout.conversationlist_ry__item, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final UserGson item) {
            RequestOptions requestOptions=new RequestOptions()
                    .error(R.mipmap.co);
            Glide.with(getActivity()).load(item.getHead()).apply(requestOptions).into((ImageView) helper.getView(R.id.iv_head));
            helper.setText(R.id.tv_username, item.getUsername())
                    .setOnClickListener(R.id.item_friends, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ConversationActivity.class);
                            intent.putExtra("tel", item.getTel());
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
                        }
                    });
            IMUtils.login(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("tel", ""), getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("password", ""), new BasicCallback() {
                @Override
                public void gotResult(int i, final String s) {
                    if (i == 0) {
                        Conversation singleConversation = Conversation.createSingleConversation(item.getTel());
                        Message latestMessage = singleConversation.getLatestMessage();
                        MessageContent content = latestMessage.getContent();
                        TextContent textContent = (TextContent) content;
                        helper.setText(R.id.tv_msg, textContent.getText());
                    } else {
                        ToastUtil.showToastError("获取消息列表出错");
                    }
                }
            });

        }
    }
}
