package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/6.
 */

public class UserRole {


    /**
     * id : 1
     * name : 店长
     * area : 上海市
     * notes : 拥有店面管理最高权限
     * is_super : 1
     * area_id : 1
     */

    private int id;
    private String name;
    private String area;
    private String notes;
    private int is_super;
    private String area_id;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIs_super() {
        return is_super;
    }

    public void setIs_super(int is_super) {
        this.is_super = is_super;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
}
