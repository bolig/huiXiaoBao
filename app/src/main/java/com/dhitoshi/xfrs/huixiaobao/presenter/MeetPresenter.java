package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingManage;
import com.dhitoshi.xfrs.huixiaobao.model.MeetModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class MeetPresenter implements MeetingManage.Prsenter{
    private MeetingManage.View view;
    private MeetModel meetModel;
    public MeetPresenter(MeetingManage.View view, Context context) {
        this.view = view;
        meetModel=new MeetModel(context);
    }
    @Override
    public void getMeetingLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout) {
        meetModel.getMeetingLists(token,userid, page,smartRefreshLayout, new Callback<HttpBean<PageBean<MeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<MeetBean>> httpBean) {
                view.getMeetingLists(httpBean.getData());
            }
        });
    }
}
