package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;

/**
 * Created by dxs on 2017/11/12.
 */

public interface MeetingVideoManage {
    interface View{
        void getVideoList(HttpPageBeanTwo<VideoBean> httpPageBeanTwo);
    }
    interface Model{
        void getVideoList(String token,String meeting_id, String page, SmartRefreshLayout refreshLayout,Callback<HttpPageBeanTwo<VideoBean>> callback);
    }
    interface Presenter{
        void getVideoList(String token,String meeting_id, String page, SmartRefreshLayout refreshLayout);
    }
}
