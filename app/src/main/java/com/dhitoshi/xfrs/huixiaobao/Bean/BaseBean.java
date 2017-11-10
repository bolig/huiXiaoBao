package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by dxs on 2017/9/15.
 */
public class BaseBean implements Parcelable {
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
    public BaseBean() {
    }
    public BaseBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
    protected BaseBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }
    public static final Parcelable.Creator<BaseBean> CREATOR = new Parcelable.Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };
}
