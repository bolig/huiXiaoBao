package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddClientManage {
    interface View{
        void addClient(HttpBean<ClientBean> httpBean);
        void editClient(HttpBean<ClientBean> httpBean);
        void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean);
        void checkRepeat(String result);
    }
    interface Model{
        void addClient(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<ClientBean>> callback);
        void editClient(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<ClientBean>> callback);
        void getInfoForAdd(String token,Callback<HttpBean<InfoAddClientBean>> callback);
        void checkRepeat(String token,LoadingDialog dialog,String area,String phone,String id,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addClient(Map<String,String> map, LoadingDialog dialog);
        void editClient(Map<String,String> map, LoadingDialog dialog);
        void getInfoForAdd(String token);
        void checkRepeat(String token,LoadingDialog dialog,String area,String phone,String id);
    }

}
