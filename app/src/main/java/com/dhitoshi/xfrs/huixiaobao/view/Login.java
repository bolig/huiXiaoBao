package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.ClearEditText;
import com.dhitoshi.xfrs.huixiaobao.presenter.LoginPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class Login extends AppCompatActivity implements LoginManage.View, View.OnTouchListener {
    @BindView(R.id.login_name)
    ClearEditText loginName;
    @BindView(R.id.login_pswd)
    ClearEditText loginPswd;
    @BindView(R.id.login)
    TextView login;
    private String name;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        login.setOnTouchListener(this);
        if(!SharedPreferencesUtil.Obtain(this,"token","").toString().isEmpty()){
            startActivity(new Intent(this, Theme.class));
            finish();
        }
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
    @Override
    public void login(UserBean userBean) {
        SharedPreferencesUtil.Save(this, "truename", userBean.getTruename());
        SharedPreferencesUtil.Save(this, "id", userBean.getId());
        SharedPreferencesUtil.Save(this, "token", userBean.getToken());
        startActivity(new Intent(this, Theme.class));
        finish();
    }
    @OnClick(R.id.login)
    public void onViewClicked() {
        name = loginName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "请填写用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        password = loginPswd.getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "请填写密码", Toast.LENGTH_SHORT).show();
            return;
        }
        login();
    }
    private void login() {
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("登录中");
        dialog.show();
        LoginPresenter loginPresenter = new LoginPresenter(this,this);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        loginPresenter.login(map, dialog);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.8f);
                break;
            case MotionEvent.ACTION_UP:
                v.setAlpha(1.0f);
                break;
        }
        return false;
    }
}
