package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public interface ClientManage {
    interface View{
        void getClientList(PageBean<ClientBean> pageBean);
        void getSelectCustomer(ScreenBean screenBean);
    }
    interface Model{
        void getClientList(Map<String,String> map,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<ClientBean>>> callback);
        void getSelectCustomer(String token,Callback<HttpBean<ScreenBean>> callback);
    }
    interface Prsenter{
        void getClientList(Map<String,String> map, SmartRefreshLayout smartRefreshLayout);
        void getSelectCustomer(String token);
    }
}
