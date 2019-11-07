package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
/**
 * Created by dxs on 2017/9/12.
 */

public class LoginModel implements LoginManage.Model {
    private Context context;
    private YWIMKit mIMKit;
    public LoginModel(Context context) {
        this.context = context;
    }
    @Override
    public void login(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<UserBean>> callback) {
        final MyHttp http=MyHttp.getInstance();
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
                            dialog.dismiss();
                            callback.get(httpBean);
                        }
                        @Override
                        public void onProgress(int arg0) {
                        }
                        @Override
                        public void onError(int errCode, String description) {
                            dialog.dismiss();
                            Toast.makeText(context,description,Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    dialog.dismiss();
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
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
