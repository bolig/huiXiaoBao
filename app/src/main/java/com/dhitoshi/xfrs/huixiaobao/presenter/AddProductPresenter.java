package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddProductModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddProductPresenter implements AddProductManage.Presenter{
    private AddProductManage.View view;
    private AddProductModel model;
    public AddProductPresenter(AddProductManage.View view, Context context) {
        this.view = view;
        model=new AddProductModel(context);
    }
    @Override
    public void addItem(Map<String,String> map, LoadingDialog dialog) {
        model.addItem(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addItem(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void editItem(Map<String,String> map, LoadingDialog dialog) {
        model.editItem(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editItem(httpBean.getStatus().getMsg());
            }
        });
    }
}
