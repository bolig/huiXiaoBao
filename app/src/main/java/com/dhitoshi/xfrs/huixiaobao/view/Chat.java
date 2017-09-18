package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.WindowManager;
import android.widget.TextView;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
public class Chat extends AppCompatActivity {
    @BindView(R.id.back)
    AppCompatImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    AppCompatImageView rightIcon;
    private YWIMKit mIMKit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        InitView();
        String userid = "visitor1";
        mIMKit = YWAPI.getIMKitInstance(userid, "23015524");
        FragmentManager fm = getSupportFragmentManager();
        String target = "visitor2";// 消息接收者ID
        title.setText(target);
        Fragment fragment = mIMKit.getChattingFragment(target, "23015524");
        fm.beginTransaction().add(R.id.chat, fragment).commit();
    }

    private void InitView() {
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(R.color.colorPrimary);
    }

}
