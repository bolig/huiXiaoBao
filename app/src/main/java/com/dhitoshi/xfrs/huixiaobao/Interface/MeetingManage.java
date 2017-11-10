package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface MeetingManage {
    interface View{
        void getMeetingLists(HttpPageBean<MeetBean> httpPageBean);
    }
    interface Model{
        void getMeetingLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<MeetBean>>> callback);
    }
    interface Prsenter{
        void getMeetingLists(String token,String userid, String page,SmartRefreshLayout smartRefreshLayout);
    }
}
