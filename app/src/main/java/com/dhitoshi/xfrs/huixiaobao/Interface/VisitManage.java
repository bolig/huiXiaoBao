package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface VisitManage {
    interface View{
        void getFeedbackLists(HttpPageBean<VisitBean> httpPageBean);
    }
    interface Model{
        void getFeedbackLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<VisitBean>>> callback);
    }
    interface Prsenter{
        void getFeedbackLists(String token,String userid,String page,SmartRefreshLayout smartRefreshLayout);
    }
}
