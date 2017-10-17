package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/29.
 */

public class ChatContact {

    /**
     * userid : lhy
     * nick : lhy
     * icon_url : http://xfrsimg.oss-cn-hangzhou.aliyuncs.com/head/170613045354.png
     */

    private String userid;
    private String nick;
    private String icon_url;
    private boolean isSelected=false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
