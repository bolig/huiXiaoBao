package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class ScreenBean {
    private List<AreaBean> area;
    private List<CustomerTypeBean> customer_type;
    private List<OrderBean> order;
    public List<AreaBean> getArea() {
        return area;
    }
    public void setArea(List<AreaBean> area) {
        this.area = area;
    }
    public List<CustomerTypeBean> getCustomer_type() {
        return customer_type;
    }
    public void setCustomer_type(List<CustomerTypeBean> customer_type) {
        this.customer_type = customer_type;
    }
    public List<OrderBean> getOrder() {
        return order;
    }
    public void setOrder(List<OrderBean> order) {
        this.order = order;
    }
}
