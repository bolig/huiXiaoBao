package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddProduct extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("新增产品");
        setRightText("提交");
    }
}
