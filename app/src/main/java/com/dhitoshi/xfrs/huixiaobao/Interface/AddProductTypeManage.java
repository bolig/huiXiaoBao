package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddProductTypeManage {
    interface View{
        void addItemType(String result);
        void editItemType(String result);
    }
    interface Model{
        void addItemType(String token,String name, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
        void editItemType(String id,String token,String name, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addItemType(String token,String name, LoadingDialog dialog);
        void editItemType(String id,String token,String name, LoadingDialog dialog);
    }
}
