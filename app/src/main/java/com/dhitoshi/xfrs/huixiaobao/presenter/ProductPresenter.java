package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductManage;
import com.dhitoshi.xfrs.huixiaobao.model.ProductModel;
import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */

public class ProductPresenter implements ProductManage.Presenter{
    private ProductManage.View view;
    private ProductModel model;
    public ProductPresenter(ProductManage.View view, Context context) {
        this.view = view;
        model=new ProductModel(context);
    }
    @Override
    public void getItem(SmartRefreshLayout smartRefreshLayout) {
        model.getItem(smartRefreshLayout,new Callback<HttpBean<PageBean<ProductBean>>>() {
            @Override
            public void get(HttpBean<PageBean<ProductBean>> httpBean) {
                view.getItem(httpBean);
            }
        });
    }
}
