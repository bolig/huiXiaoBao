package com.dhitoshi.xfrs.huixiaobao.view;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.dhitoshi.ImmersionBar.ImmersionBar;
import com.dhitoshi.xfrs.huixiaobao.R;

public class Chat extends AppCompatActivity {
    private YWIMKit mIMKit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        InitView();
        String userid = "visitor1";
        mIMKit = YWAPI.getIMKitInstance(userid, "23015524");
        FragmentManager fm = getSupportFragmentManager();
        String target = "visitor2";// 消息接收者ID
        Fragment fragment = mIMKit.getChattingFragment(target);
        fm.beginTransaction().add(R.id.chat,fragment).commit();
    }

    private void InitView() {
        ImmersionBar.with(this).navigationBarEnable(false).statusBarDarkFont(true)
                .barColor(android.R.color.holo_blue_bright).init();
    }
}
