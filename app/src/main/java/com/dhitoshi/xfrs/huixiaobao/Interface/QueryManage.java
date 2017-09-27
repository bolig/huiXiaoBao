package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;

/**
 * Created by dxs on 2017/9/26.
 */
public interface QueryManage {
    interface View{
        void getListForSearch(HttpBean<InfoQuery> httpBean);
    }
    interface Model{
        void getListForSearch(String token,Callback<HttpBean<InfoQuery>> callback);
    }
    interface Presenter{
        void getListForSearch(String token);
    }
}
