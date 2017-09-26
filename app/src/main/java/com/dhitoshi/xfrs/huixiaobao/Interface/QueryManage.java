package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */
public interface QueryManage {
    interface View{
       void getListForSearch(HttpBean<InfoQuery> httpBean);
        void getSearchOne(HttpBean<PageBean<ClientBean>> httpBean);
        void getSearchTwo(HttpBean<PageBean<SpendBean>> httpBean);
        void getSearchThree(HttpBean<PageBean<VisitBean>> httpBean);
        void getSearchFour(HttpBean<PageBean<RelationBean>> httpBean);
        void getSearchFive(HttpBean<PageBean<GiftBean>> httpBean);
        void getSearchSix(HttpBean<PageBean<MeetBean>> httpBean);
    }
    interface Model{
        void getListForSearch(String token,Callback<HttpBean<InfoQuery>> callback);
        void getSearchOne(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<ClientBean>>> callback);
        void getSearchTwo(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<PageBean<SpendBean>>> callback);
        void getSearchThree(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<PageBean<VisitBean>>> callback);
        void getSearchFour(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<PageBean<RelationBean>>> callback);
        void getSearchFive(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<PageBean<GiftBean>>> callback);
        void getSearchSix(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<PageBean<MeetBean>>> callback);
    }
    interface Presenter{
        void getListForSearch(String token);
        void getSearchOne(Map<String,String> map, LoadingDialog dialog);
        void getSearchTwo(Map<String,String> map, LoadingDialog dialog);
        void getSearchThree(Map<String,String> map, LoadingDialog dialog);
        void getSearchFour(Map<String,String> map, LoadingDialog dialog);
        void getSearchFive(Map<String,String> map, LoadingDialog dialog);
        void getSearchSix(Map<String,String> map, LoadingDialog dialog);
    }
}
