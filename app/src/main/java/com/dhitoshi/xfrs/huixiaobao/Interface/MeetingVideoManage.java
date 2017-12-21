package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.PlayCountBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;

import java.util.Map;

/**
 * Created by dxs on 2017/11/12.
 */

public interface MeetingVideoManage {
    interface View{
        void getVideoList(HttpPageBeanTwo<VideoBean> httpPageBeanTwo);
        void setPlayCount(PlayCountBean bean);
        void getPlayCount(PlayCountBean bean);
    }
    interface Model{
        void getVideoList(Map<String,String> map, SmartRefreshLayout refreshLayout, Callback<HttpPageBeanTwo<VideoBean>> callback);
        void getVideoList(String token,String meeting_id, String page, SmartRefreshLayout refreshLayout,Callback<HttpPageBeanTwo<VideoBean>> callback);
        void setPlayCount(String token,String video_id,Callback<HttpBean<PlayCountBean>> callback);
        void getPlayCount(String token, String video_id, Callback<HttpBean<PlayCountBean>> callback);
    }
    interface Presenter{
        void getVideoList(Map<String,String> map, SmartRefreshLayout refreshLayout);
        void getVideoList(String token,String meeting_id, String page, SmartRefreshLayout refreshLayout);
        void setPlayCount(String token,String video_id);
        void getPlayCount(String token,String video_id);
    }
}
