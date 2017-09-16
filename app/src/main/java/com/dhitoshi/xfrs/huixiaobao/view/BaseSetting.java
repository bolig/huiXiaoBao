package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseSetting extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_setting);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        setTitle("基础设置");
    }

    @OnClick({R.id.base_area, R.id.base_permission, R.id.base_user, R.id.base_productType, R.id.base_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_area:
                break;
            case R.id.base_permission:
                break;
            case R.id.base_user:
                break;
            case R.id.base_productType:
                break;
            case R.id.base_product:
                startActivity(new Intent(this,Product.class));
                break;
        }
    }
}