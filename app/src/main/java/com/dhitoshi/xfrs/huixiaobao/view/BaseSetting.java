package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;

public class BaseSetting extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_setting);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("基础设置");
    }
}
