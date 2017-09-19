package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddProductModel;
import com.dhitoshi.xfrs.huixiaobao.model.AddProductTypeModel;
import com.dhitoshi.xfrs.huixiaobao.view.AddProductType;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddProductTypePresenter implements AddProductTypeManage.Presenter{
    private AddProductTypeManage.View view;
    private AddProductTypeModel model;
    public AddProductTypePresenter(AddProductTypeManage.View view, Context context) {
        this.view = view;
        model=new AddProductTypeModel(context);
    }

    @Override
    public void addItemType(String token, String name, LoadingDialog dialog) {
        model.addItemType(token,name, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addItemType(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editItemType(String id, String token, String name, LoadingDialog dialog) {
        model.editItemType(id,token,name, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editItemType(httpBean.getStatus().getMsg());
            }
        });
    }
}
