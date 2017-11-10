package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BulkImportBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface BulkImportManage {
    interface View{
        void getClientList(HttpPageBean<ClientBean> httpPageBean);
        void addCustomerAll(HttpBean<BulkImportBean> httpBean);
    }
    interface Model{
        void getClientList(Map<String,String> map,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<ClientBean>>> callback);
        void addCustomerAll(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<BulkImportBean>> callback);
    }
    interface Presenter{
        void getClientList(Map<String,String> map, SmartRefreshLayout smartRefreshLayout);
        void addCustomerAll(Map<String,String> map, LoadingDialog dialog);
    }

}
