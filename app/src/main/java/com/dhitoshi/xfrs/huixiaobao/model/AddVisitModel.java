package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddVisitModel implements AddVisitManage.Model{
    @Override
    public void addVisit(AddVisitBean addVisitBean, final Callback<HttpBean<VisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addVisit(addVisitBean),new CommonObserver(new HttpResult<HttpBean<VisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<VisitBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void getListForVisit(final Callback<HttpBean<InfoAddVisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForVisit(),new CommonObserver(new HttpResult<HttpBean<InfoAddVisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddVisitBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void editVisit(AddVisitBean addVisitBean, final Callback<HttpBean<VisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editVisit(addVisitBean),new CommonObserver(new HttpResult<HttpBean<VisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<VisitBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
