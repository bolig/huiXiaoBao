package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;

import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductTypeManage {
    interface View{
        void getItemType(HttpBean<PageBean<BaseBean>> httpBean);
    }
    interface Model{
        void getItemType(SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<BaseBean>>> callback);
    }
    interface Presenter{
        void getItemType(SmartRefreshLayout smartRefreshLayout);
    }
}
