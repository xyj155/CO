package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquareGson {


    /**
     * id : 1
     * title : 1
     * writetime : 2018-09-10 15:50:34
     * location : 嘉兴
     * uid : 4
     * pic_size : 0
     * user : {"id":4,"identity":null,"tel":"17374131273","city":"嘉兴市","updatetime":"2018-09-09 13:01:09","password":"123","username":"Privocs","latitude":"30.736212","longitude":"120.716109","head":"http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg","is_qq":0,"qq_id":null,"sex":1,"is_vip":null}
     * pics : []
     */

    private int id;
    private String title;
    private String writetime;
    private String location;
    private int uid;
    private int pic_size;

    @Override
    public String toString() {
        return "SquareGson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writetime='" + writetime + '\'' +
                ", location='" + location + '\'' +
                ", uid=" + uid +
                ", pic_size=" + pic_size +
                ", user=" + user +
                ", pics=" + pics +
                '}';
    }

    private UserBean user;
    private List<Pic> pics;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWritetime() {
        return writetime;
    }

    public void setWritetime(String writetime) {
        this.writetime = writetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPic_size() {
        return pic_size;
    }

    public void setPic_size(int pic_size) {
        this.pic_size = pic_size;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }

    public static class Pic {
        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }

    public static class UserBean {
        /**
         * id : 4
         * identity : null
         * tel : 17374131273
         * city : 嘉兴市
         * updatetime : 2018-09-09 13:01:09
         * password : 123
         * username : Privocs
         * latitude : 30.736212
         * longitude : 120.716109
         * head : http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg
         * is_qq : 0
         * qq_id : null
         * sex : 1
         * is_vip : null
         */

        private int id;
        private String identity;
        private String tel;
        private String city;
        private String updatetime;

        @Override
        public String toString() {
            return "UserBean{" +
                    "id=" + id +
                    ", identity='" + identity + '\'' +
                    ", tel='" + tel + '\'' +
                    ", city='" + city + '\'' +
                    ", updatetime='" + updatetime + '\'' +
                    ", password='" + password + '\'' +
                    ", username='" + username + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", head='" + head + '\'' +
                    ", is_qq=" + is_qq +
                    ", qq_id='" + qq_id + '\'' +
                    ", sex=" + sex +
                    ", is_vip='" + is_vip + '\'' +
                    '}';
        }

        private String password;
        private String username;
        private String latitude;
        private String longitude;
        private String head;
        private int is_qq;
        private String qq_id;
        private int sex;
        private String is_vip;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
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

        public int getIs_qq() {
            return is_qq;
        }

        public void setIs_qq(int is_qq) {
            this.is_qq = is_qq;
        }

        public String getQq_id() {
            return qq_id;
        }

        public void setQq_id(String qq_id) {
            this.qq_id = qq_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }
    }

}
