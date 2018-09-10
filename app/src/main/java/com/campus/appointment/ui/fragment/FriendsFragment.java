package com.campus.appointment.ui.fragment;

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
import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.contract.home.FriendsContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.FriendsPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.wave.MultiWaveHeader;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
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
    private FriendsPresenter friendsPresenter;

    public static synchronized FriendsFragment getInstance() {
        if (instance == null) {
            return new FriendsFragment();
        } else {
            return instance;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ButterKnife.inject(this, view);
        friendsPresenter = new FriendsPresenter(this);

        slFriends.autoRefresh();
        slFriends.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                slFriends.finishRefresh(1500);
                friendsPresenter.getContactList("3");
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
        ryConversation.setNestedScrollingEnabled(false);
        ryConversation.setLayoutManager(new LinearLayoutManager(getActivity()));
        ConversationAdapter adapter = new ConversationAdapter(userGsons);
        ryConversation.setAdapter(adapter);
    }


    private class ConversationAdapter extends BaseQuickAdapter<UserGson, BaseViewHolder> {

        public ConversationAdapter(@Nullable List<UserGson> data) {
            super(R.layout.conversationlist_ry__item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserGson item) {
            helper.setText(R.id.tv_username, item.getUsername())
                    .setText(R.id.tv_msg, item.getTel());
            Glide.with(getActivity()).load(item.getHead()).into((ImageView) helper.getView(R.id.iv_head));
        }
    }
}
