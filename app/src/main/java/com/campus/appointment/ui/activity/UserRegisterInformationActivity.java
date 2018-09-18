package com.campus.appointment.ui.activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.login.UserRegisterInformationContract;
import com.campus.appointment.gson.UserGson;
import com.campus.appointment.presenter.login.UserRegisterInformationPresenter;
import com.campus.appointment.util.TimeUtil;
import com.campus.appointment.weight.bottomCalada.AlertView;
import com.campus.appointment.weight.bottomCalada.OnConfirmeListener;
import com.campus.appointment.weight.tag.Tag;
import com.campus.appointment.weight.tag.TagView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserRegisterInformationActivity extends BaseActivity implements UserRegisterInformationContract.View, OnConfirmeListener {
    @InjectView(R.id.tag_group)
    TagView tagGroup;
    @InjectView(R.id.tag_group_check)
    TagView tagGroupCheck;
    @InjectView(R.id.rb_boy)
    RadioButton rbBoy;
    @InjectView(R.id.rb_girl)
    RadioButton rbGirl;
    @InjectView(R.id.rg_sex)
    RadioGroup rgSex;
    @InjectView(R.id.btn_submit)
    Button btnSubmit;
    @InjectView(R.id.tv_birthday)
    TextView tvBirthday;
    @InjectView(R.id.et_sign)
    EditText etSign;
    @InjectView(R.id.et_username)
    EditText etUsername;
    private String color[] = {"#836FFF", "#7EC0EE", "#CD0000", "#E9967A", "#EE9A00", "#FF6347", "#FFB90F"};
    private String tag[] = {"阳光", "自信", "随性", "理想主义", "热血", "有理想", "厚道", "宅", "腐女", "拖延症", "萌萌哒", "女汉子", "篮球", "强迫症", "逗比", "文艺", "义气", "AJ"};
    private List<Tag> tags = new ArrayList<>();
    private UserRegisterInformationPresenter presenter = new UserRegisterInformationPresenter(this);
    private StringBuilder builder = new StringBuilder();

    @Override
    public int intiLayout() {
        return R.layout.activity_user_register_information;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    private String sex = "0";

    @Override
    public void initData() {
        ButterKnife.inject(this);
        for (int i = 0; i < tag.length; i++) {
            int j = (int) (0 + Math.random() * color.length);
            Tag tag1 = new Tag(tag[i]);
            tag1.layoutColor = Color.parseColor(color[j]);
            tagGroup.addTag(tag1);
        }
        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                tagGroup.remove(position);
                int j = (int) (0 + Math.random() * color.length);
                Tag tag1 = new Tag(tag.text);
                tags.add(tag1);
                tag1.layoutColor = Color.parseColor(color[j]);
                tagGroupCheck.addTags(tags);
                builder.append(tag.text + ",");
            }
        });
        rgSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_boy:
                        sex = "1";
                        break;
                    case R.id.rb_girl:
                        sex = "0";
                        break;
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.btn_submit, R.id.tv_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                String s = tvBirthday.getText().toString();
                String replace = s.replace("出生日期 ： ", "");
                List<Tag> tags = tagGroupCheck.getTags();
                if (replace.trim().isEmpty()) {
                    ToastUtil.showToastInfor("请选择出生日期");
                } else if (etSign.getText().toString().isEmpty()) {
                    ToastUtil.showToastInfor("请输入你的个人介绍呗！");
                } else if (tags.isEmpty()) {
                    ToastUtil.showToastInfor("请选择你的标签吧！");
                } else if (!rbBoy.isChecked() && !rbGirl.isChecked()) {
                    ToastUtil.showToastInfor("你还没有选择性别哦！");
                } else {
                    Log.i(TAG, "onViewClicked: " + s);
                    long l = TimeUtil.YMDToTimestamp(replace);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                    String format1 = format.format(l);
                    Log.i(TAG, "onViewClicked: " + format1);
                    int ageFromBirthTime = getAgeFromBirthTime(new Date(l));
                    Log.i(TAG, "onViewClicked: " + ageFromBirthTime);
                    presenter.userReister(user.getString("username", etUsername.getText().toString()),
                            user.getString("password", "123456789"),
                            user.getString("tel", "17374131273"),
                            "" + ageFromBirthTime,
                            sex, etSign.getText().toString(),
                            builder.toString());
                }
                break;
            case R.id.tv_birthday:
                new AlertView("请选择日期", UserRegisterInformationActivity.this, 1995, 2018, UserRegisterInformationActivity.this).show();
                break;
        }

    }

    @Override
    public void registerSuccesss(BaseGson<EmptyGson> baseGson) {
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        presenter.loginByUsername(user.getString("tel", ""), user.getString("password", ""));
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
        editor.putString("tel", userGson.getTel());
        editor.putInt("age", userGson.getAge());
        editor.putString("city", userGson.getCity());
        editor.putString("latitude", userGson.getLatitude());
        editor.putString("longitude", userGson.getLongitude());
        editor.putString("sign", userGson.getSign());
        editor.putString("tag", userGson.getTag());
        editor.putBoolean("login", true);
        editor.putInt("id", userGson.getId());
        editor.apply();
        starActivity(HomeActivity.class);
        finish();
    }

    // 根据年月日计算年龄,birthTimeString:"1994-11-14"
    public static int getAgeFromBirthTime(Date date) {
        // 得到当前时间的年、月、日
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH) + 1;
            int dayNow = cal.get(Calendar.DATE);
            //得到输入时间的年，月，日
            cal.setTime(date);
            int selectYear = cal.get(Calendar.YEAR);
            int selectMonth = cal.get(Calendar.MONTH) + 1;
            int selectDay = cal.get(Calendar.DATE);
            // 用当前年月日减去生日年月日
            int yearMinus = yearNow - selectYear;
            int monthMinus = monthNow - selectMonth;
            int dayMinus = dayNow - selectDay;
            int age = yearMinus;// 先大致赋值
            if (yearMinus <= 0) {
                age = 0;
            }
            if (monthMinus < 0) {
                age = age - 1;
            } else if (monthMinus == 0) {
                if (dayMinus < 0) {
                    age = age - 1;
                }
            }
            return age;
        }
        return 0;
    }

    @Override
    public void result(String s) {
        tvBirthday.setText("出生日期 ： " + s);
    }
}
