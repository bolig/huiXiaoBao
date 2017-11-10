package com.dhitoshi.xfrs.huixiaobao.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TribeInfoView extends BaseView {
    @BindView(R.id.tribe_name)
    TextView tribeName;
    @BindView(R.id.tribe_id)
    TextView tribeId;
    @BindView(R.id.tribe_description)
    EditText tribeDescription;
    @BindView(R.id.tribe_number)
    TextView tribeNumber;
    @BindView(R.id.nick_name)
    TextView nickName;
    @BindView(R.id.quite_tribe)
    TextView quiteTribe;
    @BindView(R.id.tribe_manage)
    RelativeLayout tribeManage;
    @BindView(R.id.tribe_nick)
    RelativeLayout tribeNick;
    private YWIMKit mIMKit;
    private long mTribeId;
    private YWTribe mTribe;
    private Intent intent;
    private int role = 0;//1-- 群主 2--成员
    private int join = -1;//1-- 搜索加群
    private IYWTribeService mTribeService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tribe_info_view);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        initTitle();
    }

    private void initTitle() {
        initBaseViews();
        setTitle("群资料");
        mTribeId = getIntent().getLongExtra(TribeConstants.TRIBE_ID, 0);
        mTribeId = getIntent().getIntExtra("join", -1);
        tribeId.setText("群号 " + mTribeId);
        getTribeInfoById(mTribeId);
    }

    //根据群id获取群消息
    public void getTribeInfoById(long tribeId) {
        mTribe = mTribeService.getTribe(tribeId);
        tribeName.setText(mTribe.getTribeName());
        tribeDescription.setText(mTribe.getTribeNotice());
        role = mTribe.getTribeRole() == null ? 2 : mTribe.getTribeRole().type;
        //nickName.setText(mTribe.g);
        quiteTribe.setText(role == 1 ? "解散群" : join == 1 ? "加入群" : "退出群");
        tribeManage.setVisibility(join == 1 ? View.GONE : View.VISIBLE);
        tribeNick.setVisibility(join == 1 ? View.GONE : View.VISIBLE);
    }

    @OnClick({R.id.tribe_manage, R.id.tribe_nick, R.id.quite_tribe, R.id.tribe_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tribe_manage:
                if (role == 1) {
                    intent = new Intent(TribeInfoView.this, TribeMembers.class);
                    intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
                    startActivity(intent);
                }
                break;
            case R.id.tribe_nick:
                intent = new Intent(TribeInfoView.this, EditMyTribeProfile.class);
                intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
                intent.putExtra(TribeConstants.TRIBE_NICK, nickName.getText().toString());
                startActivityForResult(intent, 1);
                break;
            case R.id.quite_tribe:
                operareTribe();
                break;
            case R.id.tribe_info:
                if (role == 1) {
                    intent = new Intent(TribeInfoView.this, EditTribe.class);
                    intent.putExtra("tribeId", mTribeId);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }
                break;
        }
    }

    private void operareTribe() {
        if (join == 1) {
            joinTribe();
        } else {
            if (role == 1) {
                disbandTribe();
            } else {
                exitFromTribe();
            }
        }
    }

    //加入群
    private void joinTribe() {
        mTribeService.joinTribe(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                if (result != null && result.length > 0) {
                    Integer retCode = (Integer) result[0];
                    if (retCode == 0) {
                        IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "加入群成功！");
//                        mTribeOp = null;
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateView();
//                            }
//                        });
                    }
                }
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "加入群失败");
            }

            @Override
            public void onProgress(int progress) {
            }
        }, mTribeId);
    }

    //退出群
    private void exitFromTribe() {
        mTribeService.exitFromTribe(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "退出群成功！");
                finish();
                if (join == -1) {
                    ActivityManagerUtil.destoryActivity("TribeChat");
                }
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "退出群失败");
            }

            @Override
            public void onProgress(int progress) {
            }
        }, mTribeId);
    }

    //解散群
    private void disbandTribe() {
        mTribeService.disbandTribe(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "解散群成功");
                finish();
                if (join == -1) {
                    ActivityManagerUtil.destoryActivity("TribeChat");
                }
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast(TribeInfoView.this, "解散群失败");
            }

            @Override
            public void onProgress(int progress) {
            }
        }, mTribeId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                if (data != null) {
                    String newUserNick = data.getStringExtra(TribeConstants.TRIBE_NICK);
                    nickName.setText(newUserNick);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
