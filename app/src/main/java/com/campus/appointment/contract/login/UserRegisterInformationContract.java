package com.campus.appointment.contract.login;

import com.campus.appointment.base.BaseGson;
import com.campus.appointment.base.EmptyGson;
import com.campus.appointment.gson.UserGson;

import rx.Observable;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public interface UserRegisterInformationContract {
    interface Model {
        Observable<BaseGson<EmptyGson>> userReister(String username,
                                                    String password,
                                                    String tel,
                                                    String age,
                                                    String sex,
                                                    String sign,
                                                    String tag
                                                    );
        Observable<BaseGson<UserGson>> loginByUsername(String username,String password);
    }

    interface View {
        void registerSuccesss(BaseGson<EmptyGson> baseGson);
        void showDialog(String msg);
        void hideDialog();

        void loginByUsername(UserGson userGson);
    }

    interface Presenter {
        void userReister(String username,
                         String password,
                         String tel,
                         String age,
                         String sex,
                         String sign,
                         String tag
                         );
        void loginByUsername(String username,String password);
    }
}
