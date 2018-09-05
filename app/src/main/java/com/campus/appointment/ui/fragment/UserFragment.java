package com.campus.appointment.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.ui.activity.UserSettingActivity;
import com.campus.appointment.weight.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class UserFragment extends BaseFragment {
    private static UserFragment instance;
    @InjectView(R.id.iv_head)
    CircleImageView ivHead;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.user_tv_user_infor)
    TextView userTvUserInfor;
    @InjectView(R.id.user_tv_user_infor_divider)
    View userTvUserInforDivider;
    @InjectView(R.id.user_tv_book)
    TextView userTvBook;
    @InjectView(R.id.user_tv_book_divider)
    View userTvBookDivider;
    @InjectView(R.id.user_tv_observer)
    TextView userTvObserver;
    @InjectView(R.id.user_tv_observer_divider)
    View userTvObserverDivider;
    @InjectView(R.id.user_tv_setting)
    TextView userTvSetting;
    @InjectView(R.id.user)
    ConstraintLayout user;

    public static synchronized UserFragment getInstance() {
        if (instance == null) {
            return new UserFragment();
        } else {
            return instance;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_user;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {

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

    @OnClick({R.id.iv_head, R.id.tv_username, R.id.user_tv_user_infor, R.id.user_tv_book, R.id.user_tv_observer, R.id.user_tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.tv_username:
                break;
            case R.id.user_tv_user_infor:
                break;
            case R.id.user_tv_book:
                break;
            case R.id.user_tv_observer:
                break;
            case R.id.user_tv_setting:
                starActivity(UserSettingActivity.class);
                break;
        }
    }
}
