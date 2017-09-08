package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
/**
 * Created by dxs on 2017/9/6.
 */
public interface GiftManage {
    interface View{
        void getGiftLists(PageBean<GiftBean> pageBean );
    }
    interface Model{
        void getGiftLists(String userid, String page, Callback<HttpBean<PageBean<GiftBean>>> callback);
    }
    interface Prsenter{
        void getGiftLists(String userid, String page);
    }
}
