package com.campus.appointment.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.base.ToastUtil;
import com.campus.appointment.contract.home.SquareContract;
import com.campus.appointment.entity.SquareEntity;
import com.campus.appointment.gson.SquareGson;
import com.campus.appointment.presenter.home.SquarePresenter;
import com.campus.appointment.ui.activity.UserPostDetailActivity;
import com.campus.appointment.weight.iosDialog.ActionSheetDialog;
import com.campus.appointment.weight.iosDialog.AlertDialog;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquareAdapter extends BaseMultiItemQuickAdapter<SquareEntity, BaseViewHolder> implements SquareContract.View {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Fragment context;
    private SquarePresenter presenter;

    public SquareAdapter(List<SquareEntity> data, Fragment context) {
        super(data);
        this.context = context;
        addItemType(SquareEntity.TYPE_TEXT_TYPE, R.layout.square_ry_text_item);
        addItemType(SquareEntity.TYPE_PIC_MESSAGE_TYPE, R.layout.square_ry_pic_text_item);
        presenter = new SquarePresenter(this);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SquareEntity item) {
        final int itemType = item.getItemType();
        switch (itemType) {
            case SquareEntity.TYPE_TEXT_TYPE:

                break;
            case SquareEntity.TYPE_PIC_MESSAGE_TYPE:
                List<String> list = new ArrayList<>();
                for (int i = 0; i < item.getSquareMsgGson().getPic_size(); i++) {
                    list.add(item.getSquareMsgGson().getPics().get(i).getPic());
                }
                RecyclerView ryPicList = helper.getView(R.id.ry_piclist);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context.getActivity(), 3);
                ryPicList.setLayoutManager(gridLayoutManager);
                SquareMsgAdapter adapter = new SquareMsgAdapter(list);
                ryPicList.setAdapter(adapter);
                break;
        }

        ImageView report = helper.getView(R.id.iv_report);
        ImageView comment = helper.getView(R.id.iv_comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog(context.getActivity()).builder().setTitle("举报")
                        .setMsg("举报用户: " + item.getSquareMsgGson().getUser().getUsername())
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ActionSheetDialog(context.getActivity())
                                .builder()
                                .setCancelable(false)
                                .setTitle("请选择举报类型")
                                .setCanceledOnTouchOutside(false)
                                .addSheetItem("不文明用语", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                presenter.sendReport(String.valueOf(item.getSquareMsgGson().getUid()), "不文明用语", String.valueOf(item.getSquareMsgGson().getId()));
                                            }
                                        })
                                .addSheetItem("广告", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                presenter.sendReport(String.valueOf(item.getSquareMsgGson().getUid()), "广告", String.valueOf(item.getSquareMsgGson().getId()));
                                            }
                                        })
                                .addSheetItem("恶意骚扰", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                presenter.sendReport(String.valueOf(item.getSquareMsgGson().getUid()), "恶意骚扰", String.valueOf(item.getSquareMsgGson().getId()));
                                            }
                                        })
                                .addSheetItem("谩骂，诽谤", ActionSheetDialog.SheetItemColor.Blue,
                                        new ActionSheetDialog.OnSheetItemClickListener() {
                                            @Override
                                            public void onClick(int which) {
                                                presenter.sendReport(String.valueOf(item.getSquareMsgGson().getUid()), "谩骂，诽谤", String.valueOf(item.getSquareMsgGson().getId()));
                                            }
                                        })
                                .show();
                    }
                }).show();
            }
        });
        Log.i(TAG, "getThumb: " + item.getSquareMsgGson().getThumb());
        Log.i(TAG, "convert: " + item.getSquareMsgGson().getComment());
        helper.setText(R.id.tv_username, item.getSquareMsgGson().getUser().getUsername())
                .setText(R.id.tv_time, "发布于： " + item.getSquareMsgGson().getLocation())
                .setText(R.id.tv_title, item.getSquareMsgGson().getTitle())
                .setText(R.id.tv_thumb_count, item.getSquareMsgGson().getThumb() + "")
                .setText(R.id.tv_comment_count, item.getSquareMsgGson().getComment() + "")
                .setChecked(R.id.rb_thumb, item.getSquareMsgGson().isLike())
                .setOnClickListener(R.id.rb_thumb, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView view = helper.getView(R.id.tv_thumb_count);
                        view.setText((item.getSquareMsgGson().getThumb() + 1) + "");
                        Log.i(TAG, "onCheckedChanged: " + item.getSquareMsgGson().getId());
                        presenter.updateThumb("3", String.valueOf(item.getSquareMsgGson().getId()));
                    }
                })
                .setOnClickListener(R.id.ll_item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context.getActivity(), UserPostDetailActivity.class);
                        intent.putExtra("uid", "3");
                        intent.putExtra("id", String.valueOf(item.getSquareMsgGson().getId()));
                        intent.putExtra("puid", item.getSquareMsgGson().getUid());
                        Log.i(TAG, "onClick: " + helper.getPosition());
                        intent.putExtra("item", helper.getPosition());
                        context.startActivity(intent);
                    }
                });
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()//设置圆形
                .placeholder(R.mipmap.co)
                .error(R.mipmap.co)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(item.getSquareMsgGson().getUser().getAvatar()).apply(options).into((ImageView) helper.getView(R.id.iv_head));
        switch (item.getSquareMsgGson().getUser().getSex()) {
            case 1:
                Glide.with(context).load(R.mipmap.square_sex_boy).into((ImageView) helper.getView(R.id.iv_sex));
                break;
            case 0:
                Glide.with(context).load(R.mipmap.square_sex_girl).into((ImageView) helper.getView(R.id.iv_sex));
                break;
        }
    }

    @Override
    public void squareUserActive(List<SquareGson> squareGsons) {

    }

    @Override
    public void sendReport(List<EmptyGson> squareGsons) {
        ToastUtil.showToastSuccess("举报成功");
    }

    @Override
    public void updateThumb(List<EmptyGson> squareGsons) {

    }

    private class SquareMsgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public SquareMsgAdapter(@Nullable List<String> data) {
            super(R.layout.square_gd_img_item, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView imageView = helper.getView(R.id.iv_pic_msg);
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .circleCrop()//设置圆形
                    .error(R.mipmap.co)
                    .bitmapTransform(new RoundedCornersTransformation(12, 0, RoundedCornersTransformation.CornerType.ALL))
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            System.out.println(item);
            Glide.with(context).load(item)
                    .transition(DrawableTransitionOptions.withCrossFade()).apply(options).into(imageView);
        }
    }


}
