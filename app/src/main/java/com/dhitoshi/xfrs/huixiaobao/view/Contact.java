package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class Contact extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("通讯录联系人");
        setRightText("全部");
    }
}