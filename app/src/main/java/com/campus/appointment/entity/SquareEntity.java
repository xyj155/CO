package com.campus.appointment.entity;

import com.campus.appointment.gson.SquareGson;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquareEntity implements MultiItemEntity {
    public static final int TYPE_TEXT_TYPE = 1;

    public static final int TYPE_PIC_MESSAGE_TYPE = 2;

    private int itemType;
    private SquareGson squareMsgGson;

    public SquareEntity(int itemType, SquareGson data) {
        this.itemType = itemType;
        this.squareMsgGson = data;
    }

    /**
     * @param data
     * @return
     */
    public static SquareEntity picMsg(SquareGson data) {
        return new SquareEntity(TYPE_PIC_MESSAGE_TYPE, data);
    }

    /**
     * @param data
     * @return
     */
    public static SquareEntity textMsg(SquareGson data) {
        return new SquareEntity(TYPE_TEXT_TYPE, data);
    }

    public SquareGson getSquareMsgGson() {
        return squareMsgGson;
    }

    public void setSquareMsgGson(SquareGson squareMsgGson) {
        this.squareMsgGson = squareMsgGson;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
