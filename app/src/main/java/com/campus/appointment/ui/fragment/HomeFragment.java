package com.campus.appointment.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.campus.appointment.R;
import com.campus.appointment.adapter.TagGroupAdapter;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.gson.UserTest;
import com.campus.appointment.http.volley.VolleyRequestCllBack;
import com.campus.appointment.http.volley.VolleyRequestUtil;
import com.campus.appointment.presenter.home.HomePresenter;
import com.google.gson.Gson;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    private static HomeFragment instance;
    @InjectView(R.id.tag_cloud)
    TagCloudView tagCloud;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.home)
    FrameLayout home;
    private RequestQueue queen;
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
        Map<String,String> map=new HashMap<>();
        map.put("id","3");
        map.put("city","嘉兴");
        map.put("latitude","31.253411");
        map.put("longitude","121.518998");
        queen = Volley.newRequestQueue(getActivity());
        queen.add(VolleyRequestUtil.RequestWithParams("http://122.152.231.185/CO/public/index.php/index/Around/searchUserAround", map, new VolleyRequestCllBack() {
            @Override
            public void onSuccess(String result) {
                Log.i(TAG, "onSuccess: "+result);
                Gson gson=new Gson();
                UserTest userTest = gson.fromJson(result, UserTest.class);
//
                List<UserTest.DataBean> groupGsons = new ArrayList<>();
//                Log.i(TAG, "showTags: " + groupGsons.size());
                for (int i = 0; i < userTest.getData().size(); i++) {
                    UserTest.DataBean gson1 = new UserTest.DataBean();
                    gson1.setHead(userTest.getData().get(i).getHead());
                    gson1.setUsername(userTest.getData().get(i).getUsername());
                    groupGsons.add(gson1);
                }
                TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, getActivity());
                tagCloud.setAdapter(adapter);
                tagCloud.run();
            }

            @Override
            public void onError(String error) {

            }
        }));
        presenter.queryAroundByGEO("3", "嘉兴", "31.253411", "121.518998");
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

    private static final String TAG = "HomeFragment";

    @Override
    public void showTags(List<UserGson> list) {
//        List<UserGson> groupGsons = new ArrayList<>();
//        Log.i(TAG, "showTags: " + groupGsons.size());
//        for (int i = 0; i < list.size(); i++) {
//            UserGson gson = new UserGson();
//            gson.setHead(groupGsons.get(i).getHead());
//            gson.setUsername(groupGsons.get(i).getUsername());
//            groupGsons.add(gson);
//        }
//        TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, getActivity());
//        tagCloud.setAdapter(adapter);
//        tagCloud.run();
    }
}
