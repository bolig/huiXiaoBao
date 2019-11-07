package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
/**
 * Created by dxs on 2017/11/10.
 */

public interface TribeCompanyManage {
    interface View{
        void getCompanyList(HttpPageBeanTwo<BaseBean> httpPageBeanTwo);
    }
    interface Model{
        void getCompanyList(String token, String page, SmartRefreshLayout refreshLayout,Callback<HttpPageBeanTwo<BaseBean>> callback);
    }
    interface Presenter{
        void getCompanyList(String token, String page, SmartRefreshLayout refreshLayout);
    }
}
