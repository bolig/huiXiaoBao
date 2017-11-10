package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SearchClientManage;
import com.dhitoshi.xfrs.huixiaobao.model.SearchClientModel;

/**
 * Created by dxs on 2017/9/16.
 */

public class SearchClientPresenter implements SearchClientManage.Prsenter{
    private SearchClientManage.View view;
    private SearchClientModel model;
    public SearchClientPresenter(SearchClientManage.View view, Context context) {
        this.view = view;
        model=new SearchClientModel(context);
    }
    @Override
    public void searchClientList(String token,String search, String page, SmartRefreshLayout smartRefreshLayout) {
        model.searchClientList(token,search, page, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<ClientBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<ClientBean>> httpBean) {
                view.searchClientList(httpBean.getData());
            }
        });
    }
}
