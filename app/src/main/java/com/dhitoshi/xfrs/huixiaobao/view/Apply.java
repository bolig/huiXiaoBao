package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Apply extends BaseView {
    @BindView(R.id.apply_start)
    TextView applyStart;
    @BindView(R.id.apply_end)
    TextView applyEnd;
    private String starttime = "";
    private String endtime = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("申请举办");
        setRightText("提交");
    }
    @OnClick({R.id.apply_start, R.id.apply_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_start:
                selectStart();
                break;
            case R.id.apply_end:
                selectend();
                break;
        }
    }
    private void selectend() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("结束时间").getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                starttime = date;
                applyStart.setText(date);
                applyStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    private void selectStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("开始时间").getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                endtime = date;
                applyEnd.setText(date);
                applyEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
}
