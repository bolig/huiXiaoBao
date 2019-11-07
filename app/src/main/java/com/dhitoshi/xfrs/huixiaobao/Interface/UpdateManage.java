package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */
public interface UpdateManage {
    interface View{
        void resetPassword(HttpBean<Object> httpBean);
    }
    interface Model{
        void resetPassword(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void resetPassword(Map<String,String> map,LoadingDialog dialog);
    }
}
