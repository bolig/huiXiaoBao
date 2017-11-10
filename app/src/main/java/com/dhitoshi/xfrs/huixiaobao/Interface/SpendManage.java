package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface SpendManage {
    interface View{
        void getSpendingLists(HttpPageBean<SpendBean> httpPageBean);
    }
    interface Model{
        void getSpendingLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<SpendBean>>> callback);
    }
    interface Prsenter{
        void getSpendingLists(String token,String userid, String page,SmartRefreshLayout smartRefreshLayout);
    }
}
