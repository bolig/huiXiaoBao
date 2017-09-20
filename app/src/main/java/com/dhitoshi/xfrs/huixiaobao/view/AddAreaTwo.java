package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddAreaTwo extends BaseView {
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaOther);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        title=getIntent().getStringExtra("title");
        setTitle(title);
        setRightIcon(R.mipmap.add);
    }
}
