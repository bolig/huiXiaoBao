package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/14.
 */
public class InfoAddSpendBean {

    private List<ProductBean> item;
    private List<SaleaddressBean> saleaddress;
    private List<SalesmanBean> salesman;

    public List<ProductBean> getItem() {
        return item;
    }

    public void setItem(List<ProductBean> item) {
        this.item = item;
    }

    public List<SaleaddressBean> getSaleaddress() {
        return saleaddress;
    }

    public void setSaleaddress(List<SaleaddressBean> saleaddress) {
        this.saleaddress = saleaddress;
    }
    public List<SalesmanBean> getSalesman() {
        return salesman;
    }
    public void setSalesman(List<SalesmanBean> salesman) {
        this.salesman = salesman;
    }

}
