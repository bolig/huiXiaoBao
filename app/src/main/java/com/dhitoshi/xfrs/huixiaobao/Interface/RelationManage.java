package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface RelationManage {
    interface View{
        void getRelationLists(PageBean<RelationBean> pageBean);
    }
    interface Model{
        void getRelationLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<RelationBean>>> callback);
    }
    interface Prsenter{
        void getRelationLists(String token,String userid, String page,SmartRefreshLayout smartRefreshLayout);
    }
}
