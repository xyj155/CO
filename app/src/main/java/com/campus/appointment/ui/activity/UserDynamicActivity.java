package com.campus.appointment.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.adapter.SingleDynamicAdapter;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.contract.user.UserDynamicContract;
import com.campus.appointment.entity.SquareEntity;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.presenter.user.UserDynamicPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.droidsonroids.gif.GifImageView;

public class UserDynamicActivity extends BaseActivity implements UserDynamicContract.View {


    @InjectView(R.id.ry_single_user_dynamic)
    RecyclerView rySingleUserDynamic;
    @InjectView(R.id.gifView)
    GifImageView gifView;
    @InjectView(R.id.id_ll_ok)
    TextView idLlOk;
    @InjectView(R.id.sl_single)
    SmartRefreshLayout slSingle;
    @InjectView(R.id.tv_nodata)
    TextView tvNodata;
    private UserDynamicPresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_dynamic;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        setIsshowtitle(true);
        rySingleUserDynamic.setLayoutManager(new LinearLayoutManager(UserDynamicActivity.this));
    }

    @Override
    public void initData() {
        presenter = new UserDynamicPresenter(this);
        slSingle.autoRefresh();
        slSingle.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.querySingleUserPost(String.valueOf(getSharedPreferences("user", Context.MODE_PRIVATE).getInt("id", 8)));
                slSingle.finishRefresh(300);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @Override
    public void querySingleUserPost(List<SquareGson> squareGsons) {
        if (squareGsons.size()==0){
            tvNodata.setVisibility(View.VISIBLE);
            rySingleUserDynamic.setVisibility(View.GONE);
        }else {
            tvNodata.setVisibility(View.GONE);
            rySingleUserDynamic.setVisibility(View.VISIBLE);
            List<SquareEntity> list = new ArrayList<>();
            for (int i = 0; i < squareGsons.size(); i++) {
                SquareGson squareGson = new SquareGson();
                squareGson.setTitle(squareGsons.get(i).getTitle());
                squareGson.setLocation(squareGsons.get(i).getLocation());
                squareGson.setUser(squareGsons.get(i).getUser());
                squareGson.setWritetime(squareGsons.get(i).getWritetime());
                if (squareGsons.get(i).getPic_size() > 0) {
                    squareGson.setPics(squareGsons.get(i).getPics());
                    squareGson.setPic_size(squareGsons.get(i).getPic_size());
                    list.add(new SquareEntity(2, squareGson));
                } else {
                    list.add(new SquareEntity(1, squareGson));
                }
            }
            SingleDynamicAdapter adapter = new SingleDynamicAdapter(list, UserDynamicActivity.this);
            rySingleUserDynamic.setAdapter(adapter);
        }

    }
}
