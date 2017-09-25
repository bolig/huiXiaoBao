package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/24.
 */

public class OwnMeetBean {

    /**
     * id : 3
     * type : {"id":2,"img":"","name":"测试会议","starttime":"2017-09-11 00:00:00","endtime":"2017-09-11 00:00:00","cost":"120","area":"1,2,3","body":"149"}
     * location : 预计地点
     * starttime : 2020-09-12 00:00:00
     * endtime : 2020-09-14 00:00:00
     * note : 备注信息
     * days : 2
     */
    private int id;
    private TypeBean type;
    private String location;
    private String starttime;
    private String endtime;
    private String note;
    private int days;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public TypeBean getType() {
        return type;
    }
    public void setType(TypeBean type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }


}
