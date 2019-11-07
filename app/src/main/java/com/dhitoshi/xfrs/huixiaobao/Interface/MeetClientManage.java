package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import java.util.Map;

/**
 * Created by dxs on 2017/9/27.
 */

public interface MeetClientManage {
    interface View{
        void getCustomerList(HttpBean<HttpPageBean<MeetClientBean>> httpBean);
    }
    interface Model{
        void getCustomerList(Map<String,String> map, SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<HttpPageBean<MeetClientBean>>> callback);
    }
    interface Presenter{
        void getCustomerList(Map<String,String> map, SmartRefreshLayout smartRefreshLayout);
    }
}
