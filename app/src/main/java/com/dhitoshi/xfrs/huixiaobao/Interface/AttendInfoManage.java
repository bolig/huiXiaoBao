package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AttendInfoManage {
    interface View{
        void GetaAttendList(HttpBean<AttendBean<Integer>> httpBean);
    }
    interface Model{
        void GetaAttendList(String token,String meetingid, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<AttendBean<Integer>>> callback);
    }
    interface Presenter{
        void GetaAttendList(String token,String meetingid, SmartRefreshLayout smartRefreshLayout);
    }

}
