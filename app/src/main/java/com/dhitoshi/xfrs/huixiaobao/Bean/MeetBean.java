package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/7.
 */

public class MeetBean implements Parcelable {

    /**
     * id : 1
     * userid : 13
     * createtime : 2017-09-06 00:00:00
     * type : 联谊会
     * salesman : test
     * usertype : 全部
     * attend : 1
     * body : 会议主题
     * notes : 备注
     */

    private int id;
    private String userid;
    private String createtime;
    private String type;
    private String salesman;
    private String usertype;
    private String attend;
    private String body;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        dest.writeString(this.type);
        dest.writeString(this.salesman);
        dest.writeString(this.usertype);
        dest.writeString(this.attend);
        dest.writeString(this.body);
        dest.writeString(this.notes);
    }

    public MeetBean() {
    }

    protected MeetBean(Parcel in) {
        this.id = in.readInt();
        this.userid = in.readString();
        this.createtime = in.readString();
        this.type = in.readString();
        this.salesman = in.readString();
        this.usertype = in.readString();
        this.attend = in.readString();
        this.body = in.readString();
        this.notes = in.readString();
    }

    public static final Parcelable.Creator<MeetBean> CREATOR = new Parcelable.Creator<MeetBean>() {
        @Override
        public MeetBean createFromParcel(Parcel source) {
            return new MeetBean(source);
        }

        @Override
        public MeetBean[] newArray(int size) {
            return new MeetBean[size];
        }
    };
}
