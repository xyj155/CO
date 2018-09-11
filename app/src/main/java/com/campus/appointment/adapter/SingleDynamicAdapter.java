package com.campus.appointment.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.campus.appointment.R;
import com.campus.appointment.entity.SquareEntity;
import com.campus.appointment.util.TimeUtil;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SingleDynamicAdapter extends BaseMultiItemQuickAdapter<SquareEntity, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context context;


    public SingleDynamicAdapter(List<SquareEntity> data, Context context) {
        super(data);
        this.context = context;
        addItemType(SquareEntity.TYPE_TEXT_TYPE, R.layout.square_ry_text_item);
        addItemType(SquareEntity.TYPE_PIC_MESSAGE_TYPE, R.layout.square_ry_pic_text_item);

    }

    @Override
    protected void convert(BaseViewHolder helper, final SquareEntity item) {
        int itemType = item.getItemType();
        switch (itemType) {
            case SquareEntity.TYPE_TEXT_TYPE:

                break;
            case SquareEntity.TYPE_PIC_MESSAGE_TYPE:
                List<String> list = new ArrayList<>();
                for (int i = 0; i < item.getSquareMsgGson().getPic_size(); i++) {
                    list.add(item.getSquareMsgGson().getPics().get(i).getPic());
                }
                RecyclerView ryPicList = helper.getView(R.id.ry_piclist);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
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


        String writetime = item.getSquareMsgGson().getWritetime();
        Date date = new Date(TimeUtil.dateToTimestamp(writetime));

        SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日");
        String format1 = time.format(date);
        Log.i(TAG, "convert: " + format1);
        helper.setText(R.id.tv_username, item.getSquareMsgGson().getUser().getUsername())
                .setText(R.id.tv_time, "发布于： " + item.getSquareMsgGson().getLocation())
                .setText(R.id.tv_title, item.getSquareMsgGson().getTitle())
                .setVisible(R.id.iv_report, false)
                .setVisible(R.id.tv_post_time, true)
                .setText(R.id.tv_post_time, format1);


        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()//设置圆形
                .placeholder(R.mipmap.co)
                .error(R.mipmap.co)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(item.getSquareMsgGson().getUser().getHead()).apply(options).into((ImageView) helper.getView(R.id.iv_head));
        switch (item.getSquareMsgGson().getUser().getSex()) {
            case 1:
                Glide.with(context).load(R.mipmap.square_sex_boy).into((ImageView) helper.getView(R.id.iv_sex));
                break;
            case 0:
                Glide.with(context).load(R.mipmap.square_sex_girl).into((ImageView) helper.getView(R.id.iv_sex));
                break;
        }
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
