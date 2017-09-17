package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SearchClientManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/16.
 */
public class SearchClientModel implements SearchClientManage.Model{
    private Context context;
    public SearchClientModel(Context context) {
        this.context = context;
    }
    @Override
    public void searchClientList(String search, String page, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<ClientBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().searchClientList(search, page),new CommonObserver(new HttpResult<HttpBean<PageBean<ClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<ClientBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
