package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.PermissionManage;
import com.dhitoshi.xfrs.huixiaobao.model.PermissionModel;
import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */

public class PermissionPresenter implements PermissionManage.Presenter{
    private PermissionManage.View view;
    private PermissionModel model;
    public PermissionPresenter(PermissionManage.View view, Context context) {
        this.view = view;
        model=new PermissionModel(context);
    }
    @Override
    public void getGroupLists(String token, SmartRefreshLayout smartRefreshLayout) {
        model.getGroupLists(token, smartRefreshLayout, new Callback<HttpBean<List<UserRole>>>() {
            @Override
            public void get(HttpBean<List<UserRole>> httpBean) {
                view.getGroupLists(httpBean);
            }
        });
    }

    @Override
    public void deleteGroup(String id, String token, LoadingDialog dialog) {
        model.deleteGroup(id, token, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.deleteGroup(httpBean.getStatus().getMsg());
            }
        });
    }
}
