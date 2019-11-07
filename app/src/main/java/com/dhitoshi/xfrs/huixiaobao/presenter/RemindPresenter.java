package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RemindBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.RemindManage;
import com.dhitoshi.xfrs.huixiaobao.model.RemindModel;

/**
 * Created by dxs on 2017/9/6.
 */
public class RemindPresenter implements RemindManage.Presenter{
    private RemindManage.View view;
    private RemindModel remindModel;
    public RemindPresenter(RemindManage.View view, Context context) {
        this.view = view;
        remindModel=new RemindModel(context);
    }
    @Override
    public void getRemind(String token, SmartRefreshLayout refreshLayout) {
        remindModel.getRemind(token, refreshLayout, new Callback<HttpBean<RemindBean>>() {
            @Override
            public void get(HttpBean<RemindBean> httpBean) {
                view.getRemind(httpBean);
            }
        });
    }
}
