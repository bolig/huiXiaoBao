package com.dhitoshi.xfrs.huixiaobao.Event;

import java.util.Map;

/**
 * Created by dxs on 2017/9/17.
 */
public class AddAreaTwoEvent {
    //1-新增 编辑 刷新
    private int state;
    private Map<String,String> map;
    public AddAreaTwoEvent(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public AddAreaTwoEvent(int state, Map<String, String> map) {
        this.state = state;
        this.map = map;
    }
}
