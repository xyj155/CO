package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class UserInfromationGson {

    /**
     * id : 3
     * tel : 456789
     * city : 嘉兴市
     * updatetime : 2018-09-16 02:20:02
     * password : 123456
     * username : 123456
     * latitude : 30.736212
     * longitude : 120.716109
     * head : http://img0.imgtn.bdimg.com/it/u=3198678185,878755003&fm=27&gp=0.jpg
     * is_qq : 0
     * qq_id : B0AD70A138C7CD9738F51B935F86606E
     * sex : 1
     * is_vip : 0
     * avatar : http://cdn.duitang.com/uploads/item/201508/30/20150830175812_sYikS.jpeg
     * identity : 普通用户
     * age : 12
     * sign : null
     * tag : null
     * pic : ["http://i2.chinanews.com/simg/cmshd/2018/09/10/4a7012098b3d4626bb8bff78cb5d50d7.jpg","http://images.china.cn/site1000/2018-09/03/dfa81cdb-b3e8-4d37-9f4b-b2e464f19663_watermark.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg","http://n.sinaimg.cn/news/transform/700/w1000h500/20180910/HOzO-hivtsym0542442.jpg"]
     * pcount : 0
     * ocount : 12
     * uocount : 1
     */

    private int id;
    private String tel;
    private String city;
    private String updatetime;
    private String password;
    private String username;
    private String latitude;
    private String longitude;
    private String head;
    private int is_qq;
    private String qq_id;
    private int sex;
    private int is_vip;
    private String avatar;
    private String identity;
    private int age;
    private String sign;
    private String tag;
    private int pcount;
    private int ocount;
    private int uocount;
    private List<String> pic;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(int pcount) {
        this.pcount = pcount;
    }

    public int getOcount() {
        return ocount;
    }

    public void setOcount(int ocount) {
        this.ocount = ocount;
    }

    public int getUocount() {
        return uocount;
    }

    public void setUocount(int uocount) {
        this.uocount = uocount;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }
}
