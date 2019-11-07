package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RemindBean;

/**
 * Created by dxs on 2017/9/8.
 */
public interface RemindManage {
    interface View{
        void getRemind(HttpBean<RemindBean> httpBean);
    }
    interface Model{
        void getRemind(String token, SmartRefreshLayout refreshLayout, Callback<HttpBean<RemindBean>> callback);
    }
    interface Presenter{
        void getRemind(String token, SmartRefreshLayout refreshLayout);
    }

}
