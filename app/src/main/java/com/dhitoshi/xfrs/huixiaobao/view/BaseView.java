package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.dhitoshi.ImmersionBar.ImmersionBar;
import com.dhitoshi.xfrs.huixiaobao.R;

import io.reactivex.disposables.Disposable;

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
