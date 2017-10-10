package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class ClientBean implements Parcelable {
    /**
     * id : 13
     * name : kehu
     * sex : 男
     * birthday : 2017-05-13
     * phone : 13500000000
     * vip_id : v123456
     * area : 1
     * telephone : 0284567894
     * email : 123456@163.com
     * position : 中层经理
     * address : 地址
     * company : 公司
     * company_phone : 123456789
     * company_address : 单位地址
     * entryman : 录入人
     * notes : 备注
     * type : 全部
     * createtime : 2017-09-05 21:16:29
     * totalnum : 0
     * buytime : 1970-01-01 08:00:00
     * backtime : 1970-01-01 08:00:00
     * hobby : [{"hobbyname":"购物"},{"hobbyname":"看书"},{"hobbyname":"下棋"}]
     * ill : [{"illname":"糖尿病"},{"illname":"高血压"},{"illname":"高血脂"}]
     */
    private int id;
    private String name;
    private String sex;
    private String birthday;
    private String phone;
    private String head;
    private String vip_id;
    private String area;
    private String telephone;
    private String email;
    private String position;
    private String address;
    private String company;
    private String company_phone;
    private String company_address;
    private String entryman;
    private String notes;
    private String type;
    private String createtime;
    private String totalnum;
    private String buytime;
    private String backtime;
    private List<HobbyNameBean> hobby;
    private List<IllNameBean> ill;
    private String area_id;
    private String idcard;
    private boolean isSelected=false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public String getArea_id() {
        return area_id;
    }
    public void setArea_id(String area_id) {
        this.area_id = area_id;
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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
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
    public String getVip_id() {
        return vip_id;
    }
    public void setVip_id(String vip_id) {
        this.vip_id = vip_id;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany_phone() {
        return company_phone;
    }
    public void setCompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }
    public String getCompany_address() {
        return company_address;
    }
    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }
    public String getEntryman() {
        return entryman;
    }
    public void setEntryman(String entryman) {
        this.entryman = entryman;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getTotalnum() {
        return totalnum;
    }
    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }
    public String getBuytime() {
        return buytime;
    }
    public void setBuytime(String buytime) {
        this.buytime = buytime;
    }
    public String getBacktime() {
        return backtime;
    }
    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }
    public List<HobbyNameBean> getHobby() {
        return hobby;
    }
    public void setHobby(List<HobbyNameBean> hobby) {
        this.hobby = hobby;
    }
    public List<IllNameBean> getIll() {
        return ill;
    }
    public void setIll(List<IllNameBean> ill) {
        this.ill = ill;
    }

    public ClientBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.sex);
        dest.writeString(this.birthday);
        dest.writeString(this.phone);
        dest.writeString(this.head);
        dest.writeString(this.vip_id);
        dest.writeString(this.area);
        dest.writeString(this.telephone);
        dest.writeString(this.email);
        dest.writeString(this.position);
        dest.writeString(this.address);
        dest.writeString(this.company);
        dest.writeString(this.company_phone);
        dest.writeString(this.company_address);
        dest.writeString(this.entryman);
        dest.writeString(this.notes);
        dest.writeString(this.type);
        dest.writeString(this.createtime);
        dest.writeString(this.totalnum);
        dest.writeString(this.buytime);
        dest.writeString(this.backtime);
        dest.writeTypedList(this.hobby);
        dest.writeTypedList(this.ill);
        dest.writeString(this.area_id);
        dest.writeString(this.idcard);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected ClientBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.sex = in.readString();
        this.birthday = in.readString();
        this.phone = in.readString();
        this.head = in.readString();
        this.vip_id = in.readString();
        this.area = in.readString();
        this.telephone = in.readString();
        this.email = in.readString();
        this.position = in.readString();
        this.address = in.readString();
        this.company = in.readString();
        this.company_phone = in.readString();
        this.company_address = in.readString();
        this.entryman = in.readString();
        this.notes = in.readString();
        this.type = in.readString();
        this.createtime = in.readString();
        this.totalnum = in.readString();
        this.buytime = in.readString();
        this.backtime = in.readString();
        this.hobby = in.createTypedArrayList(HobbyNameBean.CREATOR);
        this.ill = in.createTypedArrayList(IllNameBean.CREATOR);
        this.area_id = in.readString();
        this.idcard = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<ClientBean> CREATOR = new Creator<ClientBean>() {
        @Override
        public ClientBean createFromParcel(Parcel source) {
            return new ClientBean(source);
        }

        @Override
        public ClientBean[] newArray(int size) {
            return new ClientBean[size];
        }
    };
}
