package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.model.ProductTypeModel;
import java.util.List;

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
    public void getItemType(SmartRefreshLayout smartRefreshLayout) {
       model.getItemType(smartRefreshLayout, new Callback<HttpBean<PageBean<BaseBean>>>() {
           @Override
           public void get(HttpBean<PageBean<BaseBean>> httpBean) {
               view.getItemType(httpBean);
           }
       });
    }
}
