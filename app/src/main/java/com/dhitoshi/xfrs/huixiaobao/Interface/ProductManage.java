package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductManage {
    interface View{
        void getItem(HttpBean<PageBean<ProductBean>> httpBean);
    }
    interface Model{
        void getItem(SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<PageBean<ProductBean>>> callback);
    }
    interface Presenter{
        void getItem(SmartRefreshLayout smartRefreshLayout);
    }
}
