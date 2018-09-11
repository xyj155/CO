package com.campus.appointment.ui.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.adapter.SquareAdapter;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.SquareContract;
import com.campus.appointment.entity.SquareEntity;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.presenter.home.SquarePresenter;
import com.campus.appointment.ui.activity.EditPostActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.droidsonroids.gif.GifImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class SquareFragment extends BaseFragment implements SquareContract.View {
    private static SquareFragment instance;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.tv_square)
    TextView tvSquare;
    @InjectView(R.id.gifView)
    GifImageView gifView;
    @InjectView(R.id.ry_square)
    RecyclerView rySquare;
    @InjectView(R.id.friend)
    RelativeLayout friend;
    @InjectView(R.id.sl_square)
    SmartRefreshLayout slSquare;
    @InjectView(R.id.fb_square)
    FloatingActionButton fbSquare;
    private int distance;
    private boolean visible = true;
    private SquarePresenter squarePresenter;

    public static synchronized SquareFragment getInstance() {
        if (instance == null) {
            return new SquareFragment();
        } else {
            return instance;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_square;
    }

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ButterKnife.inject(this, view);
        squarePresenter = new SquarePresenter(this);
        rySquare.setLayoutManager(new LinearLayoutManager(getActivity()));
        slSquare.autoRefresh();
        slSquare.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                squarePresenter.squareUserActive("3");
                slSquare.finishRefresh(1500);
            }
        });
        rySquare.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (distance < -ViewConfiguration.getTouchSlop() && !visible) {
                    //显示fab
                    //iv_go_uploading.setVisibility(View.VISIBLE);
                    showFABAnimation(fbSquare);
                    distance = 0;
                    visible = true;
                } else if (distance > ViewConfiguration.getTouchSlop() && visible) {
                    //隐藏
                    //iv_go_uploading.setVisibility(View.GONE);
                    hideFABAnimation(fbSquare);
                    distance = 0;
                    visible = false;
                }
                if ((dy > 0 && visible) || (dy < 0 && !visible))//向下滑并且可见  或者  向上滑并且不可见
                    distance += dy;
            }

        });
    }

    public void showFABAnimation(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(400).start();
    }

    /**
     * by moos on 2017.8.21
     * func:隐藏fab的动画
     */

    public void hideFABAnimation(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 0f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 0f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(400).start();
    }


    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void squareUserActive(List<SquareGson> squareGsons) {
        Log.i(TAG, "squareUserActive: " + squareGsons);
        List<SquareEntity> list = new ArrayList<>();
        Log.i(TAG, "squareUserActive: " + squareGsons.size());
            for (int i = 0; i < squareGsons.size(); i++) {
                SquareGson squareGson = new SquareGson();
                squareGson.setTitle(squareGsons.get(i).getTitle());
                squareGson.setLocation(squareGsons.get(i).getLocation());
                squareGson.setUser(squareGsons.get(i).getUser());
                squareGson.setWritetime(squareGsons.get(i).getWritetime());
                if (squareGsons.get(i).getPic_size()>0){
                    squareGson.setPics(squareGsons.get(i).getPics());
                    squareGson.setPic_size(squareGsons.get(i).getPic_size());
                    list.add(new SquareEntity(2, squareGson));
                }else {
                    list.add(new SquareEntity(1, squareGson));
                }
            }
        SquareAdapter adapter = new SquareAdapter(list, getActivity());
        rySquare.setAdapter(adapter);
    }

    @Override
    public void sendReport(List<EmptyGson> squareGsons) {

    }

    @OnClick({R.id.fb_square})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fb_square:
                Intent intent=new Intent(getContext(),EditPostActivity.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:
                squarePresenter.squareUserActive("3");
                break;
        }
    }
}
