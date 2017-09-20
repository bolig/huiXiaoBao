package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddPermissionManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddPermissionModel;
import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddPermissionPresenter implements AddPermissionManage.Presenter{
    private AddPermissionManage.View view;
    private AddPermissionModel model;

    public AddPermissionPresenter(AddPermissionManage.View view, Context context) {
        this.view = view;
        model=new AddPermissionModel(context);
    }

    @Override
    public void addGroup(Map<String, String> map, LoadingDialog dialog) {
        model.addGroup(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addGroup(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editGroup(Map<String, String> map, LoadingDialog dialog) {
        model.editGroup(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editGroup(httpBean.getStatus().getMsg());
            }
        });
    }
}
