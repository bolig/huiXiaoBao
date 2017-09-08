package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.VisitManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/7.
 */

public class VisitModel implements VisitManage.Model{
    @Override
    public void getFeedbackLists(String userid, String page, final Callback<HttpBean<PageBean<VisitBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getFeedbackLists(userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<VisitBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<VisitBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}