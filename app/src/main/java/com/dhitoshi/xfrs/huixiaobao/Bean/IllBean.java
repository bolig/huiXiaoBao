package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/6.
 */

public class IllBean implements Parcelable {
    private int id;
    private String name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public IllBean() {
    }

    protected IllBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<IllBean> CREATOR = new Parcelable.Creator<IllBean>() {
        @Override
        public IllBean createFromParcel(Parcel source) {
            return new IllBean(source);
        }

        @Override
        public IllBean[] newArray(int size) {
            return new IllBean[size];
        }
    };
}
