package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddClientManage {
    interface View{
        void addClient(String result);
        void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean);
        void checkRepeat(String result);
    }
    interface Model{
        void addClient(ClientBean clientBean,Callback<HttpBean<ClientBean>> callback);
        void getInfoForAdd(Callback<HttpBean<InfoAddClientBean>> callback);
        void checkRepeat( String area,String phone,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addClient(ClientBean clientBean);
        void getInfoForAdd();
        void checkRepeat( String area,String phone);
    }

}
