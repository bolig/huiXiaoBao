package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;


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
        void addMeeting(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<MeetBean>> callback);
        void getListForMeeting(String token,Callback<HttpBean<InfoAddMeetBean>> callback);
        void editMeeting(Map<String,String> map,LoadingDialog dialog,Callback<HttpBean<MeetBean>> callback);
    }
    interface Presenter{
        void addMeeting(Map<String,String> map,LoadingDialog dialog);
        void editMeeting(Map<String,String> map,LoadingDialog dialog);
        void getListForMeeting(String token);
    }

}
