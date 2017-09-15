package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/14.
 */
public class InfoAddSpendBean {
    private List<ProductBean> item;
    private List<BaseBean> saleaddress;
    private List<BaseBean> salesman;
    public List<ProductBean> getItem() {
        return item;
    }
    public void setItem(List<ProductBean> item) {
        this.item = item;
    }
    public List<BaseBean> getSaleaddress() {
        return saleaddress;
    }
    public void setSaleaddress(List<BaseBean> saleaddress) {
        this.saleaddress = saleaddress;
    }
    public List<BaseBean> getSalesman() {
        return salesman;
    }
    public void setSalesman(List<BaseBean> salesman) {
        this.salesman = salesman;
    }

}
