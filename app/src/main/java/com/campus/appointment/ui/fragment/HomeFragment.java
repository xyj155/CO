package com.campus.appointment.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.HomeContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.HomePresenter;
import com.campus.appointment.ui.activity.HomeSettingActivity;
import com.campus.appointment.ui.activity.MatchUserActivity;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.scwang.wave.MultiWaveHeader;

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
    @InjectView(R.id.fb_flash)
    FloatingActionButton fbFlash;
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
        mLocationOption.setInterval(60000*10);
//        mLocationOption.setOnceLocation(true);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
        return rootView;
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                Log.i(TAG, "onLocationChanged: " + amapLocation.getLatitude() + "------" + amapLocation.getLongitude());
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
                editor.putString("location", amapLocation.getCity());
                editor.putString("latitude", String.valueOf(amapLocation.getLatitude()));
                editor.putString("longitude", String.valueOf(amapLocation.getLongitude()));
                editor.apply();
                presenter.updateLatin(String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)),
                        amapLocation.getCity(),
                        String.valueOf(amapLocation.getLatitude()),
                        String.valueOf(amapLocation.getLongitude()));
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
    private TagGroupAdapter adapter;

    @Override
    public void showTags(final BaseGson<UserGson> list) {
        adapter = new TagGroupAdapter(list.getData(), getActivity());
        adapter.setOnItemClickListner(new TagGroupAdapter.OnClickInterface() {
            @Override
            public void OnItemClickListener(int position) {
                Intent intent = new Intent(getActivity(), MatchUserActivity.class);
                intent.putExtra("url", list.getData().get(position).getHead());
                intent.putExtra("username", list.getData().get(position).getUsername());
                intent.putExtra("age", list.getData().get(position).getAge());
                intent.putExtra("tel", list.getData().get(position).getTel());
                intent.putExtra("id", list.getData().get(position).getId());
                Log.i(TAG, "OnItemClickListener: "+list.getData().get(position).getId());
                intent.putExtra("sex", list.getData().get(position).getSex());
                intent.putExtra("location", list.getData().get(position).getCity());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
            }
        });
        tagCloud.setAdapter(adapter);
        tagCloud.run();
    }

    @Override
    public void showDialog(String msg) {
        showmDialog(msg);
    }

    @Override
    public void hideDialog() {
        hidemDialog();
    }

    @Override
    public void showLatin(UserGson userGson) {
        if (userGson != null) {
            presenter.queryAroundByGEO(String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)),
                    Double.valueOf(userGson.getLatitude()),
                    Double.valueOf(userGson.getLongitude()));
        }
    }

    @OnClick({R.id.iv_setting, R.id.tv_macther, R.id.fb_flash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fb_flash:
                Log.i(TAG, "onViewClicked: " + String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)) + "----" +
                        getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("city", "") + "----" +
                        getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("latitude", "") + "----" +
                        getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("longitude", ""));
                presenter.queryAroundByGEO(String.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 0)),
                        Double.valueOf(getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("latitude", "")),
                        Double.valueOf( getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).getString("longitude", "")));
                break;
            case R.id.iv_setting:
                starActivity(HomeSettingActivity.class);
                break;
            case R.id.tv_macther:
                ToastUtil.showToastInfor("工程师在努力开发中...");
//                starActivity(FloatBottleActivity.class);
                break;
        }
    }
}
