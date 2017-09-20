package com.dhitoshi.xfrs.huixiaobao.Event;

import java.util.Map;

/**
 * Created by dxs on 2017/9/17.
 */
public class AddAreaOneEvent {
    //1-新增 编辑 刷新
    private int state;
    private Map<String,String> map;
    public AddAreaOneEvent(int state) {
        this.state = state;
    }
    public int getState() {
        return state;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public AddAreaOneEvent(int state, Map<String, String> map) {
        this.state = state;
        this.map = map;
    }
}
