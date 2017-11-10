package com.dhitoshi.xfrs.huixiaobao.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.gingko.model.settings.YWTribeSettingsModel;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeCheckMode;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeMember;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeRole;
import com.alibaba.mobileim.gingko.presenter.tribe.IYWTribeChangeListener;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.xfrs.huixiaobao.Event.NewsEvent;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
/**
 * 该类演示了如何获取群消息接收状态以及对群消息接收状态的设置
 */
public class TribeInfo extends BaseView {
    private static final int EDIT_MY_TRIBE_NICK_REQUEST_CODE = 10001;
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private YWTribe mTribe;
    private long mTribeId;
    private String mTribeOp;
    private int mTribeMemberCount;
    List<YWTribeMember> mList = new ArrayList<>();
    private IYWTribeChangeListener mTribeChangedListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private TextView mTribeName;
    private TextView mTribeDesc;
    private TextView mMemberCount;
    private TextView mQuiteTribe;
    private TextView mMangeTribeMembers;
    private TextView mMyTribeNick;
    private RelativeLayout mMangeTribeMembersLayout;
    private RelativeLayout mEditMyTribeProfileLayout;
    private RelativeLayout mEditTribeInfoLayout;
    private YWTribeMember mLoginUser;
    private int role=0;//1-- 群主 2--成员
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_tribe_info);
        Intent intent = getIntent();
        mTribeId = intent.getLongExtra(TribeConstants.TRIBE_ID, 0);
        mTribeOp = intent.getStringExtra(TribeConstants.TRIBE_OP);
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        initTribeChangedListener();
        initTribeInfo();
        initView();
    }
    private void initTitle() {
        initBaseViews();
        setTitle("群资料");
    }
    private void initView() {
        initTitle();
        mTribeName = (TextView) findViewById(R.id.tribe_name);
        final TextView tribeId = (TextView) findViewById(R.id.tribe_id);
        tribeId.setText("群号 " + mTribeId);
        mTribeDesc = (TextView) findViewById(R.id.tribe_description);
        mMemberCount = (TextView) findViewById(R.id.member_count);
        mMangeTribeMembers = (TextView) findViewById(R.id.manage_tribe_members);
        mMangeTribeMembersLayout = (RelativeLayout) findViewById(R.id.manage_tribe_members_layout);
        mMangeTribeMembersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(TribeInfo.this, TribeMembers.class);
            intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
            intent.putExtra("tribeType",mTribe.getTribeType().type);
            startActivity(intent);

            }
        });
        mEditTribeInfoLayout = (RelativeLayout) findViewById(R.id.tribe_info);
        mEditTribeInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role==1){
                    Intent intent = new Intent(TribeInfo.this, EditTribe.class);
                    intent.putExtra("tribeId", mTribeId);
                    intent.putExtra("type", 2);
                    startActivity(intent);
                }else{
                    Toast.makeText(TribeInfo.this, "仅群主可修改群资料", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mMyTribeNick = (TextView) findViewById(R.id.my_tribe_nick);
        mMyTribeNick.setText(getLoginUserTribeNick());
        mEditMyTribeProfileLayout = (RelativeLayout) findViewById(R.id.my_tribe_profile_layout);
        mEditMyTribeProfileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TribeInfo.this, EditMyTribeProfile.class);
                intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
                intent.putExtra(TribeConstants.TRIBE_NICK, mMyTribeNick.getText());
                startActivityForResult(intent, EDIT_MY_TRIBE_NICK_REQUEST_CODE);
            }
        });
        mQuiteTribe = (TextView) findViewById(R.id.quite_tribe);

    }
    private void updateView() {
        mTribeName.setText(mTribe.getTribeName());
        mTribeDesc.setText(mTribe.getTribeNotice());
        mMyTribeNick.setText(getLoginUserTribeNick());
        if (mTribeMemberCount > 0) {
            mMemberCount.setText(mTribeMemberCount + "人");
        }
        if (role == YWTribeRole.TRIBE_HOST.type) {
            mMangeTribeMembers.setText("群成员管理");
            mQuiteTribe.setText("解散群");
            mQuiteTribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTribeService.disbandTribe(new IWxCallback() {
                        @Override
                        public void onSuccess(Object... result) {
                            IMNotificationUtils.getInstance().showToast(TribeInfo.this, "解散群成功！");
                            openTribeListFragment();
                        }
                        @Override
                        public void onError(int code, String info) {
                            IMNotificationUtils.getInstance().showToast(TribeInfo.this, "解散群失败");
                        }
                        @Override
                        public void onProgress(int progress) {

                        }
                    }, mTribeId);
                }
            });
        } else {
            mMangeTribeMembers.setText("群成员列表");
            mQuiteTribe.setText("退出群");
            mQuiteTribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTribeService.exitFromTribe(new IWxCallback() {
                        @Override
                        public void onSuccess(Object... result) {
                            IMNotificationUtils.getInstance().showToast(TribeInfo.this, "退出群成功！");
                            openTribeListFragment();
                        }
                        @Override
                        public void onError(int code, String info) {
                            IMNotificationUtils.getInstance().showToast(TribeInfo.this, "退出群失败" );
                        }
                        @Override
                        public void onProgress(int progress) {
                        }
                    }, mTribeId);
                }
            });
        }
        if (!TextUtils.isEmpty(mTribeOp)) {
            mMangeTribeMembersLayout.setVisibility(View.GONE);
            mEditMyTribeProfileLayout.setVisibility(View.GONE);
            mQuiteTribe.setText("加入群");
            mQuiteTribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTribeService.joinTribe(new IWxCallback() {
                        @Override
                        public void onSuccess(Object... result) {
                            if (result != null && result.length > 0) {
                                Integer retCode = (Integer) result[0];
                                if (retCode == 0) {

                                    IMNotificationUtils.getInstance().showToast(TribeInfo.this, "加入群成功！");
                                    mTribeOp = null;
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            updateView();
                                        }
                                    });
                                }
                            }
                        }
                        @Override
                        public void onError(int code, String info) {
                            IMNotificationUtils.getInstance().showToast(TribeInfo.this, "加入群失败");
                        }
                        @Override
                        public void onProgress(int progress) {

                        }
                    }, mTribeId);
                }
            });
        } else {
            if (role == YWTribeRole.TRIBE_MEMBER.type) {
                mMangeTribeMembersLayout.setVisibility(View.VISIBLE);
                mEditMyTribeProfileLayout.setVisibility(View.VISIBLE);
            } else {
                mMangeTribeMembersLayout.setVisibility(View.VISIBLE);
                mEditMyTribeProfileLayout.setVisibility(View.VISIBLE);
            }
        }
    }
    private void updateTribeMemberCount() {
        if (mTribeMemberCount > 0) {
            mMemberCount.setText(mTribeMemberCount + "人");
        }
    }
    private void openTribeListFragment() {
        EventBus.getDefault().post(new NewsEvent(1));
        finish();
        if (TextUtils.isEmpty(mTribeOp)) {
            ActivityManagerUtil.destoryActivity("TribeChat");
        }
    }
    private void initTribeInfo() {
        mTribe = mTribeService.getTribe(mTribeId);
        role = mTribe.getTribeRole() == null ? 4 : mTribe.getTribeRole().type;
        mTribeService.addTribeListener(mTribeChangedListener);
        initTribeMemberList();
        getTribeInfoFromServer();
    }
    private void getTribeInfoFromServer() {
        mTribeService.getTribeFromServer(new IWxCallback() {
            @Override
            public void onSuccess(final Object... result) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTribe = (YWTribe)result[0];
                        mTribeMemberCount = mTribe.getMemberCount();
                        updateView();
                    }
                });
            }

            @Override
            public void onError(int code, String info) {

            }

            @Override
            public void onProgress(int progress) {

            }
        }, mTribeId);
    }
    private void initTribeMemberList() {
        getTribeMembersFromLocal();
        getTribeMembersFromServer();
    }
    private void getTribeMembersFromLocal() {
        mTribeService.getMembers(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                mList.clear();
                mList.addAll((List<YWTribeMember>) result[0]);
                if (mList != null || mList.size() > 0) {
                    mTribeMemberCount = mList.size();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            initLoginUser();
                            updateView();
                        }
                    });
                }
            }

            @Override
            public void onError(int code, String info) {

            }

            @Override
            public void onProgress(int progress) {

            }
        }, mTribeId);
    }
    private void getTribeMembersFromServer() {
        mTribeService.getMembersFromServer(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                mList.clear();
                mList.addAll((List<YWTribeMember>) result[0]);
                if (mList != null || mList.size() > 0) {
                    mTribeMemberCount = mList.size();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            initLoginUser();
                            updateView();
                        }
                    });
                }
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast(TribeInfo.this, "error, code = " + code + ", info = " + info);
            }

            @Override
            public void onProgress(int progress) {

            }
        }, mTribeId);
    }
    private void initLoginUser(){
        String loginUser = mIMKit.getIMCore().getLoginUserId();
        for (YWTribeMember member : mList) {
            if (member.getUserId().equals(loginUser)) {
                mLoginUser = member;
                break;
            }
        }
    }
    //获取登录用户的群昵称
    private String getLoginUserTribeNick() {
        if (mLoginUser != null && !TextUtils.isEmpty(mLoginUser.getTribeNick())) {
            return mLoginUser.getTribeNick();
        }
        String tribeNick = null;
        IYWContactService contactService = mIMKit.getContactService();
        IYWContact contact = contactService.getContactProfileInfo(mIMKit.getIMCore().getLoginUserId(), mIMKit.getIMCore().getAppKey());
        if (contact != null){
            if (!TextUtils.isEmpty(contact.getShowName())){
                tribeNick = contact.getShowName();
            } else {
                tribeNick =  contact.getUserId();
            }
        }
        if(TextUtils.isEmpty(tribeNick)) {
            tribeNick = mIMKit.getIMCore().getLoginUserId();
        }
        return tribeNick;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTribeService.removeTribeListener(mTribeChangedListener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == EDIT_MY_TRIBE_NICK_REQUEST_CODE) {
                if (data != null) {
                    String newUserNick = data.getStringExtra(TribeConstants.TRIBE_NICK);
                    mMyTribeNick.setText(newUserNick);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void initTribeChangedListener() {
        mTribeChangedListener = new IYWTribeChangeListener() {
            @Override
            public void onInvite(YWTribe tribe, YWTribeMember user) {

            }
            @Override
            public void onUserJoin(YWTribe tribe, YWTribeMember user) {
                mTribeMemberCount = tribe.getMemberCount();
                if(mIMKit.getIMCore().getLoginUserId().equals(user.getUserId())) {
                    getTribeInfoFromServer();
                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            updateTribeMemberCount();
                        }
                    });
                }
            }
            @Override
            public void onUserQuit(YWTribe tribe, YWTribeMember user) {
                mTribeMemberCount = tribe.getMemberCount();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateTribeMemberCount();
                    }
                });
            }
            @Override
            public void onUserRemoved(YWTribe tribe, YWTribeMember user) {
                openTribeListFragment();
            }
            @Override
            public void onTribeDestroyed(YWTribe tribe) {
                openTribeListFragment();
            }
            @Override
            public void onTribeInfoUpdated(YWTribe tribe) {
                mTribe = tribe;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        updateView();
                    }
                });
            }
            @Override
            public void onTribeManagerChanged(YWTribe tribe, YWTribeMember user) {
                String loginUser = mIMKit.getIMCore().getLoginUserId();
                if (loginUser.equals(user.getUserId())) {
                    for (YWTribeMember member : mList) {
                        if (member.getUserId().equals(loginUser)) {
                            mList.remove(member);
                            mList.add(user);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateView();
                                }
                            });
                            break;
                        }
                    }
                }
            }
            @Override
            public void onTribeRoleChanged(YWTribe tribe, YWTribeMember user) {
                String loginUser = mIMKit.getIMCore().getLoginUserId();
                if (loginUser.equals(user.getUserId())) {
                    for (YWTribeMember member : mList) {
                        if (member.getUserId().equals(loginUser)) {
                            mList.remove(member);
                            mList.add(user);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateView();
                                }
                            });
                            break;
                        }
                    }
                }
            }
        };
    }


}
