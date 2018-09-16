package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.MatchUserContract;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.presenter.home.MatchUserPresenter;
import com.campus.appointment.util.TimeUtil;
import com.campus.appointment.weight.CircleImageView;
import com.campus.appointment.weight.UnderLineLinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.wave.MultiWaveHeader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MatchUserActivity extends BaseActivity implements MatchUserContract.View {


    @InjectView(R.id.iv_user_head)
    CircleImageView ivUserHead;
    @InjectView(R.id.iv_matcher_head)
    CircleImageView ivMatcherHead;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.ns_user)
    NestedScrollView nsUser;
    @InjectView(R.id.ll_bottom_button)
    LinearLayout llBottomButton;
    @InjectView(R.id.ul_user)
    UnderLineLinearLayout ulUser;
    @InjectView(R.id.tv_observer)
    TextView rbObserver;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_age_location)
    TextView tvAgeLocation;
    @InjectView(R.id.tv_no_infor)
    TextView tvNoInfor;
    @InjectView(R.id.iv_sex)
    ImageView ivSex;
    @InjectView(R.id.tv_chat)
    TextView tvChat;
    private boolean isShow = true;
    private UnderLineLinearLayout mUnderLineLinearLayout;
    private MatchUserPresenter matchUserPresenter;
    boolean isObserve = false;


    @Override
    public int intiLayout() {
        return R.layout.activity_match_user;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        setIsshowtitle(true);
        matchUserPresenter = new MatchUserPresenter(this);
        String tel = getIntent().getStringExtra("tel");
        SharedPreferences.Editor editor=getSharedPreferences("matcher",MODE_PRIVATE).edit();
        editor.putString("tel",tel);
        editor.apply();
        Log.i(TAG, "initView: "+getIntent().getStringExtra("tel"));
    }

    @Override
    public void initData() {
        mUnderLineLinearLayout = findViewById(R.id.ul_user);
        mUnderLineLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mUnderLineLinearLayout.setLineGravity(UnderLineLinearLayout.GRAVITY_LEFT);
        mUnderLineLinearLayout.setLineMarginSide(80);
        nsUser.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //上滑 并且 正在显示底部栏
                if (scrollY - oldScrollY > 0 && isShow) {
                    isShow = false;
                    llBottomButton.animate().translationY(llBottomButton.getHeight());
                } else if (scrollY - oldScrollY < 0 && !isShow) {
                    isShow = true;
                    llBottomButton.animate().translationY(0);
                }
            }
        });
        matchUserPresenter.getMatherUserPost(String.valueOf(getIntent().getIntExtra("id", 3)), "3");
        Glide.with(MatchUserActivity.this).load(getIntent().getStringExtra("url")).into(ivUserHead);
        tvUsername.setText(getIntent().getStringExtra("username"));
        tvAgeLocation.setText(getIntent().getIntExtra("age", 0) + "  岁  " + getIntent().getStringExtra("location"));
        if (getIntent().getIntExtra("sex", 2) == 0) {
            Glide.with(MatchUserActivity.this).load(R.mipmap.square_sex_girl).into(ivSex);
        } else {
            Glide.with(MatchUserActivity.this).load(R.mipmap.square_sex_boy).into(ivSex);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

//        RequestOptions options = new RequestOptions()
//                .bitmapTransform(new BlurTransformation(5, 10))
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(this).load(R.drawable.bg).apply(options).into(ivBg);
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
    public void isObserve(boolean isObserves) {
        if (isObserves) {
            isObserve = false;
            rbObserver.setBackgroundResource(R.drawable.home_tv_radius_yellow_30dp);
            rbObserver.setText("已关注");
        } else {
            isObserve = true;
            rbObserver.setBackgroundResource(R.drawable.home_tv_radius_blue_30dp);
            rbObserver.setText("关注");
        }
    }

    @Override
    public void loadMatherUserPost(List<MatherPostGson> squareGsons) {
        if (squareGsons.size() == 0) {
            tvNoInfor.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < squareGsons.size(); i++) {
                View v = LayoutInflater.from(this).inflate(R.layout.observe_user_dynatic_list_item, mUnderLineLinearLayout, false);
                ((TextView) v.findViewById(R.id.tv_action)).setText(squareGsons.get(i).getTitle());
                String writetime = squareGsons.get(i).getWritetime();
                long l = TimeUtil.dateToTimestamp(writetime);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
                Date date = new Date(l);
                String format = simpleDateFormat.format(date);
                ((TextView) v.findViewById(R.id.tv_action_time)).setText(format);
                RecyclerView recyclerView = v.findViewById(R.id.ry_user_mather);
                recyclerView.setLayoutManager(new GridLayoutManager(MatchUserActivity.this, 3));
                recyclerView.setNestedScrollingEnabled(false);
                if (squareGsons.get(i).getPic() != null) {
                    SquareMsgAdapter adapter = new SquareMsgAdapter(squareGsons.get(i).getPic());
                    recyclerView.setAdapter(adapter);
                }
                mUnderLineLinearLayout.addView(v);
            }
        }

    }

    @Override
    public void isDeleteObserve(int code) {
        if (code == 200) {
            ToastUtil.showToastSuccess("关注成功");
        } else {
            ToastUtil.showToastWarning("取消关注成功");
        }
    }

    @OnClick({R.id.tv_observer, R.id.tv_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_observer:
                if (isObserve) {
                    matchUserPresenter.setObserve(String.valueOf(getIntent().getIntExtra("id", 0)), "3", "1");
                    rbObserver.setBackgroundResource(R.drawable.home_tv_radius_yellow_30dp);
                    rbObserver.setText("已关注");
                    isObserve = false;
                } else {

                    matchUserPresenter.setObserve(String.valueOf(getIntent().getIntExtra("id", 0)), "3", "0");
                    rbObserver.setBackgroundResource(R.drawable.home_tv_radius_blue_30dp);
                    rbObserver.setText("关注");
                    isObserve = true;
                }
                break;
            case R.id.tv_chat:
                Intent intent = new Intent(MatchUserActivity.this, ConversationActivity.class);
                intent.putExtra("tel", getSharedPreferences("matcher",MODE_PRIVATE).getString("tel",""));
                startActivity(intent);
                overridePendingTransition(R.anim.activity_zoom_in, R.anim.activity_zoom_out);
                break;
        }
    }

    private class SquareMsgAdapter extends BaseQuickAdapter<MatherPostGson.Pic, BaseViewHolder> {

        public SquareMsgAdapter(@Nullable List<MatherPostGson.Pic> data) {
            super(R.layout.square_gd_img_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MatherPostGson.Pic item) {
            ImageView imageView = helper.getView(R.id.iv_pic_msg);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .circleCrop()//设置圆形
                    .error(R.mipmap.co)
                    .bitmapTransform(new RoundedCornersTransformation(12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            System.out.println(item);
            Glide.with(MatchUserActivity.this).load(item.getPic())
                    .transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imageView);
        }
    }
}
