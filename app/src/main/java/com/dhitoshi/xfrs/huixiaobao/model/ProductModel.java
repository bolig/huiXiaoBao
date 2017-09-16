package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
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
    @Override
    public void getItem(final Callback<HttpBean<List<ProductBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getItem(),new CommonObserver(new HttpResult<HttpBean<List<ProductBean>>>() {
            @Override
            public void OnSuccess(HttpBean<List<ProductBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
