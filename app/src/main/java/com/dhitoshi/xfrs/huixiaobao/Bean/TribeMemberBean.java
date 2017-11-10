package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/11/10.
 */

public class TribeMemberBean {

    /**
     * id : 23
     * name : yxbxw
     * truename : 黄经理
     * head :
     */
    private int id;
    private String name;
    private String truename;
    private String head;
    private boolean isSelected=false;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTruename() {
        return truename;
    }
    public void setTruename(String truename) {
        this.truename = truename;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
