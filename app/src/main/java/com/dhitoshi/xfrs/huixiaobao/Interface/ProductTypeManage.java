package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductTypeManage {
    interface View{
        void getItemType(HttpBean<PageBean<BaseBean>> httpBean);
        void deleteItemType(String result);
    }
    interface Model{
        void getItemType(String page,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<BaseBean>>> callback);
        void deleteItemType(String token, String id, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getItemType(String page,SmartRefreshLayout smartRefreshLayout);
        void deleteItemType(String token,String id,LoadingDialog dialog);
    }
}
