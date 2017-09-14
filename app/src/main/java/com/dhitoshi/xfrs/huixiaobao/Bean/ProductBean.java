package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/6.
 */

public class ProductBean {

    /**
     * id : 1
     * name : 测试产品
     * area : 上海市
     * cost : 123
     * notes : 这是一个测试产品
     * type_name : 保健用品
     */

    private int id;
    private String name;
    private String area;
    private String cost;
    private String notes;
    private String type_name;

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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
