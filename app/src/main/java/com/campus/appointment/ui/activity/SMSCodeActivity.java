package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.weight.VerificationCodeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SMSCodeActivity extends BaseActivity {


    @InjectView(R.id.tv_adjustment)
    TextView tvAdjustment;
    @InjectView(R.id.et_sms)
    EditText etTel;

    @InjectView(R.id.btn_next)
    Button btnNext;
    @InjectView(R.id.tv_send)
    VerificationCodeView tvSend;

    @Override
    public int intiLayout() {
        return R.layout.activity_smscode;
    }

    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            ToastUtil.showToastSuccess("发送验证码成功");
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showToastError("发送验证码错误！");
                        }
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            startActivity(new Intent(SMSCodeActivity.this, HomeActivity.class));
                        } else {
                            // TODO 处理错误的结果
                            ToastUtil.showToastError("验证码错误！");
                        }
                    }
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        tvSend.start(SMSCodeActivity.this, 60);
        tvSend.setOnCountDownListener(new VerificationCodeView.Countdown() {
            @Override
            public boolean beforeStart() {
                tvSend.start(SMSCodeActivity.this, 60);
                SharedPreferences editor = getSharedPreferences("tel", MODE_PRIVATE);
                SMSSDK.getVerificationCode("86", editor.getString("tel", ""));
                return true;
            }

            @Override
            public void timeCountdown(int time) {
                tvSend.setText(time + "s");
                tvSend.setClickable(false);
            }

            @Override
            public void stop() {
                tvSend.setClickable(true);
                tvSend.setText("重新获取验证码");
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        SMSSDK.registerEventHandler(eventHandler);
        SharedPreferences.Editor editor = getSharedPreferences("tel", MODE_PRIVATE).edit();
        editor.putString("tel", getIntent().getStringExtra("tel"));
        editor.apply();
        SMSSDK.getVerificationCode("86", getIntent().getStringExtra("tel"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                SMSSDK.submitVerificationCode("86", getIntent().getStringExtra("tel"), etTel.getText().toString());
                break;
        }
    }
}
