package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class ClientBean {
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
    private String hobbys;
    private String ills;
    private List<HobbyBean> hobby;
    private List<IllBean> ill;
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
    public List<HobbyBean> getHobby() {
        return hobby;
    }
    public void setHobby(List<HobbyBean> hobby) {
        this.hobby = hobby;
    }
    public List<IllBean> getIll() {
        return ill;
    }
    public void setIll(List<IllBean> ill) {
        this.ill = ill;
    }
    public String getHobbys() {
        return hobbys;
    }
    public void setHobbys(String hobbys) {
        this.hobbys = hobbys;
    }
    public String getIlls() {
        return ills;
    }
    public void setIlls(String ills) {
        this.ills = ills;
    }
}
