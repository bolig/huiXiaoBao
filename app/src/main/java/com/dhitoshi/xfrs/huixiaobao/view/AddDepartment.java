package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.AddAreaThreeEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddAreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddAreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddDepartment extends BaseView implements AddAreaManage.View{
    @BindView(R.id.area_name)
    EditText areaName;
    @BindView(R.id.area_admin)
    EditText areaAdmin;
    @BindView(R.id.area_phone)
    EditText areaPhone;
    @BindView(R.id.area_address)
    EditText areaAddress;
    @BindView(R.id.area_employee)
    CheckBox areaEmployee;
    @BindView(R.id.area_repeat)
    CheckBox areaRepeat;
    @BindView(R.id.area_notes)
    EditText areaNotes;
    private int id;
    private String name="";
    private String admin="";
    private String phone="";
    private String address="";
    private String notes="";
    private String is_employee="";
    private String if_repeat="";
    private String parent_id="";
    private Intent it;
    private Map<String,String> map;
    private  int type=-4;//-1 编辑 0--  1-- 2--
    private AddAreaPresenter addAreaPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_area);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        it=getIntent();
        type=it.getIntExtra("type",-4);
        setTitle(type<0?"编辑部门":"添加部门");
        setRightText("确定");
        map=new HashMap<>();
        addAreaPresenter=new AddAreaPresenter(this,this);
        if(type<0){
            initAreainfo();
        }else{
            parent_id=it.getStringExtra("parent_id");
        }
    }
    private void initAreainfo() {
        id=it.getIntExtra("id",0);
        name=it.getStringExtra("name");
        areaName.setText(name);
        admin=it.getStringExtra("admin");
        areaAdmin.setText(admin);
        phone=it.getStringExtra("phone");
        areaPhone.setText(phone);
        address=it.getStringExtra("address");
        areaAddress.setText(address);
        notes=it.getStringExtra("notes");
        areaNotes.setText(notes);
        areaEmployee.setChecked(it.getIntExtra("is_employee",0)==1?true:false);
        is_employee=String.valueOf(it.getIntExtra("is_employee",0));
        areaRepeat.setChecked(it.getIntExtra("if_repeat",0)==1?true:false);
        if_repeat=String.valueOf(it.getIntExtra("if_repeat",0));
        parent_id=it.getStringExtra("parent_id");
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        commit();
    }
    private void commit() {
        if (juge()) {
            map.put("name",name);
            map.put("notes",notes);
            map.put("admin",admin);
            map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
            map.put("phone",phone);
            map.put("address",address);
            map.put("is_employee",is_employee);
            map.put("if_repeat",if_repeat);
            map.put("parent_id",type==0?"0":parent_id);
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if(type<0) {
                map.put("id",String.valueOf(id));
                addAreaPresenter.editArea(map,dialog);
            }else{
                addAreaPresenter.addArea(map,dialog);
            }
        }
    }
    private boolean juge() {
        name = areaName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "请填写地区名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        admin = areaAdmin.getText().toString();
        phone = areaPhone.getText().toString();
        address = areaAddress.getText().toString();
        notes = areaNotes.getText().toString();
        is_employee=areaEmployee.isChecked()?"1":"0";
        if_repeat=areaRepeat.isChecked()?"1":"0";
        return true;
    }
    @Override
    public void addArea(HttpBean<KidBean> httpBean) {
        Toast.makeText(this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
        map.put("id",String.valueOf(httpBean.getData().getId()));
        EventBus.getDefault().post(new AddAreaThreeEvent(1,map));
        finish();
    }
    @Override
    public void editArea(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new AddAreaThreeEvent(2,map));
        finish();
    }
}
