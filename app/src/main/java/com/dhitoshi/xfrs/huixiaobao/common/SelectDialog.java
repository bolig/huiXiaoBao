package com.dhitoshi.xfrs.huixiaobao.common;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/9/12.
 */
public class SelectDialog extends Dialog {
    private TextView title;
    private RecyclerView content;
    private TextView confirm;
    public SelectDialog(@NonNull Context context) {
        super(context);
    }
    public SelectDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select);
        initViews();
    }
    private void initViews() {
        title=(TextView)findViewById(R.id.dialog_title);
        content=(RecyclerView) findViewById(R.id.select_recyclerView);
        confirm=(TextView)findViewById(R.id.dialog_confirm);
    }
    public SelectDialog setTitle(String s){
        title.setText(s);
        return this;
    }
}
