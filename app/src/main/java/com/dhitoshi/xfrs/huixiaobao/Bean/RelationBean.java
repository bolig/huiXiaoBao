package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dxs on 2017/9/7.
 */

public class RelationBean implements Parcelable {


    /**
     * id : 2
     * userid : 13
     * name : 李庆
     * sex : 男
     * relation : 父子
     * birthday : 2014-09-12 00:00:00
     * phone : 18380419494
     * telephone : 223466780
     * email : 6742685468@qq.com
     * company : work
     * position : null
     * createtime : 1970-01-01 08:00:00
     * notes : 此地喝了饿了
     */

    private int id;
    private String userid;
    private String name;
    private String sex;
    private String relation;
    private String birthday;
    private String phone;
    private String telephone;
    private String email;
    private String company;
    private String position;
    private String createtime;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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
        dest.writeString(this.name);
        dest.writeString(this.sex);
        dest.writeString(this.relation);
        dest.writeString(this.birthday);
        dest.writeString(this.phone);
        dest.writeString(this.telephone);
        dest.writeString(this.email);
        dest.writeString(this.company);
        dest.writeString(this.position);
        dest.writeString(this.createtime);
        dest.writeString(this.notes);
    }

    public RelationBean() {
    }

    protected RelationBean(Parcel in) {
        this.id = in.readInt();
        this.userid = in.readString();
        this.name = in.readString();
        this.sex = in.readString();
        this.relation = in.readString();
        this.birthday = in.readString();
        this.phone = in.readString();
        this.telephone = in.readString();
        this.email = in.readString();
        this.company = in.readString();
        this.position = in.readString();
        this.createtime = in.readString();
        this.notes = in.readString();
    }

    public static final Parcelable.Creator<RelationBean> CREATOR = new Parcelable.Creator<RelationBean>() {
        @Override
        public RelationBean createFromParcel(Parcel source) {
            return new RelationBean(source);
        }

        @Override
        public RelationBean[] newArray(int size) {
            return new RelationBean[size];
        }
    };
}
