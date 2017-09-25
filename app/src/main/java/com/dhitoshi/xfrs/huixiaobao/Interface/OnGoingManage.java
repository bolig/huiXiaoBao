package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;

/**
 * Created by dxs on 2017/9/24.
 */

public interface OnGoingManage {
    interface View{
        void getMeetForNow(HttpBean<PageBean<ApplyMeetBean>> httpBean);
    }
    interface Model{
        void getMeetForNow(String token, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<ApplyMeetBean>>> callback);
    }
    interface Presenter{
        void getMeetForNow(String token, String page, SmartRefreshLayout smartRefreshLayout);
    }
}
