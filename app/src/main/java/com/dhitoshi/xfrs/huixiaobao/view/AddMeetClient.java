package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddMeetingClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.IdCardUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddMeetClient extends BaseView implements AddMeetingClientManage.View{
    @BindView(R.id.meet_clientName)
    EditText meetClientName;
    @BindView(R.id.meet_clientIdCard)
    EditText meetClientIdCard;
    @BindView(R.id.meet_clientPhone)
    EditText meetClientPhone;
    private String name="";
    private String idcard="";
    private String meetingid="";
    private String phone="";
    private Map<String,String> map;
    private AddMeetingClientPresenter addMeetingClientPresenter;
    private MeetClientBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meet_client);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AddMeetClient");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AddMeetClient");
    }

    private void initViews() {
        initBaseViews();
        setRightText("提交");
        bean=getIntent().getParcelableExtra("meetclient");
        setTitle(bean==null?"添加人员":"编辑人员");
        meetingid=getIntent().getStringExtra("id");
        if(bean!=null){
            initData();
        }
        addMeetingClientPresenter=new AddMeetingClientPresenter(this,this);
    }
    private void initData() {
        name=bean.getName();
        meetClientName.setText(name);
        meetClientName.setSelection(meetClientName.length());
        idcard=bean.getIdcard();
        meetClientIdCard.setText(idcard);
        phone=bean.getPhone();
        meetClientPhone.setText(phone);
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        name=meetClientName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写客户姓名",Toast.LENGTH_SHORT).show();
            return;
        }
        idcard=meetClientIdCard.getText().toString();
        if (!IdCardUtil.isIDCard(idcard)) {
            Toast.makeText(this, "请填写正确的客户身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        phone=meetClientPhone.getText().toString();
        if(map==null){
            map=new HashMap<>();
        }
        map.put("name",name);
        map.put("idcard",idcard);
        map.put("phone",phone);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        map.put("token",token);
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        if(bean==null){
            map.put("meetingid",meetingid);
            addMeetingClientPresenter.addCustomer(map,dialog);
        }else{
            map.put("id",String.valueOf(bean.getId()));
            addMeetingClientPresenter.editCustomer(map,dialog);
        }

    }
    @Override
    public void addCustomer(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MeetClientEvent(1));
        finish();
    }

    @Override
    public void editCustomer(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new MeetClientEvent(1));
        finish();
    }
}
