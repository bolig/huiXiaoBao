package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/24.
 */
public  class TypeBean implements Parcelable {
    /**
     * id : 2
     * img :
     * name : 测试会议
     * starttime : 2017-09-11 00:00:00
     * endtime : 2017-09-11 00:00:00
     * cost : 120
     * area : 1,2,3
     * body : 149
     */

    private int id;
    private String img;
    private String name;
    private String starttime;
    private String endtime;
    private String cost;
    private String area;
    private String body;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.img);
        dest.writeString(this.name);
        dest.writeString(this.starttime);
        dest.writeString(this.endtime);
        dest.writeString(this.cost);
        dest.writeString(this.area);
        dest.writeString(this.body);
    }

    public TypeBean() {
    }

    protected TypeBean(Parcel in) {
        this.id = in.readInt();
        this.img = in.readString();
        this.name = in.readString();
        this.starttime = in.readString();
        this.endtime = in.readString();
        this.cost = in.readString();
        this.area = in.readString();
        this.body = in.readString();
    }

    public static final Parcelable.Creator<TypeBean> CREATOR = new Parcelable.Creator<TypeBean>() {
        @Override
        public TypeBean createFromParcel(Parcel source) {
            return new TypeBean(source);
        }

        @Override
        public TypeBean[] newArray(int size) {
            return new TypeBean[size];
        }
    };
}