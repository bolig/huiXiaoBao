package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.UpdateManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.UpdatePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManager;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePassword extends BaseView implements UpdateManage.View {
    @BindView(R.id.update_oldPsw)
    EditText updateOldPsw;
    @BindView(R.id.update_newPsw)
    EditText updateNewPsw;
    @BindView(R.id.update_confirm)
    EditText updateConfirm;
    private String name="";
    private String password="";
    private String new_password="";
    private String comfirm_password="";
    private Map<String,String> map;
    private UpdatePresenter updatePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_password);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("修改密码");
        updatePresenter=new UpdatePresenter(this,this);
    }
    @Override
    public void resetPassword(HttpBean<Object> httpBean) {
        Toast.makeText(this,httpBean.getStatus().getMsg()+"，请重新登录...",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Login.class));
        SharedPreferencesUtil.Obtain(this,"token","");
        SharedPreferencesUtil.Save(this, "isRemeber",false);
        ActivityManager.destoryActivity("Theme");
        finish();
    }
    @OnClick(R.id.update)
    public void onViewClicked() {
        password=updateOldPsw.getText().toString();
        if(password.isEmpty()){
            Toast.makeText(this,"请输入旧密码",Toast.LENGTH_SHORT).show();
            return;
        }
        new_password=updateNewPsw.getText().toString();
        if(new_password.isEmpty()){
            Toast.makeText(this,"请输入旧密码",Toast.LENGTH_SHORT).show();
            return;
        }
        comfirm_password=updateConfirm.getText().toString();
        if(!new_password.equals(comfirm_password)){
            Toast.makeText(this,"确认密码与新密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }
        if(map==null){
            map=new HashMap<>();
        }
        map.put("password",password);
        map.put("new_password",new_password);
        map.put("name",SharedPreferencesUtil.Obtain(this,"account","").toString());
        map.put("token", SharedPreferencesUtil.Obtain(this,"token","").toString());
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        updatePresenter.resetPassword(map,dialog);
    }
}
