package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;

import java.util.List;

/**
 * Created by dxs on 2017/9/6.
 */
public interface ClientManage {
    interface View{
        void getClientList(List<ClientBean> clientBeens);
    }
    interface Model{
        void getClientList(String type, String area, String order, String page, Callback<List<ClientBean>> callback);
    }
    interface Prsenter{
        void getClientList(String type,String area,String order,String page);
    }
}
