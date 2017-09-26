package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddProductModel;

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
    public void addItem(String token,AddProductBean addProductBean, LoadingDialog dialog) {
        model.addItem(token,addProductBean, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addItem(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void editItem(String token,AddProductBean addProductBean, LoadingDialog dialog) {
        model.editItem(token,addProductBean, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editItem(httpBean.getStatus().getMsg());
            }
        });
    }
}
