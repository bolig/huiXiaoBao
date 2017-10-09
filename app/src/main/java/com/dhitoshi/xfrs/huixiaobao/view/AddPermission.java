package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.PermissionEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddPermissionManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddPermissionPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPermission extends BaseView implements AddPermissionManage.View {
    @BindView(R.id.permission_area)
    TextView permissionArea;
    @BindView(R.id.permission_name)
    EditText permissionName;
    @BindView(R.id.super_attend)
    CheckBox superAttend;
    @BindView(R.id.permission_notes)
    EditText permissionNotes;
    private String name;
    private String area;
    private String is_super;
    private String notes;
    private UserRole userRole;
    private AddPermissionPresenter addPermissionPresenter;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_permission);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AddPermission");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AddPermission");
    }

    private void initViews() {
        initBaseViews();
        setTitle("权限组");
        setRightText("提交");
        addPermissionPresenter=new AddPermissionPresenter(this,this);
        userRole = getIntent().getParcelableExtra("permission");
        map=new HashMap<>();
        if(userRole!=null){
            initPermissionInfo();
        }
    }
    private void initPermissionInfo() {
        area=userRole.getArea_id();
        permissionArea.setText(userRole.getArea());
        permissionArea.setTextColor(getResources().getColor(R.color.colorPrimary));
        name=userRole.getName();
        permissionName.setText(userRole.getName());
        is_super=String.valueOf(userRole.getIs_super());
        superAttend.setChecked(userRole.getIs_super()==1?true:false);
        notes=userRole.getNotes();
        permissionNotes.setText(userRole.getNotes());
    }
    @Override
    public void addGroup(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new PermissionEvent(1));
        finish();
    }
    @Override
    public void editGroup(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new PermissionEvent(1));
        finish();
    }
    @OnClick({R.id.right_text, R.id.permission_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.permission_area:
                selectArea();
                break;
        }
    }
    private void selectArea() {
        startActivityForResult(new Intent(this,SelectArea.class),101);
    }
    private void commit() {
        if(juge()){
            map.put("area",area);
            map.put("name",name);
            map.put("notes",notes);
            map.put("is_super",is_super);
            String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
            map.put("token",token);
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if(userRole==null) {
                addPermissionPresenter.addGroup(map,dialog);
            }else{
                map.put("id",String.valueOf(userRole.getId()));
                addPermissionPresenter.editGroup(map,dialog);
            }
        }
    }
    private boolean juge() {
        if(area.isEmpty()){
            Toast.makeText(this,"请选择所属区域",Toast.LENGTH_SHORT).show();
            return false;
        }
        name=permissionName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写组名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        is_super=superAttend.isChecked()?"1":"0";
        notes=permissionNotes.getText().toString();
        if(notes.isEmpty()){
            Toast.makeText(this,"请填写权限组描述",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==200){
            switch (requestCode){
                case 101:
                    area=data.getStringExtra("area_id");
                    permissionArea.setText(data.getStringExtra("area_name"));
                    permissionArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
