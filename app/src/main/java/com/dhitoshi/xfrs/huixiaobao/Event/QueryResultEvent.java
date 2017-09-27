package com.dhitoshi.xfrs.huixiaobao.Event;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;

/**
 * Created by dxs on 2017/9/17.
 */
public class QueryResultEvent {
    //1-新增 编辑 刷新
    private int state;
    private ClientBean clientBean;
    public QueryResultEvent(int state) {
        this.state = state;
    }

    public ClientBean getClientBean() {
        return clientBean;
    }
    public int getState() {
        return state;
    }

    public QueryResultEvent(int state, ClientBean clientBean) {
        this.state = state;
        this.clientBean = clientBean;
    }
}
