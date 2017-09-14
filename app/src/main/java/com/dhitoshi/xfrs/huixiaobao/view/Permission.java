package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class Permission extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("权限组");
    }
}
