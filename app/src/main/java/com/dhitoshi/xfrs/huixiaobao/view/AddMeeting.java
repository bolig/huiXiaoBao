package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetingEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddMeetingPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    private String attend="0";
    private String notes="";
    private String body="";
    private int userId;
    private MeetBean meetBean;
    private AddMeetingPresenter addMeetingPresenter;
    private List<BaseBean> usertypes;
    private List<BaseBean> types;
    private ArrayList<BaseBean> salesmen;

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
        addMeetingPresenter = new AddMeetingPresenter(this,this);
        addMeetingPresenter.getListForMeeting();
        meetAttend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                attend=isChecked?"1":"0";
            }
        });
    }
    private void initMeetInfo() {
        createtime=meetBean.getCreatetime();
        meetDate.setText(meetBean.getCreatetime());
        meetDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetType.setText(meetBean.getType());
        meetType.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetSalesman.setText(meetBean.getSalesman());
        meetSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
        meetClientType.setText(meetBean.getUsertype());
        meetClientType.setTextColor(getResources().getColor(R.color.colorPrimary));
        attend=meetBean.getAttend();
        meetAttend.setChecked(meetBean.getAttend().equals("1")?true:false);
        body=meetBean.getBody();
        meetBody.setText(meetBean.getBody());
        meetBody.setTextColor(getResources().getColor(R.color.colorPrimary));
        notes=meetBean.getNotes();
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
            bean.setNotes(meetNotes.getText().toString());
            bean.setSalesman(salesman);
            bean.setAttend(meetAttend.isChecked()?"1":"0");
            bean.setBody(body);
            bean.setCreatetime(createtime);
            bean.setType(type);
            bean.setUsertype(usertype);
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if(meetBean==null){
                bean.setUserid(String.valueOf(userId));
                addMeetingPresenter.addMeeting(bean,dialog);
            }else {
                bean.setId(String.valueOf(meetBean.getId()));
                addMeetingPresenter.editMeeting(bean,dialog);
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
            Toast.makeText(this,"请填写会议主题",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //选择会议类型
    private void selectMeetType() {
        CommonAdapter adapter=new CommonAdapter(types,this,meetType.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择会议类型").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                meetType.setText(baseBean.getName());
                type=String.valueOf(baseBean.getId());
                meetType.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择邀请人
    private void selectMeetSalesMan() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",salesmen)
                .putExtra("type",6).putExtra("select",meetSalesman.getText().toString()),6);
    }
    //选择客户类型
    private void selectMeetClientType() {
        CommonAdapter adapter=new CommonAdapter(usertypes,this,meetClientType.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择职位").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                meetClientType.setText(baseBean.getName());
                usertype=String.valueOf(baseBean.getId());
                meetClientType.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择会议日期
    private void selectMeetDate() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择会议日期").setTime(meetDate.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime=date;
                meetDate.setText(date);
                meetDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    @Override
    public void addMeeting(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MeetingEvent(1));
        finish();
    }
    @Override
    public void editMeeting(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MeetingEvent(1));
        finish();
    }
    @Override
    public void getListForMeeting(HttpBean<InfoAddMeetBean> httpBean) {
        usertypes = httpBean.getData().getUsertype();
        types = httpBean.getData().getType();
        salesmen = (ArrayList<BaseBean>) httpBean.getData().getSalesman();
        if(meetBean!=null){
            for (int i = 0; i < usertypes.size(); i++) {
                if(meetBean.getUsertype().equals(usertypes.get(i).getName())){
                    usertype=String.valueOf(usertypes.get(i).getId());
                }
            }
            for (int j = 0; j < types.size(); j++) {
                if(meetBean.getType().equals(types.get(j).getName())){
                    type=String.valueOf(types.get(j).getId());
                }
            }
            for (int k = 0; k < salesmen.size(); k++) {
                if(meetBean.getSalesman().equals(salesmen.get(k).getName())){
                    salesman=String.valueOf(salesmen.get(k).getId());
                }
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==200){
            switch (requestCode){
                case 6:
                    salesman=data.getStringExtra("id");
                    meetSalesman.setText(data.getStringExtra("name"));
                    meetSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
