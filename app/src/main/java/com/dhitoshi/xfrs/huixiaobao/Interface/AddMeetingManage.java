package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;


/**
 * Created by dxs on 2017/9/8.
 */

public interface AddMeetingManage {
    interface View{
        void  addMeeting(String result);
        void  editMeeting(String result);
        void  getListForMeeting(HttpBean<InfoAddMeetBean> httpBean);
    }
    interface Model{
        void addMeeting(AddMeetBean addMeetBean, Callback<HttpBean<MeetBean>> callback);
        void getListForMeeting(Callback<HttpBean<InfoAddMeetBean>> callback);
        void editMeeting(AddMeetBean addMeetBean,Callback<HttpBean<MeetBean>> callback);
    }
    interface Presenter{
        void addMeeting(AddMeetBean addMeetBean);
        void editMeeting(AddMeetBean addMeetBean);
        void getListForMeeting();
    }

}
