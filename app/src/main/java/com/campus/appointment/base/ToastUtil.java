package com.campus.appointment.base;

import android.content.Context;
import android.widget.Toast;

import com.campus.appointment.MyApplication;
import com.campus.appointment.weight.toast.Toasty;


/**
 * Created by Administrator on 2018/7/15.
 */

public class ToastUtil {
    /**
     * 获取上下文
     * @return
     */
    public static Context getApplicationContext() {
        if (MyApplication.getInstance().getApplicationContext() == null) {
            return new MyApplication();
        }
        return MyApplication.getInstance().getApplicationContext();
    }

    /**
     * 错误提示
     * @param message
     */
    public static void showToastError(String message) {
        Toasty.error(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 成功提示
     * @param message
     */
    public static void showToastSuccess(String message) {
        Toasty.success(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 信息提示
     * @param message
     */
    public static void showToastInfor(String message) {
        Toasty.info(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 警告提示
     * @param message
     */
    public static void showToastWarning(String message) {
        Toasty.warning(getApplicationContext(), message, Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 普通提示
     * @param message
     */
    public static void showToastUsual(String message) {
        Toasty.normal(getApplicationContext(), message).show();
    }

}
