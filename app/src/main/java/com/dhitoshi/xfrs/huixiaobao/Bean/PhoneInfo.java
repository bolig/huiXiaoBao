package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/9/23.
 */

public class PhoneInfo {
    private String name;
    private String number;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneInfo(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
