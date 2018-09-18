package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.SquareContract;
import com.campus.appointment.contract.home.UserPostDetailContract;
import com.campus.appointment.gson.PostDetail;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.presenter.home.SquarePresenter;
import com.campus.appointment.presenter.home.UserPostDetailPresenter;
import com.campus.appointment.util.TimeUtil;
import com.campus.appointment.weight.CircleImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import pl.droidsonroids.gif.GifImageView;

public class UserPostDetailActivity extends BaseActivity implements UserPostDetailContract.View, SquareContract.View {

    @InjectView(R.id.gifView)
    GifImageView gifView;
    @InjectView(R.id.tv_userhead)
    CircleImageView tvUserhead;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.tv_post_content)
    TextView tvPostContent;
    @InjectView(R.id.ry_post_detail)
    RecyclerView ryPostDetail;
    @InjectView(R.id.iv_comment)
    ImageView ivComment;
    @InjectView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @InjectView(R.id.rb_thumb)
    RadioButton rbThumb;
    @InjectView(R.id.tv_thumb_count)
    TextView tvThumbCount;
    @InjectView(R.id.tv_post_time)
    TextView tvPostTime;
    @InjectView(R.id.iv_report)
    ImageView ivReport;
    @InjectView(R.id.ry_comment)
    RecyclerView ryComment;
    @InjectView(R.id.iv_vip)
    ImageView ivVip;
    @InjectView(R.id.rl_detail)
    SmartRefreshLayout rlDetail;
    @InjectView(R.id.tv_comment)
    TextView tvComment;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.sl_post_detail)
    NestedScrollView slPostDetail;
    @InjectView(R.id.et_comment)
    EditText etComment;
    @InjectView(R.id.btn_send)
    TextView btnSend;
    private UserPostDetailPresenter postDetailPresenter;
    private SquarePresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_post_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        slPostDetail.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    tvTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));//AGB由相关工具获得，或者美工提供
                    tvTitle.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    Log.e("111", "y <= 0");
                } else if (y > 0 && y <= 50) {
                    float scale = (float) y / 100;
                    float alpha = (255 * scale);
                    // 只是layout背景透明(仿知乎滑动效果)白色透明
                    tvTitle.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    //                          设置文字颜色，黑色，加透明度
                    tvTitle.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                    Log.e("111", "y > 0 && y <= imageHeight");
                } else {
//							白色不透明
                    tvTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
                    //                          设置文字颜色
                    //黑色
                    tvTitle.setTextColor(Color.argb((int) 255, 0, 0, 0));
                    Log.e("111", "else");
                }
            }
        });
        rlDetail.autoRefresh();
        ryComment.setNestedScrollingEnabled(false);
        rlDetail.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(UserPostDetailActivity.this, 3);
                ryPostDetail.setLayoutManager(gridLayoutManager);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserPostDetailActivity.this);
                ryComment.setLayoutManager(linearLayoutManager);
                refreshLayout.finishRefresh(300);
            }
        });

    }

    @Override
    public void initData() {


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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIsshowtitle(false);
        postDetailPresenter = new UserPostDetailPresenter(this);
        presenter = new SquarePresenter(this);
        SharedPreferences.Editor sp = getSharedPreferences("comment", MODE_PRIVATE).edit();
        sp.putString("uid", "3");
        sp.putString("pid", getIntent().getStringExtra("id"));
        sp.putInt("item", getIntent().getIntExtra("item", 8));
        sp.apply();
        postDetailPresenter.querySinglePost(String.valueOf(
                getIntent().getIntExtra("puid", 0)),
                String.valueOf(getIntent().getStringExtra("id")),
                String.valueOf(getIntent().getStringExtra("uid")));
    }

    @Override
    public void querySinglePost(List<PostDetail> list) {
        if (list != null) {
            rlDetail.setVisibility(View.VISIBLE);
            tvComment.setText("所有评论( " + list.get(0).getCommentcount() + " )");
            tvCommentCount.setText(list.get(0).getCommentcount() + "");
            tvThumbCount.setText(list.get(0).getThumb() + "");
            tvPostTime.setText("发表于：" + list.get(0).getUser().getCity());
            tvPostContent.setText(list.get(0).getPost().getTitle());
            Glide.with(UserPostDetailActivity.this).asBitmap().load(list.get(0).getUser().getAvatar()).into(tvUserhead);
            tvUsername.setText(list.get(0).getUser().getUsername());
            if (list.get(0).getUser().getIs_vip() == 1) {
                ivVip.setVisibility(View.VISIBLE);
            }
            Log.i(TAG, "querySinglePost: " + list.get(0).isLike());
            if (list.get(0).isLike()) {
                rbThumb.setChecked(true);
            } else {
                rbThumb.setChecked(false);
                rbThumb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            presenter.updateThumb("3", getSharedPreferences("comment", MODE_PRIVATE).getString("pid", ""));
                            Intent intent = new Intent();
                            intent.setAction("com.campus.appointment.broadcast.RY_BROADCAST");
                            intent.putExtra("item", getSharedPreferences("comment", MODE_PRIVATE).getInt("item", 0));
                            sendBroadcast(intent);
                        }
                    }
                });
            }
            SquarePicsAdapter squareMsgAdapter = new SquarePicsAdapter(list.get(0).getPic());
            ryPostDetail.setAdapter(squareMsgAdapter);
            CommentAdapter commentAdapter = new CommentAdapter(list.get(0).getComment());
            ryComment.setAdapter(commentAdapter);
        }
    }


    @Override
    public void postComment(List<EmptyGson> list) {
        etComment.setText("");
        postDetailPresenter.querySinglePost(String.valueOf(
                getIntent().getIntExtra("puid", 0)),
                String.valueOf(getIntent().getStringExtra("id")),
                String.valueOf(getIntent().getStringExtra("uid")));
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        postDetailPresenter.postComment("3", etComment.getText().toString(), getSharedPreferences("comment", MODE_PRIVATE).getString("pid", ""));
    }

    @Override
    public void squareUserActive(List<SquareGson> squareGsons) {

    }

    @Override
    public void sendReport(List<EmptyGson> squareGsons) {

    }

    @Override
    public void updateThumb(List<EmptyGson> squareGsons) {
        tvThumbCount.setText(String.valueOf(Integer.valueOf(tvThumbCount.getText().toString()) + 1));

    }


    private class CommentAdapter extends BaseQuickAdapter<PostDetail.CommentBean, BaseViewHolder> {

        public CommentAdapter(@Nullable List<PostDetail.CommentBean> data) {
            super(R.layout.post_detail_user_comment_ry, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PostDetail.CommentBean item) {
            Date date = new Date(TimeUtil.dateToTimestamp(item.getTime()));
            SimpleDateFormat time = new SimpleDateFormat("hh:MM");
            String format1 = time.format(date);
            helper.setText(R.id.tv_username, item.getUser().getUsername())
                    .setText(R.id.tv_comment, item.getComment())
                    .setText(R.id.tv_time, format1);
            Log.i(TAG, "convert: " + item.getUser().getAvatar());
            Glide.with(UserPostDetailActivity.this).load(item.getUser().getAvatar()).into((ImageView) helper.getView(R.id.iv_avatar));
        }
    }

    private class SquarePicsAdapter extends BaseQuickAdapter<PostDetail.PicBean, BaseViewHolder> {

        public SquarePicsAdapter(@Nullable List<PostDetail.PicBean> data) {
            super(R.layout.square_gd_img_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PostDetail.PicBean item) {
            ImageView imageView = helper.getView(R.id.iv_pic_msg);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .circleCrop()//设置圆形
                    .error(R.mipmap.co)
                    .bitmapTransform(new RoundedCornersTransformation(12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(UserPostDetailActivity.this).load(item.getPic())
                    .transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imageView);
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }
}
