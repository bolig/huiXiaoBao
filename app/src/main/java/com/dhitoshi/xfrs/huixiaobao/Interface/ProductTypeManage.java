package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductTypeManage {
    interface View{
        void getItemType(HttpBean<HttpPageBean<BaseBean>> httpBean);
        void deleteItemType(String result);
    }
    interface Model{
        void getItemType(String token,String page,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<BaseBean>>> callback);
        void deleteItemType(String token, String id, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getItemType(String token,String page,SmartRefreshLayout smartRefreshLayout);
        void deleteItemType(String token,String id,LoadingDialog dialog);
    }
}
