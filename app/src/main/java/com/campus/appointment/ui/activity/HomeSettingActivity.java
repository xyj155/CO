package com.campus.appointment.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.jaygoo.widget.RangeSeekBar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class HomeSettingActivity extends BaseActivity {


    @InjectView(R.id.tv_adjustment)
    TextView tvAdjustment;
    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.tv_distance)
    TextView tvDistance;
    @InjectView(R.id.seekbar_distance)
    RangeSeekBar seekbarDistance;
    @InjectView(R.id.tv_age)
    TextView tvAge;
    @InjectView(R.id.seekbar_age)
    RangeSeekBar seekbarAge;
    @InjectView(R.id.tv_sex)
    TextView tvSex;
    @InjectView(R.id.rb_boy)
    RadioButton rbBoy;
    @InjectView(R.id.rb_girl)
    RadioButton rbGirl;
    @InjectView(R.id.rb_normal)
    RadioButton rbNormal;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;

    @Override
    public int intiLayout() {
        return R.layout.activity_home_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        setIsshowtitle(true);
        rbNormal.setChecked(true);
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_boy:
                        break;
                    case R.id.rb_girl:
                        break;
                    case R.id.rb_normal:
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick({R.id.tv_city, R.id.seekbar_distance, R.id.seekbar_age, R.id.rb_boy, R.id.rb_girl, R.id.rb_normal, R.id.rg_sex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                break;
            case R.id.seekbar_distance:
                break;
            case R.id.seekbar_age:
                break;
            case R.id.rb_boy:
                break;
            case R.id.rb_girl:
                break;
            case R.id.rb_normal:
                break;
            case R.id.rg_sex:
                break;
        }
    }
}
