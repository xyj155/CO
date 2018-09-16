package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.contract.home.MatchUserContract;
import com.campus.appointment.contract.home.MyObserveContract;
import com.campus.appointment.gson.MatherPostGson;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.home.MatchUserPresenter;
import com.campus.appointment.presenter.home.MyObservePresenter;
import com.campus.appointment.weight.iosDialog.AlertDialog;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UserObserveActivity extends BaseActivity implements MyObserveContract.View, MatchUserContract.View {

    @InjectView(R.id.ry_observe)
    RecyclerView ryObserve;
    private MyObservePresenter myObservePresenter;

    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private ItemTouchHelper mItemTouchHelper;
    private MyObserveAdapter adapter;
    private MatchUserPresenter matchUserPresenter = new MatchUserPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_my_observe;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
        myObservePresenter = new MyObservePresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        myObservePresenter.getUserObservers("3");
        ryObserve.setLayoutManager(new LinearLayoutManager(UserObserveActivity.this));
    }

    @Override
    public void showUserObserves(List<UserGson> userGsons) {
        adapter = new MyObserveAdapter(userGsons);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(ryObserve);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        ryObserve.setAdapter(adapter);
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
    public void isObserve(boolean isObserve) {

    }

    @Override
    public void loadMatherUserPost(List<MatherPostGson> squareGsons) {

    }

    @Override
    public void isDeleteObserve(int code) {

    }

    private class MyObserveAdapter extends BaseItemDraggableAdapter<UserGson, BaseViewHolder> {

        public MyObserveAdapter(@Nullable List<UserGson> data) {
            super(R.layout.myobserve_ry_item, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, final UserGson userGson) {
            baseViewHolder.setText(R.id.tv_username, userGson.getUsername())
                    .setOnClickListener(R.id.tv_observer, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AlertDialog(UserObserveActivity.this).builder().setTitle("取消关注")
                                    .setMsg("是否取消关注: " + userGson.getUsername())
                                    .setNegativeButton("取消", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                        }
                                    }).setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    matchUserPresenter.setObserve(String.valueOf(userGson.getId()), "3", "0");
                                    adapter.remove(baseViewHolder.getPosition());
                                }
                            }).show();
                        }
                    });
            Glide.with(UserObserveActivity.this).load(userGson.getAvatar()).into((ImageView) baseViewHolder.getView(R.id.iv_head));
        }
    }
}
