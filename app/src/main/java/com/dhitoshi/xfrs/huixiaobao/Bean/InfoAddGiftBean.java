package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;

/**
 * Created by dxs on 2017/9/14.
 */
public class InfoAddGiftBean {
    private List<GiftBean> gift;
    private List<BaseBean> saleaddress;
    private List<BaseBean> salesman;

    public List<GiftBean> getGift() {
        return gift;
    }

    public void setGift(List<GiftBean> gift) {
        this.gift = gift;
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
