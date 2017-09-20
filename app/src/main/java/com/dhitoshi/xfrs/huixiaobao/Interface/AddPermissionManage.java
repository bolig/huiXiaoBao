package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddPermissionManage {
    interface View{
        void addGroup(String result);
        void editGroup(String result);
    }
    interface Model{
        void addGroup(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
        void editGroup(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addGroup(Map<String,String> map, LoadingDialog dialog);
        void editGroup(Map<String,String> map, LoadingDialog dialog);
    }

}
