package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public interface UserManage {
    interface View{
        void getUserList(HttpBean<PageBean<UserBean>> httpBean);
    }
    interface Model{
        void getUserList(String id, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<UserBean>>> callback);
    }
    interface Presenter{
        void getUserList(String id, SmartRefreshLayout smartRefreshLayout);
    }
}
