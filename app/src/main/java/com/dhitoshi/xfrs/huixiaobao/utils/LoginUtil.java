package com.dhitoshi.xfrs.huixiaobao.utils;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.HashMap;
import java.util.Map;

import static com.dhitoshi.xfrs.huixiaobao.model.LoginModel.md5;

/**
 * Created by dxs on 2017/9/21.
 */
public class LoginUtil {
    private static YWIMKit mIMKit;
    public static void autoLogin(final Context context, final LoginCall call){
        final MyHttp http=new MyHttp();
        final Map<String, String> map = new HashMap<>();
        map.put("name", SharedPreferencesUtil.Obtain(context,"account","").toString());
        map.put("password", SharedPreferencesUtil.Obtain(context,"password","").toString());
        http.send(http.getHttpService().login(map),new CommonObserver(new HttpResult<HttpBean<UserBean>>() {
            @Override
            public void OnSuccess(final HttpBean<UserBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    //开始登录
                    String userid = map.get("name").split("@")[0];
                    mIMKit = YWAPI.getIMKitInstance(userid, "24607089");
                    IYWLoginService loginService = mIMKit.getLoginService();
                    YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, md5(map.get("password")));
                    loginService.login(loginParam, new IWxCallback() {
                        @Override
                        public void onSuccess(Object... arg0) {
                            SharedPreferencesUtil.Save(context,"token",httpBean.getData().getToken());
                            call.autoLogin(httpBean.getData().getToken());
                        }
                        @Override
                        public void onProgress(int arg0) {
                        }
                        @Override
                        public void onError(int errCode, String description) {
                            Toast.makeText(context,description,Toast.LENGTH_SHORT).show();
                        }
                    });
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
