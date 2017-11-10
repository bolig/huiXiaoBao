package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeCompanyManage;
import com.dhitoshi.xfrs.huixiaobao.model.MeetModel;
import com.dhitoshi.xfrs.huixiaobao.model.TribeCompanyModel;

import static com.dhitoshi.xfrs.huixiaobao.R.id.smartRefreshLayout;

/**
 * Created by dxs on 2017/9/6.
 */
public class TribeCompanyPresenter implements TribeCompanyManage.Presenter{
    private TribeCompanyManage.View view;
    private TribeCompanyModel tribeCompanyModel;
    public TribeCompanyPresenter(TribeCompanyManage.View view, Context context) {
        this.view = view;
        tribeCompanyModel=new TribeCompanyModel(context);
    }
    @Override
    public void getCompanyList(String token, String page, SmartRefreshLayout refreshLayout) {
        tribeCompanyModel.getCompanyList(token,page,refreshLayout, new Callback<HttpPageBeanTwo<BaseBean>>() {
            @Override
            public void get(HttpPageBeanTwo<BaseBean> httpPageBeanTwo ) {
                view.getCompanyList(httpPageBeanTwo);
            }
        });
    }
}
