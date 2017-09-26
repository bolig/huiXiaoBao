package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyMeetingInfo extends BaseView {
    @BindView(R.id.info_image)
    ImageView infoImage;
    @BindView(R.id.apply_cst)
    TextView applyCst;
    @BindView(R.id.apply_title)
    TextView applyTitle;
    @BindView(R.id.apply_start)
    TextView applyStart;
    @BindView(R.id.apply_end)
    TextView applyEnd;
    private ApplyMeetBean applyMeetBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_meeting_info);
        ButterKnife.bind(this);
        ActivityManager.addDestoryActivity(this,"ApplyMeetingInfo");
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.destoryActivity("ApplyMeetingInfo");
    }

    private void initViews() {
        initBaseViews();
        applyMeetBean = getIntent().getParcelableExtra("apply");
        setTitle("会议详情");
        Glide.with(this).load(applyMeetBean.getImg()).placeholder(R.mipmap.banner).error(R.mipmap.banner).into(infoImage);
        applyTitle.setText(applyMeetBean.getName());
        applyStart.setText(applyMeetBean.getStarttime());
        applyEnd.setText(applyMeetBean.getEndtime());
        applyCst.setText("￥" + applyMeetBean.getCost());
    }

    @OnClick({R.id.more_info, R.id.to_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_info:
                startActivity(new Intent(this,MoreMeetInfo.class).putExtra("id",applyMeetBean.getId()).putExtra("body",applyMeetBean.getBody()));
                break;
            case R.id.to_apply:
                startActivity(new Intent(this,Apply.class).putExtra("id",applyMeetBean.getId()));
                break;
        }
    }
}
