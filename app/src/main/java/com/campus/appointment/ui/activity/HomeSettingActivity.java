package com.campus.appointment.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.jaygoo.widget.OnRangeChangedListener;
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
        SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);
        seekbarAge.setValue( 18,40);
        if (sp.getAll() != null) {
            int km = sp.getInt("km", 0);
            int age1 = sp.getInt("age1", 0);
            int age2 = sp.getInt("age2", 0);
            Log.i(TAG, "initView: " + km + " --- " + age1 + "----" + age2);
            seekbarDistance.setValue(km);
            if (age2-age1>0){
                seekbarAge.setValue( age1,age2);
                tvAge.setText(age1 + " - " + age2 + " 岁");
            }
            tvDistance.setText(km + " 公里");
            setIsshowtitle(true);
            Log.i(TAG, "initView: "+sp.getInt("sex",8));
            switch (sp.getInt("sex",8)){
                case 0:
                    rbGirl.setChecked(true);
                    break;
                case 1:
                    rbBoy.setChecked(true);
                    break;
                case 2:
                    rbNormal.setChecked(true);
                    break;
            }
        }else {
            rbNormal.setChecked(true);
        }




    }

    @Override
    public void initData() {
        //范围选择
        final SharedPreferences.Editor sp = getSharedPreferences("setting", MODE_PRIVATE).edit();
        seekbarDistance.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar rangeSeekBar, float v, float v1, boolean b) {
                tvDistance.setText((Float.valueOf(v)).intValue() + "  公里");
                sp.putInt("km", (Float.valueOf(v)).intValue());
                sp.apply();
                Log.i(TAG, "onRangeChanged: " + (Float.valueOf(v)).intValue());
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean b) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean b) {

            }
        });
        //年龄选择
        seekbarAge.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar rangeSeekBar, float v, float v1, boolean b) {
                int sourceI = (Float.valueOf(v)).intValue();
                int sourceII = (Float.valueOf(v1)).intValue();
                tvAge.setText(sourceI + " - " + sourceII + "  岁");
                Log.i(TAG, "initData: " + sourceI + "---------" + sourceII);
                sp.putInt("age1", sourceI);
                sp.putInt("age2", sourceII);
                sp.apply();
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean b) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean b) {

            }
        });
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_boy:
                        sp.putInt("sex",1);
                        break;
                    case R.id.rb_girl:
                        sp.putInt("sex",0);
                        break;
                    case R.id.rb_normal:
                        sp.putInt("sex",2);
                        break;
                }
                sp.apply();
            }
        });
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
