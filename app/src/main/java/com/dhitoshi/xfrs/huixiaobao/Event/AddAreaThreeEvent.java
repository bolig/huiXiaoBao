package com.dhitoshi.xfrs.huixiaobao.Event;

import java.util.Map;

/**
 * Created by dxs on 2017/9/17.
 */
public class AddAreaThreeEvent {
    //1-新增 编辑 刷新
    private int state;
    private Map<String,String> map;
    public AddAreaThreeEvent(int state) {
        this.state = state;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public AddAreaThreeEvent(int state, Map<String, String> map) {
        this.state = state;
        this.map = map;
    }

    public int getState() {
        return state;
    }
}
