package com.campus.appointment.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.login.UserRegisterInformationContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.login.UserRegisterInformationPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/17/017.
 */

public class TelUserLoginByPassword extends BaseActivity implements UserRegisterInformationContract.View {
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.btn_register)
    Button btnRegister;
    private UserRegisterInformationPresenter presenter = new UserRegisterInformationPresenter(this);

    @Override
    public int intiLayout() {
        return R.layout.activity_tel_login_by_password;
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
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        presenter.loginByUsername(getSharedPreferences("user", MODE_PRIVATE).getString("tel", ""), etPassword.getText().toString());
    }

    @Override
    public void registerSuccesss(BaseGson<EmptyGson> baseGson) {

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
    public void loginByUsername(UserGson userGson) {
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editor.putString("username", userGson.getUsername());
        editor.putString("password", userGson.getPassword());
        editor.putString("tel", userGson.getTel());
        editor.putString("head", userGson.getHead());
        editor.putInt("age", userGson.getAge());
        editor.putString("city", userGson.getCity());
        editor.putString("latitude", userGson.getLatitude());
        editor.putString("longitude", userGson.getLongitude());
        editor.putString("sign", userGson.getSign());
        editor.putString("tag", userGson.getTag());
        editor.putBoolean("login", true);
        editor.putInt("id", userGson.getId());
        editor.apply();
        starActivity(SplashActivity.class);
        finish();
        SplashActivity.instance.finish();
    }
}
