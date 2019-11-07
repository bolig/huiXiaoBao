package com.dhitoshi.xfrs.huixiaobao.Bean;
/**
 * Created by dxs on 2017/9/17.
 */

public class AddProductBean {
    private String id;
    private String token;
    private String name;
    private String type_id;
    private String area;
    private String cost;
    private String notes;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType_id() {
        return type_id;
    }
    public void setType_id(String type_id) {
        this.type_id = type_id;
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
}
