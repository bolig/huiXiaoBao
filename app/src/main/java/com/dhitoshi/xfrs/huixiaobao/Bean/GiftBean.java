package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/8.
 */

public class GiftBean implements Parcelable {

    /**
     * id : 1
     * userid : 14
     * createtime : 2017-09-06 00:00:00
     * gift : 测试产品2
     * num : 11
     * saleaddress : 分公司
     * salesman : tes1t
     * notes : 备注
     */
    private int id;
    private String userid;
    private String createtime;
    private String gift;
    private String num;
    private String saleaddress;
    private String salesman;
    private String notes;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getGift() {
        return gift;
    }
    public void setGift(String gift) {
        this.gift = gift;
    }
    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getSaleaddress() {
        return saleaddress;
    }
    public void setSaleaddress(String saleaddress) {
        this.saleaddress = saleaddress;
    }
    public String getSalesman() {
        return salesman;
    }
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userid);
        dest.writeString(this.createtime);
        dest.writeString(this.gift);
        dest.writeString(this.num);
        dest.writeString(this.saleaddress);
        dest.writeString(this.salesman);
        dest.writeString(this.notes);
    }

    public GiftBean() {
    }

    protected GiftBean(Parcel in) {
        this.id = in.readInt();
        this.userid = in.readString();
        this.createtime = in.readString();
        this.gift = in.readString();
        this.num = in.readString();
        this.saleaddress = in.readString();
        this.salesman = in.readString();
        this.notes = in.readString();
    }

    public static final Parcelable.Creator<GiftBean> CREATOR = new Parcelable.Creator<GiftBean>() {
        @Override
        public GiftBean createFromParcel(Parcel source) {
            return new GiftBean(source);
        }

        @Override
        public GiftBean[] newArray(int size) {
            return new GiftBean[size];
        }
    };
}
