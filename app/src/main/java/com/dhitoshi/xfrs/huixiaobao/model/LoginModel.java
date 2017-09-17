package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.Map;
/**
 * Created by dxs on 2017/9/12.
 */

public class LoginModel implements LoginManage.Model {
    private Context context;
    public LoginModel(Context context) {
        this.context = context;
    }
    @Override
    public void login(Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<UserBean>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().login(map),new CommonObserver(new HttpResult<HttpBean<UserBean>>() {
            @Override
            public void OnSuccess(HttpBean<UserBean> httpBean) {
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
