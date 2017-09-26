package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MoreMeetInfoBean;


import retrofit2.http.Query;

/**
 * Created by dxs on 2017/9/8.
 */

public interface MoreMeetInfoManage {
    interface View{
        void getArticleBody(HttpBean<MoreMeetInfoBean> httpBean);
    }
    interface Model{
        void getArticleBody(@Query("aid") String aid, @Query("id") String id, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<MoreMeetInfoBean>> callback);
    }
    interface Presenter{
        void getArticleBody(@Query("aid") String aid, @Query("id") String id, SmartRefreshLayout smartRefreshLayout);
    }

}
