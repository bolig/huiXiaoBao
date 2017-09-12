package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public interface ClientManage {
    interface View{
        void getClientList(PageBean<List<ClientBean>> pageBean);
        void getSelectCustomer(ScreenBean screenBean);
    }
    interface Model{
        void getClientList(Map<String,String> map, Callback<HttpBean<PageBean<List<ClientBean>>>> callback);
        void getClientList( Callback<HttpBean<PageBean<List<ClientBean>>>> callback);
        void getSelectCustomer(Callback<HttpBean<ScreenBean>> callback);
    }
    interface Prsenter{
        void getClientList(Map<String,String> map);
        void getClientList();
        void getSelectCustomer();
    }
}
