package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;
/**
 * Created by dxs on 2017/9/12.
 */
public interface LoginManage {
    interface View{
        void login(UserBean userBean);
    }
    interface Model{
        void login(Map<String,String> map,LoadingDialog dialog,Callback<HttpBean<UserBean>> callback);
    }
    interface Presenter{
        void login(Map<String,String> map,LoadingDialog dialog);
    }
}
