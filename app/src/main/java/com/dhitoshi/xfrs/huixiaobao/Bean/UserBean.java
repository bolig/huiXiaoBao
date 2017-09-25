package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/12.
 */

public class UserBean implements Parcelable {

    /**
     * id : 2
     * name : tes1t
     * truename :
     * area : 上海市
     * group : 店长
     * phone :
     * email :
     * CRM : 1
     * APP : 1
     * token : {7296166d-1705-41eb-1eac-a9964fdd1a53}
     */

    private int id;
    private String name;
    private String truename;
    private String area;
    private String group;
    private String phone;
    private String email;
    private int CRM;
    private int APP;
    private String token;
    private String head;
    private String area_id;
    public String getArea_id() {
        return area_id;
    }
    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
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
    public String getTruename() {
        return truename;
    }
    public void setTruename(String truename) {
        this.truename = truename;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getCRM() {
        return CRM;
    }
    public void setCRM(int CRM) {
        this.CRM = CRM;
    }
    public int getAPP() {
        return APP;
    }
    public void setAPP(int APP) {
        this.APP = APP;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.truename);
        dest.writeString(this.area);
        dest.writeString(this.group);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeInt(this.CRM);
        dest.writeInt(this.APP);
        dest.writeString(this.token);
        dest.writeString(this.head);
        dest.writeString(this.area_id);
    }

    public UserBean() {
    }

    protected UserBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.truename = in.readString();
        this.area = in.readString();
        this.group = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.CRM = in.readInt();
        this.APP = in.readInt();
        this.token = in.readString();
        this.head = in.readString();
        this.area_id = in.readString();
    }

    public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel source) {
            return new UserBean(source);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
        }
    };
}
