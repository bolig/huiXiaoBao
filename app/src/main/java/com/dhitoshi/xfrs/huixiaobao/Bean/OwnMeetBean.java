package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/24.
 */

public class OwnMeetBean implements Parcelable {

    /**
     * id : 3
     * type : {"id":2,"img":"","name":"测试会议","starttime":"2017-09-11 00:00:00","endtime":"2017-09-11 00:00:00","cost":"120","area":"1,2,3","body":"149"}
     * location : 预计地点
     * starttime : 2020-09-12 00:00:00
     * endtime : 2020-09-14 00:00:00
     * note : 备注信息
     * days : 2
     */
    private int id;
    private TypeBean type;
    private String location;
    private String starttime;
    private String endtime;
    private String note;
    private int days;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public TypeBean getType() {
        return type;
    }
    public void setType(TypeBean type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.type, flags);
        dest.writeString(this.location);
        dest.writeString(this.starttime);
        dest.writeString(this.endtime);
        dest.writeString(this.note);
        dest.writeInt(this.days);
    }

    public OwnMeetBean() {
    }

    protected OwnMeetBean(Parcel in) {
        this.id = in.readInt();
        this.type = in.readParcelable(TypeBean.class.getClassLoader());
        this.location = in.readString();
        this.starttime = in.readString();
        this.endtime = in.readString();
        this.note = in.readString();
        this.days = in.readInt();
    }

    public static final Parcelable.Creator<OwnMeetBean> CREATOR = new Parcelable.Creator<OwnMeetBean>() {
        @Override
        public OwnMeetBean createFromParcel(Parcel source) {
            return new OwnMeetBean(source);
        }

        @Override
        public OwnMeetBean[] newArray(int size) {
            return new OwnMeetBean[size];
        }
    };
}
