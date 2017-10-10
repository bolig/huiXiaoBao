package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by dxs on 2017/9/6.
 */

public class HobbyBean implements Parcelable {
    private int id;
    private String name;
    private boolean isSelect;
    public boolean isSelect() {
        return isSelect;
    }
    public void setSelect(boolean select) {
        isSelect = select;
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

    public HobbyBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    protected HobbyBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<HobbyBean> CREATOR = new Creator<HobbyBean>() {
        @Override
        public HobbyBean createFromParcel(Parcel source) {
            return new HobbyBean(source);
        }

        @Override
        public HobbyBean[] newArray(int size) {
            return new HobbyBean[size];
        }
    };
}
