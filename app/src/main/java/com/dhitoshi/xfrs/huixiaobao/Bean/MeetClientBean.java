package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
/**
 * Created by dxs on 2017/9/27.
 */

public class MeetClientBean implements Parcelable {

    /**
     * id : 1
     * name : 客户1
     * idcard : 511010198005060785
     * phone : 13400000000
     * attend : ["0","1"]
     */

    private int id;
    private String name;
    private String idcard;
    private String phone;
    private List<String> attend;

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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getAttend() {
        return attend;
    }

    public void setAttend(List<String> attend) {
        this.attend = attend;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.idcard);
        dest.writeString(this.phone);
        dest.writeStringList(this.attend);
    }

    public MeetClientBean() {
    }

    protected MeetClientBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.idcard = in.readString();
        this.phone = in.readString();
        this.attend = in.createStringArrayList();
    }

    public static final Parcelable.Creator<MeetClientBean> CREATOR = new Parcelable.Creator<MeetClientBean>() {
        @Override
        public MeetClientBean createFromParcel(Parcel source) {
            return new MeetClientBean(source);
        }

        @Override
        public MeetClientBean[] newArray(int size) {
            return new MeetClientBean[size];
        }
    };
}
