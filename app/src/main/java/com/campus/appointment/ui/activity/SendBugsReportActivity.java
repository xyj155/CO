package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.contract.user.UserContract;
import com.campus.appointment.presenter.user.UserPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SendBugsReportActivity extends BaseActivity implements UserContract.View {


    @InjectView(R.id.et_content)
    EditText etContent;
    @InjectView(R.id.btn_submit)
    Button btnSubmit;
    private UserPresenter userPresenter;

    @Override
    public int intiLayout() {

        return R.layout.activity_send_bugs_report;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        userPresenter = new UserPresenter(this);
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

    @OnClick({R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                userPresenter.sendBugs("3",etContent.getText().toString());
                break;
        }
        }

    @Override
    public void sendBugs() {
        finish();
    }
}
