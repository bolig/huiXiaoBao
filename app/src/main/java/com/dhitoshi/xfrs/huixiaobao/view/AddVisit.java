package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;

public class AddVisit extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_visit);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("新增回访记录");
        setRightText("提交");
    }
}