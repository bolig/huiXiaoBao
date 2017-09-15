package com.dhitoshi.xfrs.huixiaobao.Bean;

import java.util.List;

/**
 * Created by dxs on 2017/9/15.
 */

public class InfoAddVisitBean {
    private List<BaseBean> feedman;
    private List<BaseBean> feedtype;

    public List<BaseBean> getFeedman() {
        return feedman;
    }

    public void setFeedman(List<BaseBean> feedman) {
        this.feedman = feedman;
    }

    public List<BaseBean> getFeedtype() {
        return feedtype;
    }

    public void setFeedtype(List<BaseBean> feedtype) {
        this.feedtype = feedtype;
    }
}
