package com.dhitoshi.xfrs.huixiaobao.utils;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by dxs on 2017/9/21.
 */
public class LoginUtil {
    public static void autoLogin(final Context context, final LoginCall call){
        final MyHttp http=new MyHttp();
        Map<String, String> map = new HashMap<>();
        map.put("name", SharedPreferencesUtil.Obtain(context,"account","").toString());
        map.put("password", SharedPreferencesUtil.Obtain(context,"password","").toString());
        http.send(http.getHttpService().login(map),new CommonObserver(new HttpResult<HttpBean<UserBean>>() {
            @Override
            public void OnSuccess(HttpBean<UserBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    SharedPreferencesUtil.Save(context,"token",httpBean.getData().getToken());
                    call.autoLogin(httpBean.getData().getToken());
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
