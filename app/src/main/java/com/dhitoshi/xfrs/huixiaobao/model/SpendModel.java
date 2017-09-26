package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SpendManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/7.
 */
public class SpendModel implements SpendManage.Model{
    private Context context;
    public SpendModel(Context context) {
        this.context = context;
    }
    @Override
    public void getSpendingLists(String token,String userid, String page, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<SpendBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSpendingLists(token,userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<SpendBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<SpendBean>> httpBean) {
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
