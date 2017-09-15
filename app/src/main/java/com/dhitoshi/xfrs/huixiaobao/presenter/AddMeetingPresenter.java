package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddMeetingModel;
/**
 * Created by dxs on 2017/9/15.
 */
public class AddMeetingPresenter implements AddMeetingManage.Presenter{
    private AddMeetingManage.View view;
    private AddMeetingModel model;
    public AddMeetingPresenter(AddMeetingManage.View view) {
        this.view = view;
        model=new AddMeetingModel();
    }
    @Override
    public void addMeeting(AddMeetBean addMeetBean) {
        model.addMeeting(addMeetBean, new Callback<HttpBean<MeetBean>>() {
            @Override
            public void get(HttpBean<MeetBean> httpBean) {
                view.addMeeting(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void editMeeting(AddMeetBean addMeetBean) {
        model.editMeeting(addMeetBean, new Callback<HttpBean<MeetBean>>() {
            @Override
            public void get(HttpBean<MeetBean> httpBean) {
                view.editMeeting(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getListForMeeting() {
        model.getListForMeeting(new Callback<HttpBean<InfoAddMeetBean>>() {
            @Override
            public void get(HttpBean<InfoAddMeetBean> httpBean) {
                view.getListForMeeting(httpBean);
            }
        });
    }
}
