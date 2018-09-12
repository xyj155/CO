package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/12/012.
 */

public class PostDetail {


        /*
         * like : true
         * pic_size : 2
         * pic : [{"pic":"http://images.china.cn/site1000/2018-09/03/92b1ce7f-46e8-4ad6-8758-9de56ec379fe_watermark.jpg"},{"pic":"http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg"}]
         * thumb : 1
         * user : {"id":3,"tel":"17374131273","city":"嘉兴市","updatetime":"2018-09-12 12:06:59","password":"12356","username":"袁大头","latitude":"30.736212","longitude":"120.716109","head":"http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg","is_qq":0,"qq_id":"B0AD70A138C7CD9738F51B935F86606E","sex":1,"is_vip":0,"avatar":"http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png","identity":"普通用户"}
         * post : {"id":3,"title":"具体来看，其主要职责包括十余个方面，诸如：组织拟订国民健康政策，拟订卫生健康事业发展法律法规草案、政策、规划，制定部门规章和标准并组织实施；协调推进深化医药卫生体制改革，研究提出深化医药卫生体制改革重大方针、政策、措施的建议，等等。 在强化卫生健康领域的同时，其计划生育的职能则较之前有所弱化。在该部门\u201c三定\u201d规定中，其涉及计划生育的职责仅有\u201c负责计划生育管理和服务工作，开展人口监测预警，研究提出人口与家庭发展相关政策建议，完善计划生育政策\u201d、\u201c指导中国计划生育协会的业务工作\u201d两项。","writetime":"2018-09-11 08:45:09","location":"湘潭","uid":6,"fell":null,"is_post":1}
         * comment : [{"id":1,"uid":3,"comment":"我是袁建中的爸爸！","pid":3,"user":{"avatar":"http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png","username":"袁大头","id":3}},{"id":2,"uid":4,"comment":"你的东西不行啊","pid":3,"user":{"avatar":"http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png","username":"Privocs","id":4}},{"id":3,"uid":5,"comment":"今天下大雨啊","pid":3,"user":{"avatar":"http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png","username":"Discs","id":5}}]
         * commentcount : 3
         */

    private boolean like;
    private int pic_size;
    private int thumb;
    private UserBean user;
    private PostBean post;
    private int commentcount;
    private List<PicBean> pic;
    private List<CommentBean> comment;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public int getPic_size() {
        return pic_size;
    }

    public void setPic_size(int pic_size) {
        this.pic_size = pic_size;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public List<PicBean> getPic() {
        return pic;
    }

    public void setPic(List<PicBean> pic) {
        this.pic = pic;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class UserBean {
        /**
         * id : 3
         * tel : 17374131273
         * city : 嘉兴市
         * updatetime : 2018-09-12 12:06:59
         * password : 12356
         * username : 袁大头
         * latitude : 30.736212
         * longitude : 120.716109
         * head : http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg
         * is_qq : 0
         * qq_id : B0AD70A138C7CD9738F51B935F86606E
         * sex : 1
         * is_vip : 0
         * avatar : http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png
         * identity : 普通用户
         */

        private int id;
        private String tel;
        private String city;
        private String updatetime;
        private String password;
        private String username;
        private String latitude;
        private String longitude;
        private int is_qq;
        private String qq_id;
        private int sex;
        private int is_vip;
        private String avatar;
        private String identity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }

    public static class PostBean {
        /**
         * id : 3
         * title : 具体来看，其主要职责包括十余个方面，诸如：组织拟订国民健康政策，拟订卫生健康事业发展法律法规草案、政策、规划，制定部门规章和标准并组织实施；协调推进深化医药卫生体制改革，研究提出深化医药卫生体制改革重大方针、政策、措施的建议，等等。 在强化卫生健康领域的同时，其计划生育的职能则较之前有所弱化。在该部门“三定”规定中，其涉及计划生育的职责仅有“负责计划生育管理和服务工作，开展人口监测预警，研究提出人口与家庭发展相关政策建议，完善计划生育政策”、“指导中国计划生育协会的业务工作”两项。
         * writetime : 2018-09-11 08:45:09
         * location : 湘潭
         * uid : 6
         * fell : null
         * is_post : 1
         */

        private int id;
        private String title;
        private String writetime;
        private String location;
        private int uid;
        private Object fell;
        private int is_post;

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
    }

    public static class PicBean {
        /**
         * pic : http://images.china.cn/site1000/2018-09/03/92b1ce7f-46e8-4ad6-8758-9de56ec379fe_watermark.jpg
         */

        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }

    public static class CommentBean {
        /**
         * id : 1
         * uid : 3
         * comment : 我是袁建中的爸爸！
         * pid : 3
         * user : {"avatar":"http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png","username":"袁大头","id":3}
         */

        private int id;
        private int uid;
        private String comment;
        private int pid;
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        private UserBeanX user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public static class UserBeanX {
            /**
             * avatar : http://122.152.231.185/CO/public/avatar/20180911/4d51894570dfab0585e6027f80433093.png
             * username : 袁大头
             * id : 3
             */

            private String avatar;
            private String username;
            private int id;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }

    }
}
