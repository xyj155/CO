package com.campus.appointment.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/11/011.
 */

public class TimeUtil {
    /**
     * 时间转换成时间戳
     * @param time
     * @return
     */
    public static long YMDToTimestamp(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() ;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }
    public static long dateToTimestamp(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() ;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

}
