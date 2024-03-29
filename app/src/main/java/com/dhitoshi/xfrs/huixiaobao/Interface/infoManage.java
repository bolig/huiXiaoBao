package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;

import java.util.List;

/**
 * Created by dxs on 2017/9/6.
 */
public interface infoManage {
    interface View{
        void getClientList(List<ClientBean> clientBeens);
        void getSelectCustomer(ScreenBean screenBean);
    }
    interface Model{
        void getClientList(String token,String type, String area, String order, String page, Callback<HttpBean<List<ClientBean>>> callback);
        void getSelectCustomer(String token,Callback<HttpBean<ScreenBean>> callback);
    }
    interface Prsenter{
        void getClientList(String token,String type, String area, String order, String page);
        void getSelectCustomer(String token);
    }
}
