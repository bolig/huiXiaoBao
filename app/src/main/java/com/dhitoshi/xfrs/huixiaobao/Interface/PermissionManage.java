package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */
public interface PermissionManage {
    interface View{
        void getGroupLists(HttpBean<List<UserRole>> httpBean);
        void deleteGroup(String result);
    }
    interface Model{
        void getGroupLists(String token,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<List<UserRole>>> callback);
        void deleteGroup(String id, String token, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getGroupLists(String token,SmartRefreshLayout smartRefreshLayout);
        void deleteGroup(String id, String token, LoadingDialog dialog);
    }
}
