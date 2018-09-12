package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/8/008.
 */

public class SquareGson {


        /**
         * id : 1
         * title : 根据中央机构编制委员会办公室在中国机构编制网上给出的定义，“三定”规定就是对一个部门的主要职责、内设机构、人员编制及领导职数等三大内容进行确定。 按照规定的统一体例和审核、审批程序，由党委、政府印发的“职能配置、内设机构和人员编制规定”，是具有一定法律效力的规范性文件。
         * writetime : 2018-09-11 08:44:54
         * location : 嘉兴
         * uid : 4
         * fell : null
         * is_post : 1
         * pic_size : 1
         * user : {"id":4,"identity":null,"tel":"17374131273","city":"嘉兴市","updatetime":"2018-09-09 13:01:09","password":"123","username":"Privocs","latitude":"30.736212","longitude":"120.716109","head":"http://www.qqzhi.com/uploadpic/2014-09-26/031630340.jpg","is_qq":0,"qq_id":null,"sex":1,"is_vip":null}
         * thumb : 1
         * comment : 0
         * like : true
         * pics : [{"pic":"http://i2.chinanews.com/simg/cmshd/2018/09/10/4a7012098b3d4626bb8bff78cb5d50d7.jpg"}]
         */

        private int id;
        private String title;
        private String writetime;

    @Override
    public String toString() {
        return "SquareGson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", writetime='" + writetime + '\'' +
                ", location='" + location + '\'' +
                ", uid=" + uid +
                ", fell=" + fell +
                ", is_post=" + is_post +
                ", pic_size=" + pic_size +
                ", user=" + user +
                ", thumb=" + thumb +
                ", comment=" + comment +
                ", like=" + like +
                ", pics=" + pics +
                '}';
    }

    private String location;
        private int uid;
        private Object fell;
        private int is_post;
        private int pic_size;
        private UserBean user;
        private int thumb;
        private int comment;
        private boolean like;
        private List<PicsBean> pics;

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

        public Object getFell() {
            return fell;
        }

        public void setFell(Object fell) {
            this.fell = fell;
        }

        public int getIs_post() {
            return is_post;
        }

        public void setIs_post(int is_post) {
            this.is_post = is_post;
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

        public int getThumb() {
            return thumb;
        }

        public void setThumb(int thumb) {
            this.thumb = thumb;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public boolean isLike() {
            return like;
        }

        public void setLike(boolean like) {
            this.like = like;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
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
            private Object identity;
            private String tel;
            private String city;
            private String updatetime;
            private String password;
            private String username;
            private String latitude;
            private String longitude;
            private String avatar;
            private int is_qq;
            private Object qq_id;
            private int sex;
            private Object is_vip;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getIs_qq() {
                return is_qq;
            }

            public void setIs_qq(int is_qq) {
                this.is_qq = is_qq;
            }

            public Object getQq_id() {
                return qq_id;
            }

            public void setQq_id(Object qq_id) {
                this.qq_id = qq_id;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getIs_vip() {
                return is_vip;
            }

            public void setIs_vip(Object is_vip) {
                this.is_vip = is_vip;
            }
        }

        public static class PicsBean {
            /**
             * pic : http://i2.chinanews.com/simg/cmshd/2018/09/10/4a7012098b3d4626bb8bff78cb5d50d7.jpg
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

}
