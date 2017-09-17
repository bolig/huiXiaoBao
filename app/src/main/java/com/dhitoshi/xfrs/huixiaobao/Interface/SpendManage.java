package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface SpendManage {
    interface View{
        void getSpendingLists(PageBean<SpendBean> pageBean);
    }
    interface Model{
        void getSpendingLists(String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<SpendBean>>> callback);
    }
    interface Prsenter{
        void getSpendingLists(String userid, String page,SmartRefreshLayout smartRefreshLayout);
    }
}
