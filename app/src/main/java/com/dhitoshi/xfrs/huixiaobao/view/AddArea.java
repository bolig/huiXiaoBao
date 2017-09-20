package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddAreaAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;

public class AddArea extends BaseView {
    private AddAreaAdapter adapter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
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
