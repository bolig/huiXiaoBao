package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class PersonalClient extends BaseView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_client);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("用户");
        setRightIcon(R.mipmap.add);
    }
}
