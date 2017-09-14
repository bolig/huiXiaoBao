package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class ProductType extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_type);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("产品类型");
        setRightIcon(R.mipmap.add);
    }
}
