package com.campus.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.gson.UserTest;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class TagGroupAdapter extends TagsAdapter {
    private List<UserTest.DataBean> list;
    private Context context;

    public TagGroupAdapter(List<UserTest.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        this.context = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.home_tag_item_layout, parent, false);
        ImageView ivHead = inflate.findViewById(R.id.iv_head);
        TextView tvUserName = inflate.findViewById(R.id.tv_username);
        tvUserName.setTextSize(5);
        Glide.with(context).load(list.get(position).getHead()).into(ivHead);
        tvUserName.setText(list.get(position).getUsername());
        return inflate;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position%5;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {

    }
}
