package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SalesAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesInfo extends BaseView {
    @BindView(R.id.sales_recyclerView)
    RecyclerView salesRecyclerView;
    private String title = "";
    private ArrayList<SpendBean> spends;
    private SalesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_info);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        title = getIntent().getStringExtra("title");
        spends=getIntent().getParcelableArrayListExtra("spends");
        setTitle(title);
        salesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        salesRecyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        adapter = new SalesAdapter(spends,this);
        salesRecyclerView.setAdapter(adapter);
    }
}
