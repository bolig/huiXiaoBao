package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;
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
        void addGift(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<GiftBean>> callback);
        void getListForGift(String token,Callback<HttpBean<InfoAddGiftBean>> callback);
        void editGift(Map<String,String> map,LoadingDialog dialog, Callback<HttpBean<GiftBean>> callback);
    }
    interface Presenter{
        void addGift(Map<String,String> map,LoadingDialog dialog);
        void editGift(Map<String,String> map,LoadingDialog dialog);
        void getListForGift(String token);
    }

}
