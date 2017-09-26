package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddProductManage {
    interface View{
        void addItem(String result);
        void editItem(String result);
    }
    interface Model{
        void addItem(String token,AddProductBean addProductBean, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
        void editItem(String token,AddProductBean addProductBean,LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addItem(String token,AddProductBean addProductBean,LoadingDialog dialog);
        void editItem(String token,AddProductBean addProductBean,LoadingDialog dialog);
    }
}
