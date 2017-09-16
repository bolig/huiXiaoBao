package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddSpendManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/13.
 */
public class AddSpendModel implements AddSpendManage.Model{
    @Override
    public void addSpend(AddSpendBean addSpendBean, final Callback<HttpBean<SpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addSpend(addSpendBean),new CommonObserver(new HttpResult<HttpBean<SpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<SpendBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void getListForSpending(final Callback<HttpBean<InfoAddSpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForSpending(),new CommonObserver(new HttpResult<HttpBean<InfoAddSpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddSpendBean> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void editSpend(AddSpendBean addSpendBean, final Callback<HttpBean<SpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editSpend(addSpendBean),new CommonObserver(new HttpResult<HttpBean<SpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<SpendBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}