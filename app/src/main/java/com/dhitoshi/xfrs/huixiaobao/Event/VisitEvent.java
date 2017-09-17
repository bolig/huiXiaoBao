package com.dhitoshi.xfrs.huixiaobao.Event;
/**
 * Created by dxs on 2017/9/17.
 */
public class VisitEvent {
    //1-新增 编辑 刷新
    private int state;
    public VisitEvent(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}
