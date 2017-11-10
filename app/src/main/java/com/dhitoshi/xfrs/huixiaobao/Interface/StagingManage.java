package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;

/**
 * Created by dxs on 2017/9/24.
 */

public interface StagingManage {
    interface View{
        void getListForNow(HttpBean<HttpPageBean<OwnMeetBean>> httpBean);
    }
    interface Model{
        void getListForNow(String token, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<OwnMeetBean>>> callback);
    }
    interface Presenter{
        void getListForNow(String token, String page, SmartRefreshLayout smartRefreshLayout);
    }
}
