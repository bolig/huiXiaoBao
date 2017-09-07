package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/7.
 */
public class MeetModel implements MeetingManage.Model{

    @Override
    public void getMeetingLists(String userid, String page, final Callback<HttpBean<PageBean<MeetBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getMeetingLists(userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<MeetBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<MeetBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
