package com.dhitoshi.xfrs.huixiaobao.Bean;
/**
 * Created by dxs on 2017/9/14.
 */
public class SexBean {
    private String id;
    private String name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public SexBean(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
