package com.campus.appointment.gson;

import java.util.List;

/**
 * Created by Administrator on 2018/9/16/016.
 */

public class MatherPostGson {

    /**
     * id : 1
     * title : 根据中央机构编制委员会办公室在中国机构编制网上给出的定义，“三定”规定就是对一个部门的主要职责、内设机构、人员编制及领导职数等三大内容进行确定。 按照规定的统一体例和审核、审批程序，由党委、政府印发的“职能配置、内设机构和人员编制规定”，是具有一定法律效力的规范性文件。
     * writetime : 2018-09-11 08:44:54
     * location : 嘉兴
     * uid : 4
     * fell : null
     * is_post : 1
     * pic : []
     */

    private int id;
    private String title;
    private String writetime;
    private String location;
    private int uid;
    private Object fell;
    private int is_post;
    private List<Pic> pic;

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

    public List<Pic> getPic() {
        return pic;
    }

    public void setPic(List<Pic> pic) {
        this.pic = pic;
    }
    public static class Pic{
        private String pic;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
