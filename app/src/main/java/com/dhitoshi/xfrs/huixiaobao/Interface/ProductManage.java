package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductManage {
    interface View{
        void getItem(HttpBean<PageBean<ProductBean>> httpBean);
        void deleteItem(String result);
    }
    interface Model{
        void getItem(String token,String page,SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<PageBean<ProductBean>>> callback);
        void deleteItem(String token, String id, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getItem(String token,String page,SmartRefreshLayout smartRefreshLayout);
        void deleteItem(String token,String id,LoadingDialog dialog);
    }
}
