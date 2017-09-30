package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddProductManage {
    interface View{
        void addItem(String result);
        void editItem(String result);
    }
    interface Model{
        void addItem(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
        void editItem(Map<String,String> map,LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addItem(Map<String,String> map,LoadingDialog dialog);
        void editItem(Map<String,String> map,LoadingDialog dialog);
    }
}
