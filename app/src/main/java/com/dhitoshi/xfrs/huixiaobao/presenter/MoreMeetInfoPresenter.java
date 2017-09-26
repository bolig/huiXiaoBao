package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MoreMeetInfoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MoreMeetInfoManage;
import com.dhitoshi.xfrs.huixiaobao.model.MoreMeetInfoModel;
import retrofit2.http.Query;
/**
 * Created by dxs on 2017/9/26.
 */

public class MoreMeetInfoPresenter implements MoreMeetInfoManage.Presenter{
    private MoreMeetInfoManage.View view;
    private MoreMeetInfoModel model;

    public MoreMeetInfoPresenter(MoreMeetInfoManage.View view, Context context) {
        this.view = view;
        model=new MoreMeetInfoModel(context);
    }
    @Override
    public void getArticleBody(@Query("aid") String aid, @Query("id") String id, SmartRefreshLayout smartRefreshLayout) {
        model.getArticleBody(aid, id, smartRefreshLayout, new Callback<HttpBean<MoreMeetInfoBean>>() {
            @Override
            public void get(HttpBean<MoreMeetInfoBean> httpBean) {
                view.getArticleBody(httpBean);
            }
        });
    }
}
