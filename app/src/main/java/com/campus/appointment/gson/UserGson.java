package com.campus.appointment.gson;

/**
 * Created by Administrator on 2018/9/6/006.
 */

public class UserGson {

    @Override
    public String toString() {
        return "UserGson{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                ", tel='" + tel + '\'' +
                ", city='" + city + '\'' +
                ", time='" + time + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    /**
     * id : 3
     * identity : null
     * tel : null
     * city : 嘉兴
     * time : 2018-09-06 01:04:07
     * password : 123
     * username : 123
     * latitude : 31.253411
     * longitude : 121.518998
     * head : null
     */

    private int id;
    private String identity;
    private String tel;
    private String city;
    private int sex;
    private String  tag;
    private String sign;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    private String time;
    private String password;
    private String username;
    private String latitude;
    private String longitude;
    private String avatar;
private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
