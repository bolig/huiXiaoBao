package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;

/**
 * Created by dxs on 2017/9/7.
 */
public class AttendBean<T> {
    private List<T> list;
    private int days;
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }
}
