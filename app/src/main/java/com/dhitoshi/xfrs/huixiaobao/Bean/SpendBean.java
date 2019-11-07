package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/7.
 */
public class SpendBean implements Parcelable {

    /**
     * id : 1
     * createtime : 2017-09-06 00:00:00
     * buyaddress : 总公司
     * cost : 123
     * number : 2
     * discount : 20
     * ac_receive : 231
     * debt : 12
     * ac_num : 1
     * wait_num : 1
     * notes : 备注
     * customer_name : kehu
     * customer_company : 公司
     * item_name : 测试产品
     * saleman_name : test
     */

    private int id;
    private String createtime;
    private String buyaddress;
    private String cost;
    private String number;
    private String discount;
    private String ac_receive;
    private String debt;
    private String ac_num;
    private String wait_num;
    private String notes;
    private String customer_name;
    private String customer_company;
    private String item_name;
    private String saleman_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getBuyaddress() {
        return buyaddress;
    }

    public void setBuyaddress(String buyaddress) {
        this.buyaddress = buyaddress;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getAc_receive() {
        return ac_receive;
    }

    public void setAc_receive(String ac_receive) {
        this.ac_receive = ac_receive;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getAc_num() {
        return ac_num;
    }

    public void setAc_num(String ac_num) {
        this.ac_num = ac_num;
    }

    public String getWait_num() {
        return wait_num;
    }

    public void setWait_num(String wait_num) {
        this.wait_num = wait_num;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_company() {
        return customer_company;
    }

    public void setCustomer_company(String customer_company) {
        this.customer_company = customer_company;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getSaleman_name() {
        return saleman_name;
    }

    public void setSaleman_name(String saleman_name) {
        this.saleman_name = saleman_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.createtime);
        dest.writeString(this.buyaddress);
        dest.writeString(this.cost);
        dest.writeString(this.number);
        dest.writeString(this.discount);
        dest.writeString(this.ac_receive);
        dest.writeString(this.debt);
        dest.writeString(this.ac_num);
        dest.writeString(this.wait_num);
        dest.writeString(this.notes);
        dest.writeString(this.customer_name);
        dest.writeString(this.customer_company);
        dest.writeString(this.item_name);
        dest.writeString(this.saleman_name);
    }

    public SpendBean() {
    }

    protected SpendBean(Parcel in) {
        this.id = in.readInt();
        this.createtime = in.readString();
        this.buyaddress = in.readString();
        this.cost = in.readString();
        this.number = in.readString();
        this.discount = in.readString();
        this.ac_receive = in.readString();
        this.debt = in.readString();
        this.ac_num = in.readString();
        this.wait_num = in.readString();
        this.notes = in.readString();
        this.customer_name = in.readString();
        this.customer_company = in.readString();
        this.item_name = in.readString();
        this.saleman_name = in.readString();
    }

    public static final Parcelable.Creator<SpendBean> CREATOR = new Parcelable.Creator<SpendBean>() {
        @Override
        public SpendBean createFromParcel(Parcel source) {
            return new SpendBean(source);
        }

        @Override
        public SpendBean[] newArray(int size) {
            return new SpendBean[size];
        }
    };
}
