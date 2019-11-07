package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;

/**
 * Created by dxs on 2017/9/14.
 */
public class InfoAddMeetBean {
    private List<BaseBean> usertype;
    private List<BaseBean> type;
    private List<BaseBean> salesman;

    public List<BaseBean> getUsertype() {
        return usertype;
    }

    public void setUsertype(List<BaseBean> usertype) {
        this.usertype = usertype;
    }

    public List<BaseBean> getType() {
        return type;
    }

    public void setType(List<BaseBean> type) {
        this.type = type;
    }

    public List<BaseBean> getSalesman() {
        return salesman;
    }

    public void setSalesman(List<BaseBean> salesman) {
        this.salesman = salesman;
    }
}
