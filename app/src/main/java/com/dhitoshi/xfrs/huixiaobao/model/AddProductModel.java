package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/18.
 */

public class AddProductModel implements AddProductManage.Model {
    private Context context;
    public AddProductModel(Context context) {
        this.context = context;
    }
    @Override
    public void addItem(AddProductBean addProductBean, final LoadingDialog dialog, final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addItem(addProductBean),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void editItem(AddProductBean addProductBean, final LoadingDialog dialog, final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editItem(addProductBean),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

}
