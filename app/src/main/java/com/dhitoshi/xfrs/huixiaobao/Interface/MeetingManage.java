package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;


/**
 * Created by dxs on 2017/9/6.
 */
public interface MeetingManage {
    interface View{
        void getMeetingLists(PageBean<MeetBean> pageBean);
    }
    interface Model{
        void getMeetingLists(String userid, String page, Callback<HttpBean<PageBean<MeetBean>>> callback);
    }
    interface Prsenter{
        void getMeetingLists(String userid, String page);
    }
}
