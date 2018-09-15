package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.contract.home.ConversationContract;
import com.campus.appointment.gson.Face;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class ConversationModel implements ConversationContract.Model {
    @Override
    public Observable<BaseGson<Face>> queryFace() {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().getFaces();
    }
}
