package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface GiftManage {
    interface View{
        void getGiftLists(HttpPageBean<GiftBean> httpPageBean);
    }
    interface Model{
        void getGiftLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<GiftBean>>> callback);
    }
    interface Prsenter{
        void getGiftLists(String token,String userid, String page,SmartRefreshLayout smartRefreshLayout);
    }
}
