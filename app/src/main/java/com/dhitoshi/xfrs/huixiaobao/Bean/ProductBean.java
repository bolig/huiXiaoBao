package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/6.
 */

public class ProductBean implements Parcelable {

    /**
     * id : 1
     * name : 测试产品
     * area : 上海市
     * cost : 123
     * notes : 这是一个测试产品
     * type_name : 保健用品
     */

    private int id;
    private String name;
    private String area;
    private String cost;
    private String notes;
    private String type_name;
    private String area_id;
    private String type_id;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.area);
        dest.writeString(this.cost);
        dest.writeString(this.notes);
        dest.writeString(this.type_name);
        dest.writeString(this.area_id);
        dest.writeString(this.type_id);
    }

    public ProductBean() {
    }

    protected ProductBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.area = in.readString();
        this.cost = in.readString();
        this.notes = in.readString();
        this.type_name = in.readString();
        this.area_id = in.readString();
        this.type_id = in.readString();
    }

    public static final Parcelable.Creator<ProductBean> CREATOR = new Parcelable.Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel source) {
            return new ProductBean(source);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };
}
