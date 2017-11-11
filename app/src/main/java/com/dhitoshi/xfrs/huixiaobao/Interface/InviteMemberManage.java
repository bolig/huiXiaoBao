package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
/**
 * Created by dxs on 2017/11/10.
 */

public interface InviteMemberManage {
    interface View{
        void getUserList(HttpPageBeanTwo<TribeMemberBean> httpPageBeanTwo);
    }
    interface Model{
        void getUserList(String token,String area_id, String page, SmartRefreshLayout refreshLayout, Callback<HttpPageBeanTwo<TribeMemberBean>> callback);
    }
    interface Presenter{
        void getUserList(String token,String area_id,String page, SmartRefreshLayout refreshLayout);
    }
}
