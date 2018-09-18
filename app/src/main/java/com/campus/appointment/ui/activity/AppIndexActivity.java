package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.login.AppIndexContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.login.AppIndexPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class AppIndexActivity extends BaseActivity implements AppIndexContract.View {


    @InjectView(R.id.btn_qq_login)
    TextView btnQqLogin;
    @InjectView(R.id.btn_tel_login)
    TextView btnTelLogin;
    @InjectView(R.id.index_banner)
    ViewPager indexBanner;
    private AppIndexPresenter presenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_app_index;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
        ButterKnife.inject(this);
        presenter = new AppIndexPresenter(this);
        View inflate1 = getLayoutInflater().inflate(R.layout.guidance_item_1, null);
        View inflate2 = getLayoutInflater().inflate(R.layout.guidance_item_2, null);
        View inflate3 = getLayoutInflater().inflate(R.layout.guidance_item_3, null);
        List<View> list = new ArrayList<>();
        list.add(inflate1);
        list.add(inflate2);
        list.add(inflate3);
        IndexAdapter adapter = new IndexAdapter(list);
        indexBanner.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.btn_qq_login, R.id.btn_tel_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_qq_login:
                showmDialog("请求中...");
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        hidemDialog();
                    }
                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        hidemDialog();
                        Looper.prepare();
                        Log.i(TAG, "onComplete: " + arg0.getDb().getUserId());
                        presenter.loginWithQQ(arg0.getDb().getUserId());
                        SharedPreferences.Editor editor=getSharedPreferences("user",MODE_PRIVATE).edit();
                        editor.putString("username",arg0.getDb().getUserName());
                        editor.putBoolean("login",true);
                        Log.i(TAG, "onComplete: "+arg0.getDb().getUserName());
                        Log.i(TAG, "onComplete: "+arg0.getDb().getUserIcon());
                        editor.putString("head",arg0.getDb().getUserIcon());
                        editor.apply();
                        Looper.loop();

                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub
                        hidemDialog();
                    }
                });
                qq.showUser(null);
                qq.removeAccount(true);
                break;
            case R.id.btn_tel_login:
                starActivity(TelLoginActivity.class);
                break;
        }
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
    public void loginWithQQ(BaseGson<UserGson> list) {
        if (list.isSuccess()) {
            startActivity(new Intent(AppIndexActivity.this, HomeActivity.class));
        } else {
            startActivity(new Intent(AppIndexActivity.this, TelLoginActivity.class));
        }
    }


    private class IndexAdapter extends PagerAdapter {
        private List<View> list;

        public IndexAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = list.get(position);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
