package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.PlayCountBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingVideoManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/9.
 */

public class MeetingVideoModel implements MeetingVideoManage.Model {
    private Context context;
    public MeetingVideoModel(Context context) {
        this.context = context;
    }

    @Override
    public void getVideoList(final Map<String, String> map, final SmartRefreshLayout refreshLayout, final Callback<HttpPageBeanTwo<VideoBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getVideoList(map),new CommonObserver(new HttpResult<HttpPageBeanTwo<VideoBean>>() {
            @Override
            public void OnSuccess(HttpPageBeanTwo<VideoBean> httpPageBeanTwo) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                if(httpPageBeanTwo.getStatus().getCode()==200){
                    callback.get(httpPageBeanTwo);
                }else if(httpPageBeanTwo.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getVideoList(map,refreshLayout,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpPageBeanTwo.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getVideoList(String token, final String meeting_id, final String page, final SmartRefreshLayout refreshLayout, final Callback<HttpPageBeanTwo<VideoBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getVideoList(token,meeting_id, page),new CommonObserver(new HttpResult<HttpPageBeanTwo<VideoBean>>() {
            @Override
            public void OnSuccess(HttpPageBeanTwo<VideoBean> httpPageBeanTwo) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                if(httpPageBeanTwo.getStatus().getCode()==200){
                    callback.get(httpPageBeanTwo);
                }else if(httpPageBeanTwo.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getVideoList(token,meeting_id,page,refreshLayout,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpPageBeanTwo.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void setPlayCount(final String token, final String video_id, final Callback<HttpBean<PlayCountBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().setPlayCount(token,video_id),new CommonObserver(new HttpResult<HttpBean<PlayCountBean>>() {
            @Override
            public void OnSuccess(HttpBean<PlayCountBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            setPlayCount(token, video_id, callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {

              //  Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getPlayCount(String token, final String video_id, final Callback<HttpBean<PlayCountBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getPlayCount(token,video_id),new CommonObserver(new HttpResult<HttpBean<PlayCountBean>>() {
            @Override
            public void OnSuccess(HttpBean<PlayCountBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getPlayCount(token, video_id, callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {

                //  Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
