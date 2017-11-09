package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.presenter.TribePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.HashMap;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class EditTribe extends BaseView implements TribeManage.View {
    @BindView(R.id.tribe_name)
    EditText tribeName;
    @BindView(R.id.tribe_description)
    EditText tribeDescription;
    private TribePresenter tribePresenter;
    private int tribeType = 0;//0--创建平台交流群 1--创建企业内部群 2--编辑群信息
    private String name;
    private String description;
    private Map<String,String> map;
    private LoadingDialog dialog;
    private long tribeId=0;
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tribe);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        initBaseViews();
        tribePresenter = new TribePresenter(this, this);
        tribeType = getIntent().getIntExtra("type", 0);
        if(tribeType==2){
            tribeId=getIntent().getLongExtra("tribeId", 0);
            String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
            mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
            mTribeService = mIMKit.getTribeService();
            YWTribe tribe = mTribeService.getTribe(tribeId);
            name=tribe.getTribeName();
            tribeName.setText(tribe.getTribeName());
            description=tribe.getTribeNotice();
            tribeDescription.setText(tribe.getTribeNotice());
        }
        setTitle(tribeType == 0 ? "创建企业内部群" : tribeType == 1 ? "创建平台交流群" : "编辑群信息");
        setRightText("提交");
    }
    @Override
    public void addTribe(HttpBean<TribeBean> httpBean) {
        Toast.makeText(this, httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditTribe.this, TribeInfo.class);
        intent.putExtra(TribeConstants.TRIBE_ID, Long.parseLong(httpBean.getData().getTribe_number()));
        startActivity(intent);
        finish();
    }
    @Override
    public void editTribe(HttpBean<TribeBean> httpBean) {
        Toast.makeText(this, httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        if(tribeType==2){
            updateTribeInfo();
        }else{
            if(judge()){
                createTribe(tribeType);
            }
        }
    }
    private boolean judge() {
        name=tribeName.getText().toString();
        description=tribeDescription.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "请输入群名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void updateTribeInfo() {
        if(null==map){
            map=new HashMap<>();
        }
        map.put("name",name);
        map.put("description",description);
        map.put("tribe_id",String.valueOf(tribeId));
        if(null==dialog){
            dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        }
        dialog.show();
        String token = SharedPreferencesUtil.Obtain(this, "token", "").toString();
        map.put("token", token);
        tribePresenter.editTribe(map,dialog);
    }
    private void createTribe(int tribeType) {
        if(null==map){
            map=new HashMap<>();
        }
        map.put("name",name);
        map.put("description",description);
        map.put("tribe_type",String.valueOf(tribeType));
        if(null==dialog){
            dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        }
        dialog.show();
        String token = SharedPreferencesUtil.Obtain(this, "token", "").toString();
        map.put("token", token);
        tribePresenter.addTribe(map,dialog);
    }
}
