package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class Product extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("产品");
        setRightIcon(R.mipmap.add);
    }
}
