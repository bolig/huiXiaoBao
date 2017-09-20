package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBeanX;
import com.dhitoshi.xfrs.huixiaobao.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAreaTwo extends BaseView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String title;
    private ArrayList<KidBeanX> kidBeanXes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaother);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        title = getIntent().getStringExtra("title");
        kidBeanXes = getIntent().getParcelableArrayListExtra("kidx");
        setTitle(title);
        setRightIcon(R.mipmap.add);

    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
    }
}
