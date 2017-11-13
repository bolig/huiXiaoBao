package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.kit.chat.ChattingFragment;
import com.alibaba.mobileim.lib.presenter.conversation.TribeConversation;
import com.alibaba.mobileim.ui.atmessage.AtMsgListActivity;
import com.alibaba.mobileim.utility.UserContext;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class TribeChat extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title;
    private YWIMKit mIMKit;
    private long target;
    private String name = "";
    private Intent intent;
    private YWIMCore imCore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribe_chat);
        ButterKnife.bind(this);
        InitView();
        target = getIntent().getLongExtra("target", 0);
        name = getIntent().getStringExtra("name");
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        imCore = mIMKit.getIMCore();
        FragmentManager fm = getSupportFragmentManager();
        title.setText(name);
        Fragment fragment = mIMKit.getTribeChattingFragment(target);
        fm.beginTransaction().add(R.id.chat_tribe, fragment).commit();
        ActivityManagerUtil.addDestoryActivity(this, "TribeChat");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("TribeChat");
    }
    private void InitView() {
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
    @OnClick({R.id.at, R.id.tribeInfo,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.at:
                intent = new Intent(this, AtMsgListActivity.class);
                Bundle bundle = new Bundle();
                YWConversation conversation = imCore.getConversationService().getTribeConversation(target);
                bundle.putString("conversationId", conversation.getConversationId());
                bundle.putLong("extraTribeId",target);
                String userId = ((TribeConversation)conversation).mWxAccount.getLid();
                bundle.putString("extraUserId", userId);
                intent.putExtra("bundle", bundle);
                intent.putExtra("user_context", new UserContext(userId,"24607089"));
                startActivity(intent);
                break;
            case R.id.tribeInfo:
                intent = new Intent(this, TribeInfo.class);
                intent.putExtra(TribeConstants.TRIBE_ID, target);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
