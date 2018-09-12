package com.campus.appointment.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.campus.appointment.R;
import com.campus.appointment.adapter.TagGroupAdapter;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.HomePresenter;
import com.campus.appointment.ui.activity.FloatBottleActivity;
import com.campus.appointment.ui.activity.HomeSettingActivity;
import com.campus.appointment.ui.activity.MatchUserActivity;
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

public class HomeFragment extends BaseFragment implements HomeContract.View, AMapLocationListener {

    private static HomeFragment instance;
    @InjectView(R.id.tag_cloud)
    TagCloudView tagCloud;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.iv_setting)
    ImageView ivSetting;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.tv_macther)
    TextView flFloat;
    @InjectView(R.id.home)
    RelativeLayout home;
    //    @InjectView(R.id.rf_home)
//    SmartRefreshLayout rfHome;
    private HomePresenter presenter;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

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
//        rfHome.autoRefresh();
//        rfHome.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                rfHome.finishRefresh(1000);
//            }
//        });
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.inject(this, rootView);
        mlocationClient = new AMapLocationClient(getActivity());
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();

        return rootView;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                Log.i(TAG, "onLocationChanged: " + amapLocation.getLatitude() + "------" + amapLocation.getLongitude());
                SharedPreferences.Editor editor=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                editor.putString("location",amapLocation.getCity());
                editor.apply();
                presenter.queryAroundByGEO("3", amapLocation.getCity(), String.valueOf(amapLocation.getLatitude()), String.valueOf(amapLocation.getLongitude()));
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
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
        Log.i(TAG, "showTags: " + groupGsons);
        for (int i = 0; i < list.getData().size(); i++) {
            UserGson gson = new UserGson();
            gson.setAvatar(list.getData().get(i).getAvatar());
            Log.i(TAG, "showTags: "+list.getData().get(i).getAvatar());
            gson.setUsername(list.getData().get(i).getUsername());
            Log.i(TAG, "showTags: " + list.getData().get(i).getUsername());
            groupGsons.add(gson);
        }
        TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, getActivity());
        adapter.setOnItemClickListner(new TagGroupAdapter.OnClickInterface() {
            @Override
            public void OnItemClickListener() {
                starActivity(MatchUserActivity.class);
            }
        });
        tagCloud.setAdapter(adapter);
        tagCloud.run();
    }

    @OnClick({R.id.iv_setting,R.id.tv_macther   })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                starActivity(HomeSettingActivity.class);
                break;
            case R.id.tv_macther:
                starActivity(FloatBottleActivity.class);
                break;
        }
    }
}
