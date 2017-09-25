package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;

/**
 * Created by dxs on 2017/9/24.
 */

public interface StagingManage {
    interface View{
        void getListForNow(HttpBean<PageBean<OwnMeetBean>> httpBean);
    }
    interface Model{
        void getListForNow(String token, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<OwnMeetBean>>> callback);
    }
    interface Presenter{
        void getListForNow(String token, String page, SmartRefreshLayout smartRefreshLayout);
    }
}
