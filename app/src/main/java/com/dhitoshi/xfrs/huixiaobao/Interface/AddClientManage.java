package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddClientManage {
    interface View{
        void addClient(String result);
        void editClient(String result);
        void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean);
        void checkRepeat(String result);
        void getAreaLists(HttpBean<List<AreaBean>> httpBean);
    }
    interface Model{
        void addClient(AddClientBean addClientBean, Callback<HttpBean<ClientBean>> callback);
        void editClient(AddClientBean addClientBean,Callback<HttpBean<ClientBean>> callback);
        void getInfoForAdd(Callback<HttpBean<InfoAddClientBean>> callback);
        void checkRepeat( String area,String phone,String id,Callback<HttpBean<Object>> callback);
        void getAreaLists(Callback<HttpBean<List<AreaBean>>> callback);
    }
    interface Presenter{
        void addClient(AddClientBean addClientBean);
        void editClient(AddClientBean addClientBean);
        void getInfoForAdd();
        void checkRepeat( String area,String phone,String id);
        void getAreaLists();
    }

}
