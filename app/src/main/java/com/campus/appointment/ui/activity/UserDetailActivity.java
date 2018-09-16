package com.campus.appointment.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.contract.home.UserDetailContract;
import com.campus.appointment.gson.UserInfromationGson;
import com.campus.appointment.presenter.home.UserDetailPresenter;
import com.campus.appointment.weight.CircleImageView;
import com.campus.appointment.weight.tag.Tag;
import com.campus.appointment.weight.tag.TagView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserDetailActivity extends BaseActivity implements UserDetailContract.View {

    @InjectView(R.id.circleImageView3)
    CircleImageView circleImageView3;
    @InjectView(R.id.tag_group)
    TagView tagGroup;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_age_location)
    TextView tvAgeLocation;
    @InjectView(R.id.tv_active)
    TextView tvActive;
    @InjectView(R.id.tv_observer)
    TextView tvObserver;
    @InjectView(R.id.tv_fans)
    TextView tvFans;
    @InjectView(R.id.ry_piclist)
    RecyclerView ryPiclist;
    @InjectView(R.id.tv_sign)
    TextView tvSign;
    private UserDetailPresenter userDetailPresenter = new UserDetailPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        setIsshowtitle(true);
        ryPiclist.setNestedScrollingEnabled(false);

    }

    @Override
    public void initData() {
        userDetailPresenter.querySingleUser("3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    public void loadUserinfor(UserInfromationGson getSingleUser) {
        List<Tag> tags = new ArrayList<>();
        String tag = getSingleUser.getTag();
        Glide.with(UserDetailActivity.this).load(getSingleUser.getAvatar()).into(circleImageView3);
        tvUsername.setText(getSingleUser.getUsername());
        tvSign.setText(getSingleUser.getSign());
        tvAgeLocation.setText(getSingleUser.getAge() + " 岁  " + getSingleUser.getCity());
        tvFans.setText("粉丝 ：" + getSingleUser.getUocount() + "");
        tvActive.setText("动态 ：" + getSingleUser.getPcount() + "");
        tvObserver.setText("关注 ：" + getSingleUser.getOcount() + "");
        String[] split = tag.split(",");
        for (int i = 0; i < split.length; i++) {
            tags.add(new Tag(split[i]));
        }
        tagGroup.addTags(tags);
        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
            }
        });
        SquareMsgAdapter adapter = new SquareMsgAdapter(getSingleUser.getPic());
        ryPiclist.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        ryPiclist.setAdapter(adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        ryPiclist.addItemDecoration(decoration);
    }
    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
    private class SquareMsgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SquareMsgAdapter(@Nullable List<String> data) {
            super(R.layout.square_gd_img_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = helper.getView(R.id.iv_pic_msg);
            Glide.with(UserDetailActivity.this).load(item).into(imageView);
        }
    }
}
