package com.campus.appointment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.gson.UserGson;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class TagGroupAdapter extends TagsAdapter {
    private List<UserGson> list;
    private Context context;
    private OnClickInterface onclickInterface;

    public TagGroupAdapter(List<UserGson> list, Activity context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setOnItemClickListner(OnClickInterface onclick) {
        onclickInterface = onclick;
    }

    @Override
    public View getView(final Context context, int position, ViewGroup parent) {
        this.context = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_tag_item_layout, parent, false);
        ImageView ivHead = inflate.findViewById(R.id.iv_head);
        ivHead.setMaxHeight(20);
        ivHead.setMaxWidth(20);
        TextView tvUserName = inflate.findViewById(R.id.tv_username);
        tvUserName.setTextSize(5);
        Glide.with(context).load(list.get(position).getHead()).into(ivHead);
        tvUserName.setText(list.get(position).getUsername());
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickInterface.OnItemClickListener();
            }
        });
        return inflate;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 5;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }

    public interface OnClickInterface {
        void OnItemClickListener();
    }
}
