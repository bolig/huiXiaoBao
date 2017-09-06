package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import com.dhitoshi.ImmersionBar.ImmersionBar;
import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/9/6.
 */
public class BaseView extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView title;
    private TextView rightText;
    private AppCompatImageView rightIcon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void initBaseViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView)findViewById(R.id.title);
        rightIcon = (AppCompatImageView) findViewById(R.id.right_icon);
        rightText = (TextView)findViewById(R.id.right_text);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ImmersionBar.with(this).navigationBarEnable(false).barColor(R.color.colorPrimary).init();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case android.R.id.home:
               finish();
               break;
       }
       return true;
    }
}
