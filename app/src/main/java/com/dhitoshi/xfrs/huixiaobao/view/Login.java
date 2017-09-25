package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.ClearEditText;
import com.dhitoshi.xfrs.huixiaobao.presenter.LoginPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
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
    @BindView(R.id.check)
    CheckBox check;
    private String name;
    private String password;
    private boolean isRemeber=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        login.setOnTouchListener(this);
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        loginName.setText(SharedPreferencesUtil.Obtain(this, "name","").toString());
        loginName.setSelection(loginName.getText().length());
        isRemeber= (boolean) SharedPreferencesUtil.Obtain(this,"isRemeber",true);
        if(isRemeber){
            loginPswd.setText(SharedPreferencesUtil.Obtain(this, "password","").toString());
            check.setChecked(isRemeber);
        }
    }

    @Override
    public void login(UserBean userBean) {
        SharedPreferencesUtil.Save(this, "name",name);
        SharedPreferencesUtil.Save(this, "password",password);
        SharedPreferencesUtil.Save(this, "truename", userBean.getTruename());
        SharedPreferencesUtil.Save(this, "id", userBean.getId());
        SharedPreferencesUtil.Save(this, "token", userBean.getToken());
        SharedPreferencesUtil.Save(this, "head", userBean.getHead());
        SharedPreferencesUtil.Save(this, "isRemeber",check.isChecked());
        startActivity(new Intent(this, Theme.class));
        finish();
    }

    private void login() {
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
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("登录中");
        dialog.show();
        LoginPresenter loginPresenter = new LoginPresenter(this, this);
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

    @OnClick({R.id.remeber, R.id.forget, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remeber:
                check.setChecked(!check.isChecked());
                break;
            case R.id.forget:
                break;
            case R.id.login:
                login();
                break;
        }
    }
}
