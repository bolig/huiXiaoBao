package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.model.ProductTypeModel;
/**
 * Created by dxs on 2017/9/16.
 */
public class ProductTypePresenter implements ProductTypeManage.Presenter{
    private ProductTypeManage.View view;
    private ProductTypeModel model;
    public ProductTypePresenter(ProductTypeManage.View view, Context context) {
        this.view = view;
        model=new ProductTypeModel(context);
    }
    @Override
    public void getItemType(String token,String page,SmartRefreshLayout smartRefreshLayout) {
       model.getItemType(token,page,smartRefreshLayout, new Callback<HttpBean<HttpPageBean<BaseBean>>>() {
           @Override
           public void get(HttpBean<HttpPageBean<BaseBean>> httpBean) {
               view.getItemType(httpBean);
           }
       });
    }

    @Override
    public void deleteItemType(String token, String id, LoadingDialog dialog) {
        model.deleteItemType(token, id, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.deleteItemType(httpBean.getStatus().getMsg());
            }
        });
    }

}
