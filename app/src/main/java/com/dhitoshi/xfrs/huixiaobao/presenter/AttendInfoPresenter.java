package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendInfoManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AttendInfoModel;

/**
 * Created by dxs on 2017/9/6.
 */
public class AttendInfoPresenter implements AttendInfoManage.Presenter{
    private AttendInfoManage.View view;
    private AttendInfoModel attendInfoModel;
    public AttendInfoPresenter(AttendInfoManage.View view, Context context) {
        this.view = view;
        attendInfoModel=new AttendInfoModel(context);
    }

    @Override
    public void GetaAttendList(String token, String meetingid, SmartRefreshLayout smartRefreshLayout) {
        attendInfoModel.GetaAttendList(token, meetingid, smartRefreshLayout, new Callback<HttpBean<AttendBean<Integer>>>() {
            @Override
            public void get(HttpBean<AttendBean<Integer>> attendBeanHttpBean) {
                view.GetaAttendList(attendBeanHttpBean);
            }
        });
    }
}
