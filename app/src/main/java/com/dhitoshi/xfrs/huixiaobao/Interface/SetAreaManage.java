package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.List;
/**
 * Created by dxs on 2017/9/8.
 */
public interface SetAreaManage {
    interface View{
        void getAreaLists(HttpBean<List<AreaBean>> httpBean);
        void deleteArea(String result);
    }
    interface Model{
        void getAreaLists(String token,SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<List<AreaBean>>> callback);
        void deleteArea(String id, String token, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getAreaLists(String token,SmartRefreshLayout smartRefreshLayout);
        void deleteArea(String id,String token,LoadingDialog dialog);
    }

}
