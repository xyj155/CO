package com.campus.appointment;

/**
 * Created by Administrator on 2018/9/5/005.
 */

public class TagGroupGson {
    private String username;
    private String userhead;

    public TagGroupGson(String username, String userhead) {
        this.username = username;
        this.userhead = userhead;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserhead() {
        return userhead;
    }

    public void setUserhead(String userhead) {
        this.userhead = userhead;
    }
}
