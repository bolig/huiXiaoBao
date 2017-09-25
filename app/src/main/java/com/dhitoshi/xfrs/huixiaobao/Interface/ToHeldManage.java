package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
/**
 * Created by dxs on 2017/9/24.
 */

public interface ToHeldManage {
    interface View{
        void getListForFuture(HttpBean<PageBean<OwnMeetBean>> httpBean);
    }
    interface Model{
        void getListForFuture(String token, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<OwnMeetBean>>> callback);
    }
    interface Presenter{
        void getListForFuture(String token, String page, SmartRefreshLayout smartRefreshLayout);
    }
}
