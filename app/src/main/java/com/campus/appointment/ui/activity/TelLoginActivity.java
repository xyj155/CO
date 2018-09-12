package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TelLoginActivity extends BaseActivity {

    @InjectView(R.id.tv_adjustment)
    TextView tvAdjustment;
    @InjectView(R.id.et_tel)
    EditText etTel;
    @InjectView(R.id.btn_next)
    Button btnNext;

    @Override
    public int intiLayout() {
        return R.layout.activity_tel_login;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        setIsshowtitle(true);
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

    @OnClick({R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if (isPhoneNumber(etTel.getText().toString())){
                    Intent intent = new Intent(TelLoginActivity.this, SMSCodeActivity.class);
                    intent.putExtra("tel", etTel.getText().toString());
                    startActivity(intent);
                }else {
                    ToastUtil.showToastInfor("请输入正确手机号码！");
                }

                break;
        }
    }
    /**
     * 验证手机号码
     *
     * @param mobiles
     * @return
     */
    private boolean isPhoneNumber(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }


}
