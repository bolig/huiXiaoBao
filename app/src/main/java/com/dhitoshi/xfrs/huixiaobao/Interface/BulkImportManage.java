package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface BulkImportManage {
    interface View{
        void getClientList(PageBean<ClientBean> pageBean);
    }
    interface Model{
        void getClientList(Map<String,String> map,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<PageBean<ClientBean>>> callback);
    }
    interface Presenter{
        void getClientList(Map<String,String> map, SmartRefreshLayout smartRefreshLayout);
    }

}
