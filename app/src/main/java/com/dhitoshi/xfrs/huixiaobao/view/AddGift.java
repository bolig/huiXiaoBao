package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddGift extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_gift);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("新增赠品记录");
        setRightText("提交");
    }
}
