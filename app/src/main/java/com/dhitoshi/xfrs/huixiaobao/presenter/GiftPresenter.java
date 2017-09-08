package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.model.GiftModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class GiftPresenter implements GiftManage.Prsenter{
    private GiftManage.View view;
    private GiftModel giftModel;
    public GiftPresenter(GiftManage.View view) {
        this.view = view;
        giftModel=new GiftModel();
    }
    @Override
    public void getGiftLists(String userid, String page) {
        giftModel.getGiftLists(userid, page, new Callback<HttpBean<PageBean<GiftBean>>>() {
            @Override
            public void get(HttpBean<PageBean<GiftBean>> httpBean) {
                view.getGiftLists(httpBean.getData());
            }
        });
    }
}
