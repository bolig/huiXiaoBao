package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import com.dhitoshi.xfrs.huixiaobao.R;
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
            ToNext(Welcome.class,2000);
        } else{
            Class c=isRemeber?Theme.class:Login.class;
            ToNext(c,2000);
        }
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
}
