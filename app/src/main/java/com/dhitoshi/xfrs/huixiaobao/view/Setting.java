package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class Setting extends BaseView {
    private Intent it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("设置");
    }
    @OnClick({R.id.safe, R.id.base, R.id.copyright, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.safe:
                break;
            case R.id.base:
                it=new Intent(this,BaseSetting.class);
                startActivity(it);
                break;
            case R.id.copyright:
                break;
            case R.id.exit:
                startActivity(new Intent(this,Login.class));
                finish();
                break;
        }
    }
}
