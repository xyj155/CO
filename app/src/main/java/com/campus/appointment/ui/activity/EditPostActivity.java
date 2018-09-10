package com.campus.appointment.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.campus.appointment.R;
import com.campus.appointment.adapter.GridImageAdapter;
import com.campus.appointment.base.BaseActivity;
import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.EditPostContract;
import com.campus.appointment.presenter.home.EditPostPresenter;
import com.campus.appointment.util.FullyGridLayoutManager;
import com.campus.appointment.weight.iosDialog.CustomDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditPostActivity extends BaseActivity implements EditPostContract.View {

    @InjectView(R.id.iv_close)
    ImageView ivClose;
    @InjectView(R.id.tv_submit)
    TextView tvSubmit;
    @InjectView(R.id.et_post)
    EditText etPost;
    @InjectView(R.id.recycler)
    RecyclerView recycler;
    @InjectView(R.id.tv_fell)
    TextView tvFell;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private EditPostPresenter editPostPresenter;

    @Override
    public int intiLayout() {
        return R.layout.activity_edit_post;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        ButterKnife.inject(this);
        FullyGridLayoutManager manager = new FullyGridLayoutManager(EditPostActivity.this, 3, GridLayoutManager.VERTICAL, false);
        recycler.setLayoutManager(manager);
        adapter = new GridImageAdapter(EditPostActivity.this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(9);
        recycler.setAdapter(adapter);
        editPostPresenter = new EditPostPresenter(this);
    }

    @Override
    public void initData() {

    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(EditPostActivity.this)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .theme(R.style.picture_white_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                    .maxSelectNum(9)// 最大图片选择数量
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
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(16, 9)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .isGif(false)// 是否显示gif图片
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                    .circleDimmedLayer(false)// 是否圆形裁剪
                    .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                    .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                    .openClickSound(false)// 是否开启点击声音
                    .selectionMedia(selectList)// 是否传入已选图片
                    .minimumCompressSize(100)// 小于100kb的图片不压缩
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的

                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    @OnClick({R.id.iv_close, R.id.tv_submit, R.id.tv_fell})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                finish();
                break;
            case R.id.tv_submit:
                Map<String, RequestBody> partMap = new HashMap<>();
                partMap.put("uid", toRequestBody("4"));
                partMap.put("content", toRequestBody(etPost.getText().toString()));
                partMap.put("location", toRequestBody(getSharedPreferences("user",MODE_PRIVATE).getString("location","")));
                partMap.put("fell", toRequestBody(tvFell.getText().toString()));
                List<MultipartBody.Part> list = new ArrayList<>();
                for (LocalMedia media : selectList) {
                    File file = new File(media.getPath());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                    MultipartBody.Part formData = MultipartBody.Part.createFormData("image[]", file.getName(), requestBody);
                    list.add(formData);
                }
                editPostPresenter.uploadPost(partMap, list);
                break;
            case R.id.tv_fell:
                CustomDialog.Builder builder = new CustomDialog.Builder(EditPostActivity.this);
                final CustomDialog customDialog = builder.view(R.layout.edit_post_dialog)
                        .size(0.27, 1)
                        .location(Gravity.CENTER)
                        .canTouchout(true)
                        .cancelBackPress(false)
                        .build();
                customDialog.show();
                final EditText view1 = customDialog.getView(R.id.et_fell_tag);
                customDialog.getView(R.id.btn_neg)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                customDialog.dismiss();
                            }
                        });
                customDialog.getView(R.id.btn_pos)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvFell.setText("# " + view1.getText().toString());
                                customDialog.dismiss();
                            }
                        });

                break;
        }
    }

    @Override
    public void isUploadSuccess(BaseGson<EmptyGson> baseGson) {
        ToastUtil.showToastSuccess("发布成功");
        finish();
    }

    @Override
    public void showLoading(String msg) {
        showmDialog(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent=new Intent(EditPostActivity.this,HomeActivity.class);
        startActivity(intent);
        setResult(0,intent);
    }

    @Override
    public void hideLoading() {
        hidemDialog();
    }
}
