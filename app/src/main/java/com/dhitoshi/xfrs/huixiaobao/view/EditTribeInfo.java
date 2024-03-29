package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.fundamental.widget.WXNetworkImageView;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeType;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.tribe.YWTribeCreationParam;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.presenter.TribePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditTribeInfo extends BaseView implements TribeManage.View{

    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private String mTribeOp;
    private long mTribeId;
    private String mTribeType;
    private EditText mTribeName;
    private EditText mTribeNotice;
    private String oldTribeName;
    private String oldTribeNotice;
    private ModifyTribeInfoCallback callback;
    private TribePresenter tribePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_edit_tribe_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();

        mTribeOp = getIntent().getStringExtra(TribeConstants.TRIBE_OP);
        if (mTribeOp.equals(TribeConstants.TRIBE_CREATE)) {  //创建群
            mTribeType = getIntent().getStringExtra(TribeConstants.TRIBE_TYPE);
        } else if (mTribeOp.equals(TribeConstants.TRIBE_EDIT)) { //编辑群信息
            mTribeId = getIntent().getLongExtra(TribeConstants.TRIBE_ID, 0);
        }
        mTribeName = (EditText) findViewById(R.id.tribe_name);
        mTribeNotice = (EditText) findViewById(R.id.tribe_description);
        YWTribe tribe = mTribeService.getTribe(mTribeId);
        if (tribe != null) {
            oldTribeName = tribe.getTribeName();
            oldTribeNotice = tribe.getTribeNotice();
            mTribeName.setText(tribe.getTribeName());
            mTribeNotice.setText(tribe.getTribeNotice());
        }
        initTitle();
        tribePresenter=new TribePresenter(this,this);
    }

    private void initTitle() {
        initBaseViews();
        if (TextUtils.isEmpty(mTribeType)) {
            setTitle("编辑群信息");
        } else if (mTribeType.equals(YWTribeType.CHATTING_GROUP.toString())) {
            setTitle("创建平台交流群");
        } else if (mTribeType.equals(YWTribeType.CHATTING_TRIBE.toString())) {
            setTitle("创建企业内部群");
        }
        setRightText("提交");
    }

    private void updateTribeInfo() {
        String name = mTribeName.getText().toString();
        String notice = mTribeNotice.getText().toString();
        if (name.equals(oldTribeName) && notice.equals(oldTribeNotice)) {
            //没有修改,不提交服务端
            finish();
            return;
        }
        if (callback == null) {
            callback = new ModifyTribeInfoCallback();
        }
        if (name.equals(oldTribeName) && !notice.equals(oldTribeNotice)) {
            mTribeService.modifyTribeInfo(callback, mTribeId, null, notice);
        } else if (!name.equals(oldTribeName) && notice.equals(oldTribeNotice)) {
            mTribeService.modifyTribeInfo(callback, mTribeId, name, null);
        } else {
            mTribeService.modifyTribeInfo(callback, mTribeId, name, notice);
        }
    }

    private void createTribe(final YWTribeType type) {
        List<String> users = new ArrayList<>();
        users.add(mIMKit.getIMCore().getLoginUserId());
        YWTribeCreationParam param = new YWTribeCreationParam();
        param.setTribeType(type);
        param.setTribeName(mTribeName.getText().toString());
        param.setNotice(mTribeNotice.getText().toString());
        param.setUsers(users);
        mTribeService.createTribe(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                if (result != null && result.length > 0) {
                    YWTribe tribe = (YWTribe) result[0];
                    if (type.equals(YWTribeType.CHATTING_TRIBE)) {
                        IMNotificationUtils.getInstance().showToast(EditTribeInfo.this, "创建群组成功！");
                    } else {
                        IMNotificationUtils.getInstance().showToast(EditTribeInfo.this, "创建讨论组成功！");
                    }
                    Intent intent = new Intent(EditTribeInfo.this, TribeInfo.class);
                    intent.putExtra(TribeConstants.TRIBE_ID, tribe.getTribeId());
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast(EditTribeInfo.this, "创建讨论组失败，code = " + code + ", info = " + info);
            }

            @Override
            public void onProgress(int progress) {

            }
        }, param);
    }

    @OnClick(R.id.right_text)
    public void onViewClicked() {
        if (mTribeOp.equals(TribeConstants.TRIBE_EDIT)) {
            updateTribeInfo();
        } else if (mTribeType.equals(YWTribeType.CHATTING_GROUP.toString())) {
            createTribe(YWTribeType.CHATTING_GROUP);
        } else if (mTribeType.equals(YWTribeType.CHATTING_TRIBE.toString())) {
            createTribe(YWTribeType.CHATTING_TRIBE);
        }
    }
    //创建群组
    @Override
    public void addTribe(HttpBean<TribeBean> httpBean) {

    }
    //编辑群组资料
    @Override
    public void editTribe(HttpBean<TribeBean> httpBean) {

    }

    class ModifyTribeInfoCallback implements IWxCallback {
        @Override
        public void onSuccess(Object... result) {
            IMNotificationUtils.getInstance().showToast(EditTribeInfo.this, "修改群信息成功！");
            finish();
        }

        @Override
        public void onError(int code, String info) {
            IMNotificationUtils.getInstance().showToast(EditTribeInfo.this, "修改群信息失败，code = " + code + ", info = " + info);
        }

        @Override
        public void onProgress(int progress) {

        }
    }

}
