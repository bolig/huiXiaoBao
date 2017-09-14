package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddGiftManage {
    interface View{
        void  addGift(String result);
        void  editGift(String result);
        void  getListForGift(HttpBean<InfoAddSpendBean> httpBean);
    }
    interface Model{
        void addGift(GiftBean giftBean, String userId, Callback<HttpBean<GiftBean>> callback);
        void getListForGift(Callback<HttpBean<InfoAddSpendBean>> callback);
        void editGift(GiftBean giftBean, String userId, Callback<HttpBean<GiftBean>> callback);
    }
    interface Presenter{
        void addGift(GiftBean giftBean,String userId);
        void editGift(GiftBean giftBean,String userId);
        void getListForGift();
    }

}
