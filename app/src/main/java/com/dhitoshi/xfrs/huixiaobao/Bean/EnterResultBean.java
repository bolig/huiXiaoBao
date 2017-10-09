package com.dhitoshi.xfrs.huixiaobao.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/10/9.
 */
public class EnterResultBean implements Parcelable {
    private EnterBean data;
    private String msg;
    public EnterBean getData() {
        return data;
    }
    public void setData(EnterBean data) {
        this.data = data;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.msg);
    }

    public EnterResultBean() {
    }

    protected EnterResultBean(Parcel in) {
        this.data = in.readParcelable(EnterBean.class.getClassLoader());
        this.msg = in.readString();
    }

    public static final Parcelable.Creator<EnterResultBean> CREATOR = new Parcelable.Creator<EnterResultBean>() {
        @Override
        public EnterResultBean createFromParcel(Parcel source) {
            return new EnterResultBean(source);
        }

        @Override
        public EnterResultBean[] newArray(int size) {
            return new EnterResultBean[size];
        }
    };
}
