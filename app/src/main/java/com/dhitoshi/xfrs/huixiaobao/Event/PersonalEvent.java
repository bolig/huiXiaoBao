package com.dhitoshi.xfrs.huixiaobao.Event;
/**
 * Created by dxs on 2017/9/17.
 */
public class PersonalEvent {
    //1-修改头像 2-修改资料
    private int state;
    private String resource;
    public PersonalEvent(int state) {
        this.state = state;
    }

    public String getResource() {
        return resource;
    }
    public PersonalEvent(int state, String resource) {
        this.state = state;
        this.resource = resource;
    }
    public int getState() {
        return state;
    }
}
