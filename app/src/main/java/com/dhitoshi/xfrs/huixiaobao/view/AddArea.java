package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddArea extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_area);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("地区");
        setRightIcon(R.mipmap.add);
    }
}
