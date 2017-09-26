package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;

import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public interface SearchClientManage {
    interface View{
        void searchClientList(PageBean<ClientBean> pageBean);
    }
    interface Model{
        void searchClientList(String token,String search,String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<ClientBean>>> callback);
    }
    interface Prsenter{
        void searchClientList(String token,String search,String page, SmartRefreshLayout smartRefreshLayout);

    }
}
