package com.dhitoshi.xfrs.huixiaobao.Event;
/**
 * Created by dxs on 2017/9/17.
 */
public class ClientEvent {
    //1-新增 编辑 刷新
    private int state;
    public ClientEvent(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
