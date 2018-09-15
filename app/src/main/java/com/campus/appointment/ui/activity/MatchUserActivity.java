package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.weight.CircleImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class MatchUserActivity extends BaseActivity {


    @InjectView(R.id.iv_bg)
    ImageView ivBg;
    @InjectView(R.id.iv_user_head)
    CircleImageView ivUserHead;
    @InjectView(R.id.iv_matcher_head)
    CircleImageView ivMatcherHead;
    @InjectView(R.id.imageView)
    ImageView imageView;

    @Override
    public int intiLayout() {
        return R.layout.activity_match_user;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        setIsshowtitle(true);
        RequestOptions options = new RequestOptions()
                .bitmapTransform(new BlurTransformation(5, 10))
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this).load(R.drawable.bg).apply(options).into(ivBg);
    }
}
