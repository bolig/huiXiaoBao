package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;
/**
 * Created by dxs on 2017/9/8.
 */
public interface AddAreaManage {
    interface View{
        void addArea(HttpBean<KidBean> httpBean);
        void editArea(String result);
    }
    interface Model{
        void addArea(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<KidBean>> callback);
        void editArea(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addArea(Map<String,String> map, LoadingDialog dialog);
        void editArea(Map<String,String> map, LoadingDialog dialog);
    }

}
