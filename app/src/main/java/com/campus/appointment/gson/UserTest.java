package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class UserTest {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":3,"identity":null,"tel":null,"city":"嘉兴","time":"2018-09-06 01:42:54","password":"123","username":"123","latitude":"31.253411","longitude":"121.518998","head":"http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg"},{"id":4,"identity":null,"tel":null,"city":"嘉兴","time":"2018-09-06 01:43:07","password":"123","username":"123","latitude":"31.253411","longitude":"121.518998","head":"http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg"},{"id":5,"identity":null,"tel":null,"city":"嘉兴","time":"2018-09-06 01:43:18","password":"13213","username":"3123213","latitude":"31.253411","longitude":"121.518998","head":"http://img5.duitang.com/uploads/item/201508/12/20150812170635_dhZWc.thumb.224_0.jpeg"},{"id":6,"identity":null,"tel":null,"city":"嘉兴","time":"2018-09-06 01:43:25","password":"313213","username":"13123","latitude":"31.253411","longitude":"121.518998","head":"http://img5.duitang.com/uploads/item/201504/18/20150418H5449_Bdf8G.jpeg"},{"id":7,"identity":null,"tel":null,"city":"株洲","time":"2018-09-06 01:43:28","password":"31313","username":"1313123","latitude":"31.253411","longitude":"121.518998","head":"http://cdn.duitang.com/uploads/item/201408/30/20140830180834_XuWYJ.png"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 3
         * identity : null
         * tel : null
         * city : 嘉兴
         * time : 2018-09-06 01:42:54
         * password : 123
         * username : 123
         * latitude : 31.253411
         * longitude : 121.518998
         * head : http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg
         */

        private int id;
        private Object identity;
        private Object tel;
        private String city;
        private String time;
        private String password;
        private String username;
        private String latitude;
        private String longitude;
        private String head;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIdentity() {
            return identity;
        }

        public void setIdentity(Object identity) {
            this.identity = identity;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }
    }
}
