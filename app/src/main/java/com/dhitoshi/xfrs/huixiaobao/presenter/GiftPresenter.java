package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.model.GiftModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class GiftPresenter implements GiftManage.Prsenter{
    private GiftManage.View view;
    private GiftModel giftModel;
    public GiftPresenter(GiftManage.View view, Context context) {
        this.view = view;
        giftModel=new GiftModel(context);
    }
    @Override
    public void getGiftLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout) {
        giftModel.getGiftLists(token,userid, page, smartRefreshLayout,new Callback<HttpBean<HttpPageBean<GiftBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<GiftBean>> httpBean) {
                view.getGiftLists(httpBean.getData());
            }
        });
    }
}
