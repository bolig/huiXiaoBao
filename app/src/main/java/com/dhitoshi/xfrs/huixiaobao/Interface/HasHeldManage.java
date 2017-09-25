package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;

/**
 * Created by dxs on 2017/9/24.
 */

public interface HasHeldManage {
    interface View{
        void getListForPast(HttpBean<PageBean<OwnMeetBean>> httpBean);
    }
    interface Model{
        void getListForPast(String token, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<OwnMeetBean>>> callback);
    }
    interface Presenter{
        void getListForPast(String token, String page, SmartRefreshLayout smartRefreshLayout);
    }
}
