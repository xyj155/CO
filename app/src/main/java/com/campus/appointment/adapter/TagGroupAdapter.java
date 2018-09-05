package com.campus.appointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.campus.appointment.R;
import com.campus.appointment.TagGroupGson;
import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class TagGroupAdapter extends TagsAdapter {
    private List<TagGroupGson> list;
    private Context context;

    public TagGroupAdapter(List<TagGroupGson> list, Context context) {
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
        Glide.with(context).load(list.get(position).getUserhead()).into(ivHead);
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
