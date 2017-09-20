package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;

import java.util.List;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddAreaManage {
    interface View{
        void getAreaLists(HttpBean<List<AreaBean>> httpBean);
    }
    interface Model{
        void getAreaLists(SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<List<AreaBean>>> callback);
    }
    interface Presenter{
        void getAreaLists(SmartRefreshLayout smartRefreshLayout);
    }

}
