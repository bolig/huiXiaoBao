package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddGiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/15.
 */

public class AddGiftModel implements AddGiftManage.Model{
    @Override
    public void addGift(AddGiftBean addGiftBean, final Callback<HttpBean<GiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addGift(addGiftBean),new CommonObserver(new HttpResult<HttpBean<GiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<GiftBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void getListForGift(final Callback<HttpBean<InfoAddGiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForGift(),new CommonObserver(new HttpResult<HttpBean<InfoAddGiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddGiftBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void editGift(AddGiftBean addGiftBean, final Callback<HttpBean<GiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editGift(addGiftBean),new CommonObserver(new HttpResult<HttpBean<GiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<GiftBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
