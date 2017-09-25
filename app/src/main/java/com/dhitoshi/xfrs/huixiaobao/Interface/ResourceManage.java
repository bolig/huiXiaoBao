package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ResourceBean;

/**
 * Created by dxs on 2017/9/8.
 */
public interface ResourceManage {
    interface View{
        void getMyResource(HttpBean<ResourceBean> httpBean);
    }
    interface Model{
        void getMyResource(String token, SmartRefreshLayout refreshLayout,Callback<HttpBean<ResourceBean>> callback);
    }
    interface Presenter{
        void getMyResource(String token, SmartRefreshLayout refreshLayout);
    }

}
