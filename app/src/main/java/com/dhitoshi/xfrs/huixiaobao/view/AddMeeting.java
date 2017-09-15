package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddMeetingPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMeeting extends BaseView implements AddMeetingManage.View {
    @BindView(R.id.meet_date)
    TextView meetDate;
    @BindView(R.id.meet_type)
    TextView meetType;
    @BindView(R.id.meet_salesman)
    TextView meetSalesman;
    @BindView(R.id.meet_clientType)
    TextView meetClientType;
    @BindView(R.id.meet_body)
    EditText meetBody;
    @BindView(R.id.meet_notes)
    EditText meetNotes;
    @BindView(R.id.meet_attend)
    CheckBox meetAttend;
    private String createtime="";
    private String type="";
    private String salesman="";
    private String usertype="";
    private String attend="";
    private String body="";
    private String notes="";
    private int userId;
    private MeetBean meetBean;
    private AddMeetingPresenter addMeetingPresenter;
    private List<BaseBean> usertypes;
    private List<BaseBean> types;
    private List<BaseBean> salesmen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        userId = getIntent().getIntExtra("id", 0);
        meetBean = getIntent().getParcelableExtra("meet");
        setTitle(meetBean == null ? "新增会议记录" : "编辑会议记录");
        if (null != meetBean) {
            initMeetInfo();
        }
        setRightText("提交");
        addMeetingPresenter = new AddMeetingPresenter(this);
        addMeetingPresenter.getListForMeeting();
    }
    private void initMeetInfo() {
        meetDate.setText(meetBean.getCreatetime());
        meetDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetType.setText(meetBean.getType());
        meetType.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetSalesman.setText(meetBean.getSalesman());
        meetSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetClientType.setText(meetBean.getUsertype());
        meetClientType.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetBody.setText(meetBean.getBody());
        meetAttend.setChecked(meetBean.getAttend().equals("1")?true:false);
        meetBody.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetNotes.setText(meetBean.getNotes());
        meetNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    @OnClick({R.id.right_text, R.id.meet_date, R.id.meet_type, R.id.meet_salesman, R.id.meet_clientType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.meet_date:
                selectMeetDate();
                break;
            case R.id.meet_type:
                selectMeetType();
                break;
            case R.id.meet_salesman:
                selectMeetSalesMan();
                break;
            case R.id.meet_clientType:
                selectMeetClientType();
                break;
        }
    }
    private void commit() {
        if(juge()){
            AddMeetBean bean=new AddMeetBean();
            bean.setUserid(String.valueOf(userId));
            bean.setNotes(meetNotes.getText().toString());
            bean.setSalesman(salesman);
            bean.setAttend(meetAttend.isChecked()?"1":"0");
            bean.setBody(body);
            bean.setCreatetime(createtime);
            bean.setType(type);
            bean.setUsertype(usertype);
            if(meetBean==null){
                addMeetingPresenter.addMeeting(bean);
            }else {
                addMeetingPresenter.editMeeting(bean);
            }
        }
    }
    private boolean juge() {
        if(createtime.isEmpty()){
            Toast.makeText(this,"请选择会议日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(type.isEmpty()){
            Toast.makeText(this,"请选择会议类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(salesman.isEmpty()){
            Toast.makeText(this,"请选择邀请人",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(usertype.isEmpty()){
            Toast.makeText(this,"请选择顾客类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        body=meetBody.getText().toString();
        if(body.isEmpty()){
            Toast.makeText(this,"请选择顾客类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //选择会议类型
    private void selectMeetType() {

    }
    //选择邀请人
    private void selectMeetSalesMan() {

    }
    //选择客户类型
    private void selectMeetClientType() {

    }
    //选择会议日期
    private void selectMeetDate() {
    }

    @Override
    public void addMeeting(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void editMeeting(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getListForMeeting(HttpBean<InfoAddMeetBean> httpBean) {
        usertypes = httpBean.getData().getUsertype();
        types = httpBean.getData().getType();
        salesmen = httpBean.getData().getSalesman();
    }
}
