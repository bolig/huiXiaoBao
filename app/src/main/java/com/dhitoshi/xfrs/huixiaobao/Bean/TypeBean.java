package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/24.
 */
public  class TypeBean {
    /**
     * id : 2
     * img :
     * name : 测试会议
     * starttime : 2017-09-11 00:00:00
     * endtime : 2017-09-11 00:00:00
     * cost : 120
     * area : 1,2,3
     * body : 149
     */

    private int id;
    private String img;
    private String name;
    private String starttime;
    private String endtime;
    private String cost;
    private String area;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}