package com.campus.appointment.base;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class BaseGson<T> {

    @Override
    public String toString() {
        return "BaseGson{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 200
     * success : true
     * msg : 查询成功
     * data : [{"username":"123","head":"http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg","latitude":"31.253411","longitude":"121.518998"},{"username":"3123213","head":"http://img5.duitang.com/uploads/item/201508/12/20150812170635_dhZWc.thumb.224_0.jpeg","latitude":"31.253411","longitude":"121.518998"},{"username":"13123","head":"http://img5.duitang.com/uploads/item/201504/18/20150418H5449_Bdf8G.jpeg","latitude":"31.253411","longitude":"121.518998"},{"username":"123","head":"http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg","latitude":"31.253411","longitude":"121.518998"},{"username":"3123213","head":"http://img5.duitang.com/uploads/item/201508/12/20150812170635_dhZWc.thumb.224_0.jpeg","latitude":"31.253411","longitude":"121.518998"},{"username":"13123","head":"http://img5.duitang.com/uploads/item/201504/18/20150418H5449_Bdf8G.jpeg","latitude":"31.253411","longitude":"121.518998"},{"username":"123","head":"http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg","latitude":"31.253411","longitude":"121.518998"},{"username":"123","head":"http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg","latitude":"31.253411","longitude":"121.518998"}]
     */

    private int code;
    private boolean success;
    private String msg;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


}
