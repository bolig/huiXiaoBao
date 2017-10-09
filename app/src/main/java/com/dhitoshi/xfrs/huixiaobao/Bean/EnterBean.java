package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/10/9.
 */
public class EnterBean implements Parcelable {
    private String idcard;
    private String name;

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idcard);
        dest.writeString(this.name);
    }

    public EnterBean() {
    }

    protected EnterBean(Parcel in) {
        this.idcard = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<EnterBean> CREATOR = new Parcelable.Creator<EnterBean>() {
        @Override
        public EnterBean createFromParcel(Parcel source) {
            return new EnterBean(source);
        }

        @Override
        public EnterBean[] newArray(int size) {
            return new EnterBean[size];
        }
    };
}
