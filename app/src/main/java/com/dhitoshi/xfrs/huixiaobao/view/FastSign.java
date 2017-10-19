package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.UserEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.FastSignManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.FastSignPresenter;
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

public class FastSign extends BaseView implements FastSignManage.View{

    @BindView(R.id.user_names)
    EditText userNames;
    @BindView(R.id.fast_password)
    EditText fastPassword;
    @BindView(R.id.fast_area)
    TextView fastArea;
    @BindView(R.id.fast_permission)
    TextView fastPermission;
    private String name;
    private String area;
    private String group;
    private String password;
    private  String token;
    private Map<String, String> map;
    private ArrayList<UserRole> userRoles;
    private FastSignPresenter fastSignPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fast_sign);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"FastSign");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("FastSign");
    }

    private void initViews() {
        initBaseViews();
        setRightText("确定");
        setTitle("用户快速新增");
        fastSignPresenter=new FastSignPresenter(this,this);

        fastSignPresenter.getGroupLists(token);
    }

    @OnClick({R.id.right_text, R.id.fast_area, R.id.fast_permission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.fast_area:
                selectArea();
                break;
            case R.id.fast_permission:
                if(null==userRoles){
                    reGroupLists();
                }else {
                    selectPermission();
                }
                break;
        }
    }
    private void selectPermission() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",userRoles)
                .putExtra("type",10).putExtra("select",fastPermission.getText().toString()),10);
    }

    private void selectArea() {
        startActivityForResult(new Intent(this, SelectArea.class), 111);
    }
    private void commit() {
        if(juge()){
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("area", area);
            map.put("group", group);
            map.put("CRM", "1");
            map.put("APP", "1");
            token= SharedPreferencesUtil.Obtain(this,"tooken","").toString();
            map.put("token",token);
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            map.put("password", password);
            map.put("name", name);
            fastSignPresenter.fastSign(map,dialog);
        }
    }
    private boolean juge() {
        name = userNames.getText().toString();
        password = fastPassword.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "请填写用户名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (area.isEmpty()) {
            Toast.makeText(this, "请选择所属部门", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "请填写密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (group.isEmpty()) {
            Toast.makeText(this, "请选择所属组", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void reGroupLists(){
        MyHttp http=MyHttp.getInstance();
        String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
        http.send(http.getHttpService().getGroupLists(token),new CommonObserver(new HttpResult<HttpBean<List<UserRole>>>() {
            @Override
            public void OnSuccess(HttpBean<List<UserRole>> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    userRoles = (ArrayList<UserRole>) httpBean.getData();
                    selectPermission();
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(FastSign.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            reGroupLists();
                        }
                    });
                }
                else{
                    Toast.makeText(FastSign.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(FastSign.this,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 200) {
            switch (requestCode) {
                case 111:
                    area = data.getStringExtra("area_id");
                    fastArea.setText(data.getStringExtra("area_name"));
                    fastArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 10:
                    group=data.getStringExtra("id");
                    fastPermission.setText(data.getStringExtra("name"));
                    fastPermission.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }

    @Override
    public void fastSign(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new UserEvent(1));
        finish();
    }
    @Override
    public void getGroupLists(HttpBean<List<UserRole>> httpBean) {
        userRoles = (ArrayList<UserRole>) httpBean.getData();
    }
}
