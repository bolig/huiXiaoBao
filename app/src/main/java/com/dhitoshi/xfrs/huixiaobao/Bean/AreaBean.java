package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by dxs on 2017/9/6.
 */

public class AreaBean implements Parcelable {

    private int id;
    private String name;
    private String admin;
    private String phone;
    private String address;
    private String notes;
    private int is_employee;
    private int if_repeat;
    private String parent_id;
    private List<KidBeanX> kid;
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
    public String getAdmin() {
        return admin;
    }
    public void setAdmin(String admin) {
        this.admin = admin;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public int getIs_employee() {
        return is_employee;
    }
    public void setIs_employee(int is_employee) {
        this.is_employee = is_employee;
    }
    public int getIf_repeat() {
        return if_repeat;
    }
    public void setIf_repeat(int if_repeat) {
        this.if_repeat = if_repeat;
    }
    public String getParent_id() {
        return parent_id;
    }
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    public List<KidBeanX> getKid() {
        return kid;
    }
    public void setKid(List<KidBeanX> kid) {
        this.kid = kid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.admin);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.notes);
        dest.writeInt(this.is_employee);
        dest.writeInt(this.if_repeat);
        dest.writeString(this.parent_id);
        dest.writeTypedList(this.kid);
    }

    public AreaBean() {
    }

    protected AreaBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.admin = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.notes = in.readString();
        this.is_employee = in.readInt();
        this.if_repeat = in.readInt();
        this.parent_id = in.readString();
        this.kid = in.createTypedArrayList(KidBeanX.CREATOR);
    }

    public static final Parcelable.Creator<AreaBean> CREATOR = new Parcelable.Creator<AreaBean>() {
        @Override
        public AreaBean createFromParcel(Parcel source) {
            return new AreaBean(source);
        }

        @Override
        public AreaBean[] newArray(int size) {
            return new AreaBean[size];
        }
    };
}
