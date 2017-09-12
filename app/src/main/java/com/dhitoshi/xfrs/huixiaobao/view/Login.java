package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.ClearEditText;
import com.dhitoshi.xfrs.huixiaobao.model.LoginModel;
import com.dhitoshi.xfrs.huixiaobao.presenter.LoginPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity implements LoginManage.View {

    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_password)
    ClearEditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @Override
    public void login(UserBean userBean) {
        SharedPreferencesUtil.Save(this,"token",userBean.getToken());
        startActivity(new Intent(this,Theme.class));
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        LoginPresenter loginPresenter=new LoginPresenter(this) ;
        Map<String,String> map=new HashMap<>();
        map.put("name",etPhone.getText().toString());
        map.put("password",etPassword.getText().toString());
        loginPresenter.login(map);
    }
}
