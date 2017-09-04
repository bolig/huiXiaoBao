package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.dhitoshi.ImmersionBar.ImmersionBar;
import com.dhitoshi.xfrs.huixiaobao.R;
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
        ImmersionBar.with(this).navigationBarEnable(false)
                .barColor(R.color.colorPrimary).init();
    }

}
