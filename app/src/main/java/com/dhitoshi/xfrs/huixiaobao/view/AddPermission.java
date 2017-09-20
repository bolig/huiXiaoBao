package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;

import com.dhitoshi.xfrs.huixiaobao.Interface.AddPermissionManage;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddPermission extends BaseView implements AddPermissionManage.View{
    private String name;
    private String area;
    private String is_super;
    private String notes;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_permission);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("权限组");
        setRightText("提交");
    }
    @Override
    public void addGroup(String result) {

    }
    @Override
    public void editGroup(String result) {

    }
}
