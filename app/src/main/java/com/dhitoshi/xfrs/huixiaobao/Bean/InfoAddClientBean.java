package com.dhitoshi.xfrs.huixiaobao.Bean;

import java.util.List;

/**
 * Created by dxs on 2017/9/9.
 */

public class InfoAddClientBean {

    private List<HobbyBean> hobby;
    private List<IllBean> ill;
    private List<PositionBean> position;
    private List<CustomerTypeBean> customer_type;

    public List<HobbyBean> getHobby() {
        return hobby;
    }

    public void setHobby(List<HobbyBean> hobby) {
        this.hobby = hobby;
    }

    public List<IllBean> getIll() {
        return ill;
    }

    public void setIll(List<IllBean> ill) {
        this.ill = ill;
    }

    public List<PositionBean> getPosition() {
        return position;
    }

    public void setPosition(List<PositionBean> position) {
        this.position = position;
    }

    public List<CustomerTypeBean> getCustomerType() {
        return customer_type;
    }
    public void setCustomerType(List<CustomerTypeBean> customerType) {
        this.customer_type = customerType;
    }
}
