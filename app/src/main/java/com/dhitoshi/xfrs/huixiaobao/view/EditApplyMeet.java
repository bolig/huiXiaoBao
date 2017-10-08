package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditApplyMeet extends BaseView {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.info_image)
    ImageView infoImage;
    @BindView(R.id.apply_title)
    TextView applyTitle;
    @BindView(R.id.infoMeet_start)
    TextView infoMeetStart;
    @BindView(R.id.infoMeet_end)
    TextView infoMeetEnd;
    @BindView(R.id.infoMeet_area)
    TextView infoMeetArea;
    @BindView(R.id.infoMeet_cost)
    TextView infoMeetCost;
    @BindView(R.id.more_info)
    TextView moreInfo;
    @BindView(R.id.to_enter)
    TextView toEnter;
    @BindView(R.id.to_attend)
    TextView toAttend;
    private OwnMeetBean ownMeetBean;
    private int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_apply_meet);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        ownMeetBean = getIntent().getParcelableExtra("own");
        setTitle("会议详情");
        Glide.with(this).load(ownMeetBean.getType().getImg()).placeholder(R.mipmap.banner).error(R.mipmap.banner).into(infoImage);
        applyTitle.setText(ownMeetBean.getType().getName());
        infoMeetStart.setText(ownMeetBean.getStarttime());
        infoMeetEnd.setText(ownMeetBean.getEndtime());
        infoMeetArea.setText(ownMeetBean.getLocation());
        infoMeetCost.setText("￥" + ownMeetBean.getType().getCost());
        type = getIntent().getIntExtra("type", 0);
        initEvents();
    }
    private void initEvents() {
        switch (type){
            case 1:

                break;
            case 2:
                toAttend.setClickable(false);
                toAttend.setBackgroundColor(Color.parseColor("#efefef"));
                break;
            case 3:
                toAttend.setText("考勤数据");
                break;
        }
    }
    @OnClick({R.id.more_info, R.id.to_enter, R.id.to_attend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_info:
                startActivity(new Intent(this, MoreMeetInfo.class).putExtra("id", ownMeetBean.getId()).putExtra("body", ownMeetBean.getType().getBody()));
                break;
            case R.id.to_enter:
                startActivity(new Intent(this,EnterClient.class).putExtra("id", String.valueOf(ownMeetBean.getId())).putExtra("type",type));
                break;
            case R.id.to_attend:
                if(type==3){
                    startActivity(new Intent(this,AttendInfo.class));
                }else {
                    startActivity(new Intent(this,AttendMeet.class).putExtra("start",ownMeetBean.getStarttime()).putExtra("days",ownMeetBean.getDays()).putExtra("id", String.valueOf(ownMeetBean.getId())));
                }
                break;
        }
    }
}
