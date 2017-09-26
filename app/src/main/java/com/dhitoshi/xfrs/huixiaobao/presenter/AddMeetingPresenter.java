package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddMeetingModel;
/**
 * Created by dxs on 2017/9/15.
 */
public class AddMeetingPresenter implements AddMeetingManage.Presenter{
    private AddMeetingManage.View view;
    private AddMeetingModel model;
    public AddMeetingPresenter(AddMeetingManage.View view, Context context) {
        this.view = view;
        model=new AddMeetingModel(context);
    }
    @Override
    public void addMeeting(String token,AddMeetBean addMeetBean, LoadingDialog dialog) {
        model.addMeeting(token,addMeetBean,dialog, new Callback<HttpBean<MeetBean>>() {
            @Override
            public void get(HttpBean<MeetBean> httpBean) {
                view.addMeeting(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void editMeeting(String token,AddMeetBean addMeetBean, LoadingDialog dialog) {
        model.editMeeting(token,addMeetBean,dialog, new Callback<HttpBean<MeetBean>>() {
            @Override
            public void get(HttpBean<MeetBean> httpBean) {
                view.editMeeting(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getListForMeeting(String token) {
        model.getListForMeeting(token,new Callback<HttpBean<InfoAddMeetBean>>() {
            @Override
            public void get(HttpBean<InfoAddMeetBean> httpBean) {
                view.getListForMeeting(httpBean);
            }
        });
    }
}
