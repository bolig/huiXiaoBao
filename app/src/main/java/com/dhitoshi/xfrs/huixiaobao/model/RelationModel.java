package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/7.
 */

public class RelationModel implements RelationManage.Model{
    private Context context;
    public RelationModel(Context context) {
        this.context = context;
    }
    @Override
    public void getRelationLists(String token,String userid, String page, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<RelationBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getRelationLists(token,userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<RelationBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<RelationBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
