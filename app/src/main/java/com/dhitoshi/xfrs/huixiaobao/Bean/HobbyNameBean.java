package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/6.
 */

public class HobbyNameBean implements Parcelable {

    private String hobbyname;

    public String getHobbyname() {
        return hobbyname;
    }

    public void setHobbyname(String hobbyname) {
        this.hobbyname = hobbyname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hobbyname);
    }

    public HobbyNameBean() {
    }

    protected HobbyNameBean(Parcel in) {
        this.hobbyname = in.readString();
    }

    public static final Creator<HobbyNameBean> CREATOR = new Creator<HobbyNameBean>() {
        @Override
        public HobbyNameBean createFromParcel(Parcel source) {
            return new HobbyNameBean(source);
        }

        @Override
        public HobbyNameBean[] newArray(int size) {
            return new HobbyNameBean[size];
        }
    };
}
