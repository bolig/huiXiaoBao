package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;


/**
 * Created by dxs on 2017/9/8.
 */

public interface AddMeetingManage {
    interface View{
        void  addMeeting(String result);
        void  editMeeting(String result);
        void  getListForMeeting(HttpBean<InfoAddSpendBean> httpBean);
    }
    interface Model{
        void addMeeting(MeetBean meetBean, String userId, Callback<HttpBean<MeetBean>> callback);
        void getListForMeeting(Callback<HttpBean<InfoAddSpendBean>> callback);
        void editMeeting(MeetBean meetBean,String userId,Callback<HttpBean<MeetBean>> callback);
    }
    interface Presenter{
        void addMeeting(MeetBean meetBean, String userId);
        void editMeeting(MeetBean meetBean,String userId);
        void getListForMeeting();
    }

}
