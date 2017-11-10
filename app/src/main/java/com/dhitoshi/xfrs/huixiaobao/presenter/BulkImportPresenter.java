package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BulkImportBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.BulkImportManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.BulkImportModel;
import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public class BulkImportPresenter implements BulkImportManage.Presenter{
    private BulkImportManage.View view;
    private BulkImportModel model;
    public BulkImportPresenter(BulkImportManage.View view, Context context) {
        this.view = view;
        model=new BulkImportModel(context);
    }
    @Override
    public void getClientList(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getClientList(map, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<ClientBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<ClientBean>> httpBean) {
                view.getClientList(httpBean.getData());
            }
        });
    }

    @Override
    public void addCustomerAll(Map<String, String> map, LoadingDialog dialog) {
        model.addCustomerAll(map, dialog, new Callback<HttpBean<BulkImportBean>>() {
            @Override
            public void get(HttpBean<BulkImportBean> httpBean) {
                view.addCustomerAll(httpBean);
            }
        });
    }
}
