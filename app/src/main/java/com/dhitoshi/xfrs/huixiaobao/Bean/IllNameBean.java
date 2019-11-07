package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/6.
 */

public class IllNameBean implements Parcelable {
    private String illname;
    public String getIllname() {
        return illname;
    }
    public void setIllname(String illname) {
        this.illname = illname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.illname);
    }

    public IllNameBean() {
    }

    protected IllNameBean(Parcel in) {
        this.illname = in.readString();
    }

    public static final Creator<IllNameBean> CREATOR = new Creator<IllNameBean>() {
        @Override
        public IllNameBean createFromParcel(Parcel source) {
            return new IllNameBean(source);
        }

        @Override
        public IllNameBean[] newArray(int size) {
            return new IllNameBean[size];
        }
    };
}
