package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface TribeManage {
    interface View{
        void addTribe(HttpBean<TribeBean> httpBean);
        void editTribe(HttpBean<TribeBean> httpBean);
    }
    interface Model{
        void addTribe(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<TribeBean>> callback);
        void editTribe(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<TribeBean>> callback);
    }
    interface Presenter{
        void addTribe(Map<String,String> map, LoadingDialog dialog);
        void editTribe(Map<String,String> map, LoadingDialog dialog);
    }

}
