package com.campus.appointment.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.BaseFragment;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.UserContract;
import com.campus.appointment.presenter.home.UserPresenter;
import com.campus.appointment.ui.activity.SendBugsReportActivity;
import com.campus.appointment.ui.activity.UserDetailActivity;
import com.campus.appointment.ui.activity.UserDynamicActivity;
import com.campus.appointment.ui.activity.UserObserveActivity;
import com.campus.appointment.ui.activity.UserSettingActivity;
import com.campus.appointment.weight.CircleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.scwang.wave.MultiWaveHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/9/4/004.
 */

public class UserFragment extends BaseFragment implements UserContract.View {
    private static UserFragment instance;
    @InjectView(R.id.iv_head)
    CircleImageView ivHead;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.user_tv_user_infor)
    TextView userTvUserInfor;
    @InjectView(R.id.user_tv_user_infor_divider)
    View userTvUserInforDivider;
    @InjectView(R.id.user_tv_book)
    TextView userTvBook;
    @InjectView(R.id.user_tv_book_divider)
    View userTvBookDivider;
    @InjectView(R.id.user_tv_observer)
    TextView userTvObserver;
    @InjectView(R.id.user_tv_observer_divider)
    View userTvObserverDivider;
    @InjectView(R.id.user_tv_setting)
    TextView userTvSetting;
    @InjectView(R.id.user)
    ConstraintLayout user;
    @InjectView(R.id.waveHeader)
    MultiWaveHeader waveHeader;
    @InjectView(R.id.tv_send_bug)
    ImageView tvSendBug;
    private UserPresenter userPresenter = new UserPresenter(this);

    public static synchronized UserFragment getInstance() {
        if (instance == null) {
            return new UserFragment();
        } else {
            return instance;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_user;
    }

    private static final String TAG = "UserFragment";

    @Override
    protected void setUpView(View view, Bundle bundle) {
        ButterKnife.inject(this, view);
        SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        RequestOptions requestOptions = new RequestOptions()
                .error(R.mipmap.laught);
        Glide.with(getActivity()).load(sp.getString("head", "")).apply(requestOptions).into(ivHead);
        Log.i(TAG, "setUpView: " + sp.getString("head", ""));
        Log.i(TAG, "setUpView: " + sp.getString("username", ""));
        tvUsername.setText(sp.getString("username", ""));
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    private List<LocalMedia> selectList = new ArrayList<>();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_send_bug, R.id.iv_head, R.id.tv_username, R.id.user_tv_user_infor, R.id.user_tv_book, R.id.user_tv_observer, R.id.user_tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_bug:
                starActivity(SendBugsReportActivity.class);
                break;
            case R.id.iv_head:
                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、bottom_pic.ofImage()、视频.ofVideo()、音频.ofAudio()
                        .theme(R.style.picture_white_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                        .maxSelectNum(1)// 最大图片选择数量
                        .minSelectNum(1)// 最小选择数量
                        .imageSpanCount(3)// 每行显示个数
                        .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                        .previewImage(false)// 是否可预览图片
                        .previewVideo(false)// 是否可预览视频
                        .enablePreviewAudio(false) // 是否可播放音频
                        .isCamera(false)// 是否显示拍照按钮
                        .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                        .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                        //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                        .enableCrop(false)// 是否裁剪
                        .compress(true)// 是否压缩
                        .synOrAsy(true)//同步true或异步false 压缩 默认同步
                        //.compressSavePath(getPath())//压缩图片保存地址
                        //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                        .isGif(false)// 是否显示gif图片
                        .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                        .circleDimmedLayer(false)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(false)// 是否开启点击声音
                        .selectionMedia(selectList)// 是否传入已选图片
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            case R.id.tv_username:
                break;
            case R.id.user_tv_user_infor:
                starActivity(UserDetailActivity.class);
                break;
            case R.id.user_tv_book:
                starActivity(UserDynamicActivity.class);
                break;
            case R.id.user_tv_observer:
                starActivity(UserObserveActivity.class);
                break;
            case R.id.user_tv_setting:
                starActivity(UserSettingActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: "+requestCode+resultCode+data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.i(TAG, "onActivityResult: "+selectList.get(0).getCompressPath());
                    RequestOptions requestOptions=new RequestOptions()
                            .error(R.mipmap.co);
                    Glide.with(getActivity()).load(selectList.get(0).getCompressPath()).apply(requestOptions).into(ivHead);
                    SharedPreferences.Editor editor=getActivity().getSharedPreferences("user",MODE_PRIVATE).edit();
                    editor.putString("head",selectList.get(0).getCompressPath());
                    editor.apply();
                    userPresenter.uploadAvatar(String.valueOf(getActivity().getSharedPreferences("user", MODE_PRIVATE).getInt("id", 0)), selectList.get(0).getCompressPath());
                    break;
            }
        }

    }

    @Override
    public void loadAvatar(BaseGson<EmptyGson> upload) {
        ToastUtil.showToastSuccess("头像上传成功");
    }

    @Override
    public void sendBugs(BaseGson<EmptyGson> upload) {

    }
}
