package com.campus.appointment.adapter;

import android.support.annotation.Nullable;

import com.campus.appointment.R;
import com.campus.appointment.gson.SquareGson;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/9/15/015.
 */

public class MatherUserAdapter extends BaseQuickAdapter<SquareGson, BaseViewHolder> {
    public MatherUserAdapter(@Nullable List<SquareGson> data) {
        super(R.layout.observe_user_dynatic_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, SquareGson squareGson) {

    }
}
