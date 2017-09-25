package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/22.
 */

public interface ContactManage {
    interface View{
        void addFast(String result);
    }
    interface Model{
        void addFast(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addFast(Map<String, String> map, LoadingDialog dialog);
    }
}
