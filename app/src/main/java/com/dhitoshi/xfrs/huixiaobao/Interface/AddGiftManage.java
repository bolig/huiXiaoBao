package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddGiftManage {
    interface View{
        void  addGift(String result);
        void  editGift(String result);
        void  getListForGift(HttpBean<InfoAddGiftBean> httpBean);
    }
    interface Model{
        void addGift(AddGiftBean addGiftBean, LoadingDialog dialog, Callback<HttpBean<GiftBean>> callback);
        void getListForGift(Callback<HttpBean<InfoAddGiftBean>> callback);
        void editGift(AddGiftBean addGiftBean,LoadingDialog dialog, Callback<HttpBean<GiftBean>> callback);
    }
    interface Presenter{
        void addGift(AddGiftBean addGiftBean,LoadingDialog dialog);
        void editGift(AddGiftBean addGiftBean,LoadingDialog dialog);
        void getListForGift();
    }

}
