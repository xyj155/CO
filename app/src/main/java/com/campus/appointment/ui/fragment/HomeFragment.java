package com.campus.appointment.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.campus.appointment.R;
import com.campus.appointment.TagGroupGson;
import com.campus.appointment.adapter.TagGroupAdapter;
import com.campus.appointment.base.BaseFragment;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class HomeFragment extends BaseFragment {

    private static HomeFragment instance;
    @InjectView(R.id.tag_cloud)
    TagCloudView tagCloud;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.home)
    FrameLayout home;

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
        List<TagGroupGson> groupGsons = new ArrayList<>();
        groupGsons.add(new TagGroupGson("徐易杰", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=26&gp=0.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536150597108&di=ebc0c710aaca0c1c5dc6f12084bbf126&imgtype=jpg&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D1934952861%2C3453579486%26fm%3D214%26gp%3D0.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565444&di=825251c0aedf41fa36dbe0e6a9ebab80&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F21a4462309f79052782f28490ff3d7ca7bcbd591.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565443&di=c34cc41daea4b79c703f624a78e5ff43&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F58101403915021.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565443&di=deb7c9bac196c18c0bdb8fdbe4906edd&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201409%2F25%2F20140925100559_RviGZ.jpeg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565443&di=217917fa69d8ba2083167f5dbd01cc83&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201611%2F13%2F20161113110506_ScT45.thumb.700_0.jpeg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565443&di=0bd1eb28c1ab0397808f68201884de3c&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D3194e249b012c8fcb4f3f6c5cc0392b4%2Fdce3b718972bd40776e5439472899e510eb30984.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565442&di=6751d5fe6463fc524fcb5ea0b31e721c&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F0RSQ4G-9.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565442&di=c27646479ebc7661c197d57d523d380c&imgtype=0&src=http%3A%2F%2Fimgtu.5011.net%2Fuploads%2Fcontent%2F20170209%2F4934501486627131.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565442&di=9c232d7f6991985391b2dd6a9610e5c5&imgtype=0&src=http%3A%2F%2Fimg5q.duitang.com%2Fuploads%2Fitem%2F201410%2F18%2F20141018201220_UELnM.jpeg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565421&di=97883cc120f34d9d27b00485c43d7256&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3De78d32f2251f95caa6a09ab2fc275308%2Fb3b7d0a20cf431adbb6874b54b36acaf2fdd9887.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536147565421&di=bc020f87d039c2c119cfd332c2e749a3&imgtype=0&src=http%3A%2F%2Fwww.gexing.me%2Fuploads%2F150115%2F6-15011510464N41.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536150714987&di=7ed2af7a976e9fa8090cecaca094fe12&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201603%2F06%2F20160306124749_X5v8C.jpeg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536150714986&di=919ee6610160d3ff20d2cc1d007d0046&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F1103462438-3.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536150714985&di=5d8d0e9e55734664240fa5f9e6340d1e&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F011951J94-6.jpg"));
        groupGsons.add(new TagGroupGson("徐易杰", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536150714985&di=df44abfb766546d73797c41bacbc821c&imgtype=0&src=http%3A%2F%2Fpic1.58cdn.com.cn%2Fzhuanzh%2Fn_v1bkuyfvm25wjvpmbmijrq.png"));
        TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, getActivity());
        tagCloud.setAdapter(adapter);
        tagCloud.run();
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
