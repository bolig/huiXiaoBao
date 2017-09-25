package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.GiftEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.UserEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddUserManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddUserPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddUser extends BaseView implements AddUserManage.View {
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_trueName)
    EditText userTrueName;
    @BindView(R.id.user_area)
    TextView userArea;
    @BindView(R.id.user_pswd)
    EditText userPswd;
    @BindView(R.id.user_permission)
    TextView userPermission;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_email)
    EditText userEmail;
    @BindView(R.id.user_crm)
    CheckBox userCrm;
    @BindView(R.id.user_app)
    CheckBox userApp;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.view_name)
    View viewName;
    @BindView(R.id.ll_pswd)
    LinearLayout llPswd;
    @BindView(R.id.view_pswd)
    View viewPswd;
    private String name="";
    private String truename="";
    private String area="";
    private String group="";
    private String phone="";
    private String email="";
    private String password="";
    private String CRM="";
    private String APP="";
    private String token="";
    private Map<String, String> map;
    private UserBean userBean;
    private AddUserPresenter addUserPresenter;
    private ArrayList<UserRole> userRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        initBaseViews();
        userBean = getIntent().getParcelableExtra("user");
        setTitle(userBean == null ? "新增用户" : "编辑用户");
        setRightText("确定");
        addUserPresenter = new AddUserPresenter(this, this);

        addUserPresenter.getGroupLists(token);
        if (userBean != null) {
            llPswd.setVisibility(View.GONE);
            llName.setVisibility(View.GONE);
            viewPswd.setVisibility(View.GONE);
            viewName.setVisibility(View.GONE);
            initUserInfo();
        }
    }

    private void initUserInfo() {
        truename = userBean.getTruename();
        userTrueName.setText(userBean.getTruename());
        area = userBean.getArea_id();
        userArea.setText(userBean.getArea());
        userArea.setTextColor(getResources().getColor(R.color.colorPrimary));
        userPermission.setText(userBean.getGroup());
        userPermission.setTextColor(getResources().getColor(R.color.colorPrimary));
        phone = userBean.getPhone();
        userPhone.setText(userBean.getPhone());
        email = userBean.getEmail();
        userEmail.setText(userBean.getEmail());
        CRM = String.valueOf(userBean.getCRM());
        userCrm.setChecked(userBean.getCRM() == 1 ? true : false);
        APP = String.valueOf(userBean.getAPP());
        userApp.setChecked(userBean.getAPP() == 1 ? true : false);
    }

    @OnClick({R.id.right_text, R.id.user_area, R.id.user_permission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.user_area:
                selectArea();
                break;
            case R.id.user_permission:
                selectPermission();
                break;
        }
    }

    private void commit() {
        if (juge()) {
            if (map == null) {
                map = new HashMap<>();
            }
            map.put("truename", truename);
            map.put("area", area);
            map.put("group", group);
            map.put("phone", phone);
            map.put("email", email);
            map.put("CRM", CRM);
            map.put("APP", APP);
            token = SharedPreferencesUtil.Obtain(this, "token", "").toString();
            map.put("token", token);
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if (userBean == null) {
                map.put("password", password);
                map.put("name", name);
                addUserPresenter.signUp(map, dialog);
            } else {
                map.put("id", String.valueOf(userBean.getId()));
                addUserPresenter.editUser(map, dialog);
            }
        }
    }

    private boolean juge() {
        name = userName.getText().toString();
        truename = userTrueName.getText().toString();
        password = userPswd.getText().toString();
        phone = userPhone.getText().toString();
        email = userEmail.getText().toString();
        CRM = userCrm.isChecked() ? "1" : "0";
        APP = userApp.isChecked() ? "1" : "0";
        if(userBean==null){
            if (name.isEmpty()) {
                Toast.makeText(this, "请填写用户名", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (area.isEmpty()) {
                Toast.makeText(this, "请选择所属地区", Toast.LENGTH_SHORT).show();
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
        }
        return true;
    }

    private void selectArea() {
        startActivityForResult(new Intent(this, SelectArea.class), 110);
    }

    private void selectPermission() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",userRoles)
                .putExtra("type",10).putExtra("select",userPermission.getText().toString()),10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 200) {
            switch (requestCode) {
                case 110:
                    area = data.getStringExtra("area_id");
                    userArea.setText(data.getStringExtra("area_name"));
                    userArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 10:
                    group=data.getStringExtra("id");
                    userPermission.setText(data.getStringExtra("name"));
                    userPermission.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }

    @Override
    public void signUp(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new UserEvent(1));
        finish();
    }

    @Override
    public void editUser(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new UserEvent(1));
        finish();
    }

    @Override
    public void getGroupLists(HttpBean<List<UserRole>> httpBean) {
        userRoles = (ArrayList<UserRole>) httpBean.getData();
        if (userBean != null) {
            for (int j = 0; j < userRoles.size(); j++) {
                if (userBean.getGroup().equals(userRoles.get(j).getName())) {
                    group = String.valueOf(userRoles.get(j).getId());
                }
            }
        }
    }

}
