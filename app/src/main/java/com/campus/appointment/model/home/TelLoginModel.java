package com.campus.appointment.model.home;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.contract.home.TelLoginContract;
import com.campus.appointment.http.RetrofitUtil;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/17/017.
 */

public class TelLoginModel implements TelLoginContract.Model {
    @Override
    public Observable<BaseGson<EmptyGson>> isHaveUsername(String tel) {
        return RetrofitUtil.getInstance(RetrofitUtil.BASE_URL).getServerices().isHaveUsername(tel);
    }
}
