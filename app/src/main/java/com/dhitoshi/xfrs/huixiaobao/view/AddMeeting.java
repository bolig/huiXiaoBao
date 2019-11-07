package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetingEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddMeetingPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private int from=0;
    private Map<String,String> map;
    private  LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AddMeeting");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AddMeeting");
    }

    private void initViews() {
        initBaseViews();
        userId = getIntent().getIntExtra("id", 0);
        from = getIntent().getIntExtra("type", 0);
        meetBean = getIntent().getParcelableExtra("meet");
        setTitle(meetBean == null ? "新增会议记录" : "编辑会议记录");
        if (null != meetBean) {
            initMeetInfo();
        }
        setRightText("提交");
        addMeetingPresenter = new AddMeetingPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        addMeetingPresenter.getListForMeeting(token);
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
                if(types==null){
                    reListForMeeting(0);
                }else{
                    selectMeetType();
                }
                break;
            case R.id.meet_salesman:
                if(types==null){
                    reListForMeeting(1);
                }else{
                    selectMeetSalesMan();
                }
                break;
            case R.id.meet_clientType:
                if(types==null){
                    reListForMeeting(2);
                }else{
                    selectMeetClientType();
                }
                break;
        }
    }
    private void commit() {
        if(juge()){
            if(map==null){
                map=new HashMap<>();
            }
            if(!createtime.isEmpty()){
                map.put("createtime",createtime);
            }
            if(!body.isEmpty()){
                map.put("body",body);
            }

            if(!attend.isEmpty()){
                map.put("attend",attend);
            }
            if(!notes.isEmpty()){
                map.put("notes",notes);
            }
            dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
            map.put("token",token);
            if(meetBean==null){
                if(!type.isEmpty()){
                    map.put("type",type);
                }
                if(!usertype.isEmpty()){
                    map.put("usertype",usertype);
                }
                if(!salesman.isEmpty()){
                    map.put("salesman",salesman);
                }
                map.put("userid",String.valueOf(userId));
                addMeetingPresenter.addMeeting(map,dialog);
            }else {
                map.put("id",String.valueOf(meetBean.getId()));
                if(!TextUtils.isEmpty(meetBean.getType())&&TextUtils.isEmpty(type)){
                    reListForMeeting(3);
                }else if(!TextUtils.isEmpty(meetBean.getSalesman())&&TextUtils.isEmpty(salesman)){
                    reListForMeeting(3);
                }else if(!TextUtils.isEmpty(meetBean.getUsertype())&&TextUtils.isEmpty(usertype)){
                    reListForMeeting(3);
                }else {
                    if(!type.isEmpty()){
                        map.put("type",type);
                    }
                    if(!usertype.isEmpty()){
                        map.put("usertype",usertype);
                    }
                    if(!salesman.isEmpty()){
                        map.put("salesman",salesman);
                    }
                    addMeetingPresenter.editMeeting(map,dialog);
                }
            }
        }
    }
    private boolean juge() {
        if(createtime.isEmpty()){
            Toast.makeText(this," 请选择会议日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        body=meetBody.getText().toString();
        attend=meetAttend.isChecked()?"1":"0";
        notes=meetNotes.getText().toString();
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
        if(from==1){
            EventBus.getDefault().post(new QueryResultEvent(1));
        }else {
            EventBus.getDefault().post(new MeetingEvent(1));
        }

        finish();
    }
    private void reListForMeeting(final int location){
        MyHttp http=MyHttp.getInstance();
        String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
        http.send(http.getHttpService().getListForMeeting(token),new CommonObserver(new HttpResult<HttpBean<InfoAddMeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddMeetBean> httpBean) {
                if(httpBean.getStatus().getCode()==200) {
                    usertypes = httpBean.getData().getUsertype();
                    types = httpBean.getData().getType();
                    salesmen = (ArrayList<BaseBean>) httpBean.getData().getSalesman();
                    if(meetBean!=null){
                        for (int i = 0; i < usertypes.size(); i++) {
                            if(usertypes.get(i).getName().equals(meetBean.getUsertype())){
                                usertype=String.valueOf(usertypes.get(i).getId());
                            }
                        }
                        for (int j = 0; j < types.size(); j++) {
                            if(types.get(j).getName().equals(meetBean.getType())){
                                type=String.valueOf(types.get(j).getId());
                            }
                        }
                        for (int k = 0; k < salesmen.size(); k++) {
                            if(salesmen.get(k).getName().equals(meetBean.getSalesman())){
                                salesman=String.valueOf(salesmen.get(k).getId());
                            }
                        }
                    }
                    switch (location){
                        case 0:
                            selectMeetType();
                            break;
                        case 1:
                            selectMeetSalesMan();
                            break;
                        case 2:
                            selectMeetClientType();
                            break;
                        case 3:
                            if(!type.isEmpty()){
                                map.put("type",type);
                            }
                            if(!usertype.isEmpty()){
                                map.put("usertype",usertype);
                            }
                            if(!salesman.isEmpty()){
                                map.put("salesman",salesman);
                            }
                            addMeetingPresenter.editMeeting(map,dialog);
                            break;
                    }
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(AddMeeting.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            reListForMeeting(location);
                        }
                    });
                }else{
                    Toast.makeText(AddMeeting.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                    if(location==3&&dialog!=null){
                        dialog.dismiss();
                    }
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(AddMeeting.this,msg,Toast.LENGTH_SHORT).show();
                if(location==3&&dialog!=null){
                    dialog.dismiss();
                }
            }
        }));
    }
    @Override
    public void getListForMeeting(HttpBean<InfoAddMeetBean> httpBean) {
        usertypes = httpBean.getData().getUsertype();
        types = httpBean.getData().getType();
        salesmen = (ArrayList<BaseBean>) httpBean.getData().getSalesman();
        if(meetBean!=null){
            for (int i = 0; i < usertypes.size(); i++) {
                if(usertypes.get(i).getName().equals(meetBean.getUsertype())){
                    usertype=String.valueOf(usertypes.get(i).getId());
                }
            }
            for (int j = 0; j < types.size(); j++) {
                if(types.get(j).getName().equals(meetBean.getType())){
                    type=String.valueOf(types.get(j).getId());
                }
            }
            for (int k = 0; k < salesmen.size(); k++) {
                if(salesmen.get(k).getName().equals(meetBean.getSalesman())){
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
