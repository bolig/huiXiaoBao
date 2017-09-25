package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Query extends BaseView {
    @BindView(R.id.query_result)
    TextView queryResult;
    @BindView(R.id.query_area)
    TextView queryArea;
    @BindView(R.id.query_name)
    EditText queryName;
    @BindView(R.id.query_sex)
    TextView querySex;
    @BindView(R.id.query_phone)
    EditText queryPhone;
    @BindView(R.id.query_vip)
    EditText queryVip;
    @BindView(R.id.query_address)
    EditText queryAddress;
    @BindView(R.id.query_company)
    EditText queryCompany;
    @BindView(R.id.query_position)
    TextView queryPosition;
    @BindView(R.id.createtime_start)
    TextView createtimeStart;
    @BindView(R.id.createtime_end)
    TextView createtimeEnd;
    @BindView(R.id.totalnum_start)
    EditText totalnumStart;
    @BindView(R.id.totalnum_end)
    EditText totalnumEnd;
    @BindView(R.id.query_sales)
    TextView querySales;
    @BindView(R.id.buy_address)
    TextView buyAddress;
    @BindView(R.id.buy_item)
    TextView buyItem;
    @BindView(R.id.buytime_start)
    TextView buytimeStart;
    @BindView(R.id.buytime_end)
    TextView buytimeEnd;
    @BindView(R.id.visit_start)
    TextView visitStart;
    @BindView(R.id.visit_end)
    TextView visitEnd;
    @BindView(R.id.unvisit_start)
    TextView unvisitStart;
    @BindView(R.id.unvisit_end)
    TextView unvisitEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        setTitle("高级查询");
    }

    @OnClick({R.id.query_result, R.id.query_area, R.id.query_sex, R.id.query_position, R.id.createtime_start,
            R.id.createtime_end, R.id.query_sales, R.id.buy_address, R.id.buy_item, R.id.buytime_start,
            R.id.buytime_end, R.id.visit_start, R.id.visit_end, R.id.unvisit_start, R.id.unvisit_end,R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_result:
                break;
            case R.id.query_area:
                break;
            case R.id.query_sex:
                break;
            case R.id.query_position:
                break;
            case R.id.createtime_start:
                break;
            case R.id.createtime_end:
                break;
            case R.id.query_sales:
                break;
            case R.id.buy_address:
                break;
            case R.id.buy_item:
                break;
            case R.id.buytime_start:
                break;
            case R.id.buytime_end:
                break;
            case R.id.visit_start:
                break;
            case R.id.visit_end:
                break;
            case R.id.unvisit_start:
                break;
            case R.id.unvisit_end:
                break;
            case R.id.query:
                break;
        }
    }
}
