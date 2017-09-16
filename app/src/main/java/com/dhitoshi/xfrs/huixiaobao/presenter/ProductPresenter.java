package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
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
    public ProductPresenter(ProductManage.View view) {
        this.view = view;
        model=new ProductModel();
    }
    @Override
    public void getItem() {
        model.getItem(new Callback<HttpBean<List<ProductBean>>>() {
            @Override
            public void get(HttpBean<List<ProductBean>> httpBean) {
                view.getItem(httpBean);
            }
        });
    }
}
