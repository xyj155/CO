package com.campus.appointment.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.campus.appointment.R;
import com.campus.appointment.adapter.TagGroupAdapter;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.HomePresenter;
import com.campus.appointment.ui.activity.HomeSettingActivity;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static HomeFragment instance;
    @InjectView(R.id.tag_cloud)
    TagCloudView tagCloud;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.iv_setting)
    ImageView ivSetting;
    private HomePresenter presenter;

    public static synchronized HomeFragment getInstance() {
        if (instance == null) {
            return new HomeFragment();
        } else {
            return instance;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ButterKnife.inject(this, view);
        tagCloud = view.findViewById(R.id.tag_cloud);
        presenter = new HomePresenter(this);
    }

    @Override
    protected void setUpData() {
        presenter.queryAroundByGEO("3", "嘉兴", "31.253411", "121.518998");
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

    private static final String TAG = "HomeFragment";

    @Override
    public void showTags(BaseGson<UserGson> list) {
        List<UserGson> groupGsons = new ArrayList<>();
        for (int i = 0; i < list.getData().size(); i++) {
            UserGson gson = new UserGson();
            gson.setHead(list.getData().get(i).getHead());
            gson.setUsername(list.getData().get(i).getUsername());
            Log.i(TAG, "showTags: " + list.getData().get(i).getUsername());
            groupGsons.add(gson);
        }
        TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, getActivity());
        tagCloud.setAdapter(adapter);
        tagCloud.run();
    }

    @OnClick({R.id.iv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_setting:
                Intent intent1 = new Intent(getContext(), HomeSettingActivity.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.activity_zoom_in,R.anim.activity_zoom_out);
                break;
        }
    }
}
