package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/10/9.
 */

public class VersionBean {

    /**
     * id : 1
     * code : 2
     * name : 1.01
     * url : http://119.29.144.125/%E6%85%A7%E9%94%80%E5%AE%9D_v1.0.1_2.apk
     * description : 测shi
     * createtime : 1507540220
     */

    private int id;
    private int code;
    private String name;
    private String url;
    private String description;
    private int createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatetime() {
        return createtime;
    }

    public void setCreatetime(int createtime) {
        this.createtime = createtime;
    }
}
