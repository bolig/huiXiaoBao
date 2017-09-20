package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.List;
import java.util.Map;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddClientManage {
    interface View{
        void addClient(String result);
        void editClient(HttpBean<ClientBean> httpBean);
        void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean);
        void checkRepeat(String result);

    }
    interface Model{
        void addClient(AddClientBean addClientBean, LoadingDialog dialog, Callback<HttpBean<ClientBean>> callback);
        void editClient(AddClientBean addClientBean, LoadingDialog dialog,Callback<HttpBean<ClientBean>> callback);
        void getInfoForAdd(Callback<HttpBean<InfoAddClientBean>> callback);
        void checkRepeat(LoadingDialog dialog,String area,String phone,String id,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addClient(AddClientBean addClientBean, LoadingDialog dialog);
        void editClient(AddClientBean addClientBean, LoadingDialog dialog);
        void getInfoForAdd();
        void checkRepeat(LoadingDialog dialog,String area,String phone,String id);
    }

}
