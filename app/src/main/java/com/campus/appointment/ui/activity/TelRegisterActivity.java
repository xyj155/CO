package com.campus.appointment.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class TelRegisterActivity extends BaseActivity {
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_password_2)
    EditText etPassword2;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    public static TelRegisterActivity instance;

    @Override
    public int intiLayout() {
        return R.layout.activity_tel_register;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        instance=this;
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        if (etPassword.getText().toString().equals(etPassword2.getText().toString())) {
            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
            editor.putString("password", etPassword.getText().toString());
            editor.apply();
            starActivity(UserRegisterInformationActivity.class);
        } else {
            ToastUtil.showToastError("前后密码不一致");
        }

    }
}
