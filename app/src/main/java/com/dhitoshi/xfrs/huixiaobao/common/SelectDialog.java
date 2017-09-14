package com.dhitoshi.xfrs.huixiaobao.common;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/9/12.
 */
public class SelectDialog extends Dialog {
    private TextView title;
    private RecyclerView content;
    private TextView confirm;
    private Context context;
    private String s;
    private BaseAdapter adapter;
    public SelectDialog(@NonNull Context context) {
        super(context);
        this.context=context;
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
        title.setText(s);
        content=(RecyclerView) findViewById(R.id.select_recyclerView);
        content.setLayoutManager(new LinearLayoutManager(context));
        content.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        content.setAdapter(adapter);
        confirm=(TextView)findViewById(R.id.dialog_cancel);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    public SelectDialog setTitle(String s){
        this.s=s;
        return this;
    }
    public SelectDialog setAdapter(BaseAdapter adapter){
       this.adapter=adapter;
        return this;
    }
}
