package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VersionBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
public class Launch extends AppCompatActivity {
    private Handler handler=new Handler();
    private boolean isFirst=true;//是否第一次使用此应用
    private boolean isRemeber=false;//是否记住密码
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch);
        initViews();
    }
    private void initViews() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        isFirst= (boolean) SharedPreferencesUtil.Obtain(this,"isFirst",true);
        isRemeber= (boolean) SharedPreferencesUtil.Obtain(this,"isRemeber",true);
        if(isFirst){
            ToNext(Welcome.class,1000);
        } else{
            getVersion();
            Class c=isRemeber?Theme.class:Login.class;
            ToNext(c,1000);
        }
    }

    private void getVersion() {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getVersion(),new CommonObserver(new HttpResult<HttpBean<VersionBean>>() {
            @Override
            public void OnSuccess(HttpBean<VersionBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    if(httpBean.getData().getCode()>getLocalVersionCode(Launch.this)){

                    }
                }else {
                    Toast.makeText(Launch.this,httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                Toast.makeText(Launch.this,msg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void ToNext(final Class c, long time) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Launch.this, c));
                finish();
            }
        }, time);
    }
    public static int getLocalVersionCode(Context context) {
        int localVsionCode = 1;
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVsionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVsionCode;
    }

}
