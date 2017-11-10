package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;

/**
 * Created by dxs on 2017/9/16.
 */
public interface UserManage {
    interface View{
        void getUserList(HttpBean<HttpPageBean<UserBean>> httpBean);
    }
    interface Model{
        void getUserList(String token,String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<UserBean>>> callback);
    }
    interface Presenter{
        void getUserList(String token,String page, SmartRefreshLayout smartRefreshLayout);
    }
}
