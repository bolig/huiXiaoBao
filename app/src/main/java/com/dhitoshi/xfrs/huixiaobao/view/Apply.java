package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.ApplyMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.ApplyMeetingPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Apply extends BaseView implements ApplyMeetingManage.View{
    @BindView(R.id.apply_start)
    TextView applyStart;
    @BindView(R.id.apply_end)
    TextView applyEnd;
    @BindView(R.id.apply_area)
    EditText applyArea;
    @BindView(R.id.apply_notes)
    EditText applyNotes;
    private String starttime = "";
    private String endtime = "";
    private String location = "";
    private String type = "";
    private String note = "";
    private ApplyMeetingPresenter applyMeetingPresenter;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"Apply");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("Apply");
    }

    private void initViews() {
        initBaseViews();
        setTitle("申请举办");
        setRightText("提交");
        type=String.valueOf(getIntent().getIntExtra("id",0));
        applyMeetingPresenter=new ApplyMeetingPresenter(this,this);
    }
    @OnClick({R.id.apply_start, R.id.apply_end, R.id.right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apply_start:
                selectStart();
                break;
            case R.id.apply_end:
                selectend();
                break;
            case R.id.right_text:
                commit();
                break;
        }
    }
    private void commit() {
        location = applyArea.getText().toString();
        if (location.isEmpty()) {
            Toast.makeText(this, "请填写会议举办地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (starttime.isEmpty()) {
            Toast.makeText(this, "请选择会议开始时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (endtime.isEmpty()) {
            Toast.makeText(this, "请选择会议结束时间", Toast.LENGTH_SHORT).show();
            return;
        }
        note=applyNotes.getText().toString();
        if (note.isEmpty()) {
            Toast.makeText(this, "请填写会议详情信息", Toast.LENGTH_SHORT).show();
            return;
        }
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        if(map==null){
            map=new HashMap<>();
        }
        map.put("token", SharedPreferencesUtil.Obtain(this,"token","").toString());
        map.put("type", type);
        map.put("location", location);
        map.put("starttime", starttime);
        map.put("endtime", endtime);
        map.put("note", note);
        map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
        applyMeetingPresenter.applyMeeting(map,dialog);
    }
    private void selectend() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("结束时间").getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                endtime = date;
                applyEnd.setText(date);
                applyEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    private void selectStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("开始时间").getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                starttime = date;
                applyStart.setText(date);
                applyStart.setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        }).show();
    }
    @Override
    public void applyMeeting(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        ActivityManagerUtil.destoryActivity("ApplyMeeting");
        ActivityManagerUtil.destoryActivity("ApplyMeetingInfo");
        finish();

    }
}
