package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

/**
 * Created by dxs on 2017/9/6.
 */
public class BaseView extends AppCompatActivity implements View.OnTouchListener{
    private Toolbar toolbar;
    private TextView title;
    private TextView rightText;
    private AppCompatImageView rightIcon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    public void initBaseViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView)findViewById(R.id.title);
        rightIcon = (AppCompatImageView) findViewById(R.id.right_icon);
        rightText = (TextView)findViewById(R.id.right_text);
        rightIcon.setOnTouchListener(this);
        rightText.setOnTouchListener(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setStatusTint(R.color.colorPrimary);
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
    //设置title
    public void  setTitle(String s){
        title.setText(s);
    }
    //设置右边文字
    public void setRightText(String right){
        rightText.setVisibility(View.VISIBLE);
        rightText.setText(right);
    }
    //设置右边图标
    public  void setRightIcon(int resourceId){
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setImageResource(resourceId);
        rightIcon.setColorFilter(getResources().getColor(android.R.color.white));
    }
    public void setStatusTint(int resId) {
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(resId);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.5f);
                break;
            case MotionEvent.ACTION_UP:
                v.setAlpha(1.0f);
                break;
        }
        return false;
    }
}
