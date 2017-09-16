package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public interface ProductManage {
    interface View{
        void getItem(HttpBean<List<ProductBean>> httpBean);
    }
    interface Model{
        void getItem(Callback<HttpBean<List<ProductBean>>> callback);
    }
    interface Presenter{
        void getItem();
    }
}
