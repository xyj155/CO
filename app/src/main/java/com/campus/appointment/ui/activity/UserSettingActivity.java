package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.util.DataCleanManager;
import com.campus.appointment.weight.iosDialog.AlertDialog;
import com.tencent.bugly.beta.Beta;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class UserSettingActivity extends BaseActivity {

    @InjectView(R.id.imageView2)
    ImageView imageView2;
    @InjectView(R.id.tv_sys_version)
    TextView tvSysVersion;
    @InjectView(R.id.tv_sys_update)
    TextView tvSysUpdate;
    @InjectView(R.id.tv_sys_cache)
    TextView tvSysCache;
    @InjectView(R.id.tv_login_out)
    TextView tvLoginOut;

    @Override
    public int intiLayout() {
        return R.layout.activity_user_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setIsshowtitle(true);
    }

    @Override
    public void initData() {
        ButterKnife.inject(this);
        getCacheSize();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.tv_sys_version, R.id.tv_sys_update, R.id.tv_sys_cache, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_out:
                new AlertDialog(UserSettingActivity.this)
                        .builder()
                        .setCancelable(false)
                        .setTitle("退出登陆")
                        .setMsg("    退出登陆后将清除你的所有登录信息，是否确认退出登录？")
                       .setNegativeButton("确定", new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent = new Intent();
                               intent.setAction("is_exit_app");
                               sendBroadcast(intent);
                               DataCleanManager.cleanAllApplicationData(getApplicationContext());
                               starActivity(AppIndexActivity.class);
                               finish();
                           }
                       })
                .setPositiveButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

                break;
            case R.id.tv_sys_version:

                break;
            case R.id.tv_sys_update:
                Beta.checkUpgrade();
                break;
            case R.id.tv_sys_cache:
                try {
                    DataCleanManager.cleanApplicationData(getApplicationContext());
                    tvSysCache.setText("清除缓存   0.00 KB");
                    ToastUtil.showToastSuccess("缓存清理成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void getCacheSize() {
        File file = new File(this.getCacheDir().getPath());
        try {
            tvSysCache.setText("清除缓存   " + DataCleanManager.getCacheSize(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
