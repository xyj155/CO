package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.SparseArray;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.Info;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.weight.CircleImageView;
import com.campus.appointment.weight.randa.RadarView;
import com.campus.appointment.weight.randa.RadarViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class FloatBottleActivity extends BaseActivity {


    @InjectView(R.id.tv_friend)
    TextView tvFriend;
    @InjectView(R.id.iv_head)
    CircleImageView ivHead;
    @InjectView(R.id.id_scan_circle)
    RadarView idScanCircle;
    @InjectView(R.id.radar)
    RadarViewGroup radar;
    private RadarView radarView;
    private SparseArray<Info> mDatas = new SparseArray<>();
    private RadarViewGroup radarViewGroup;
    private String[] mNames = {"ImmortalZ", "唐马儒", "王尼玛", "张全蛋", "蛋花", "王大锤", "叫兽", "哆啦A梦"};

    @Override
    public int intiLayout() {
        return R.layout.activity_float_bottle;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        setIsshowtitle(true);
        radarView = (RadarView) findViewById(R.id.id_scan_circle);
        radarViewGroup = (RadarViewGroup) findViewById(R.id.radar);
//        for (int i = 0; i < 10; i++) {
//            Info info = new Info();
//            info.setAge(((int) Math.random() * 25 + 16) + "岁");
//            info.setName(mNames[(int) (Math.random() * mNames.length)]);
//            info.setSex(i % 3 == 0 ? false : true);
//            info.setDistance(Math.round((Math.random() * 10) * 100) / 100);
//            mDatas.put(i, info);
//        }
        Glide.with(FloatBottleActivity.this).load("http://a.hiphotos.baidu.com/zhidao/pic/item/21a4462309f79052782f28490ff3d7ca7bcbd591.jpg").into(ivHead);
       radarView.startScan();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                radarViewGroup.setDatas(mDatas);
            }
        }, 1500);
    }

    @Override
    public void initData() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.iv_head)
    public void onViewClicked() {
    }
}
