package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SpendManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/7.
 */
public class SpendModel implements SpendManage.Model{
    @Override
    public void getSpendingLists(String userid, String page, final Callback<HttpBean<PageBean<SpendBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSpendingLists(userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<SpendBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<SpendBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}