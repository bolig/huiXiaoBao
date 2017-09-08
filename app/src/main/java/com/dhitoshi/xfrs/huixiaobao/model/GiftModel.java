package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/7.
 */

public class GiftModel implements GiftManage.Model{

    @Override
    public void getGiftLists(String userid, String page, final Callback<HttpBean<PageBean<GiftBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getGiftLists(userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<GiftBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<GiftBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
