package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */

public class ProductModel implements ProductManage.Model{
    private Context context;
    public ProductModel(Context context) {
        this.context = context;
    }
    @Override
    public void getItem(final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<ProductBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getItem(),new CommonObserver(new HttpResult<HttpBean<PageBean<ProductBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<ProductBean>> httpBean) {
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
