package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.WxThreadHandler;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.fundamental.widget.WxAlertDialog;
import com.alibaba.mobileim.fundamental.widget.YWAlertDialog;
import com.alibaba.mobileim.fundamental.widget.refreshlist.PullToRefreshBase;
import com.alibaba.mobileim.fundamental.widget.refreshlist.PullToRefreshListView;
import com.alibaba.mobileim.fundamental.widget.refreshlist.YWPullToRefreshBase;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeMember;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeType;
import com.alibaba.mobileim.gingko.presenter.contact.IContactProfileUpdateListener;
import com.alibaba.mobileim.gingko.presenter.contact.YWContactManagerImpl;
import com.alibaba.mobileim.gingko.presenter.tribe.IYWTribeChangeListener;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.utility.IMConstants;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.xfrs.huixiaobao.Event.NewsEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeCompanyManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.TribeMembersAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class TribeMembers extends BaseView implements AdapterView.OnItemLongClickListener {
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private IYWTribeChangeListener mTribeChangedListener;
    private long mTribeId;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView mListView;
    private List<YWTribeMember> mList = new ArrayList<>();
    private YWTribeMember myself;
    private TribeMembersAdapter mAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private int tribeType=-1;
    /**
     * 用于筛选需要处理的ProfileUpdate通知
     */
    private Set<String> mContactUserIdSet = new HashSet<String>();
    private IContactProfileUpdateListener mContactProfileUpdateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_tribe_members);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        initTitle();
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.tribe_members_list);
        mPullToRefreshListView.setMode(YWPullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        mPullToRefreshListView.setShowIndicator(false);
        mPullToRefreshListView.setDisableScrollingWhileRefreshing(false);
        mPullToRefreshListView.setRefreshingLabel("同步群成员列表");
        mPullToRefreshListView.setReleaseLabel("松开同步群成员列表");
        mPullToRefreshListView.setDisableRefresh(false);
        mPullToRefreshListView.setOnRefreshListener(new YWPullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTribeMembers();
            }
        });
        mListView = mPullToRefreshListView.getRefreshableView();
        mListView.setOnItemLongClickListener(this);
        mList = new ArrayList<>();
        mAdapter = new TribeMembersAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        Intent intent = getIntent();
        mTribeId = intent.getLongExtra(TribeConstants.TRIBE_ID, 0);
        tribeType = intent.getIntExtra("tribeType", -1);
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        getTribeMembers();
        initTribeChangedListener();
        initContactProfileUpdateListener();
        addListeners();
    }

    private void initTitle() {
        initBaseViews();
        setTitle("群成员列表");
        setRightText("添加");
    }

    private void initContactProfileUpdateListener() {
        mContactProfileUpdateListener = new IContactProfileUpdateListener() {
            @Override
            public void onProfileUpdate(final String userid, String appkey) {
                if (mContactUserIdSet.contains(userid) && mAdapter != null) {
                    WxThreadHandler.getInstance().getHandler().removeCallbacks(reindexRunnable);
                    WxThreadHandler.getInstance().getHandler().postDelayed(reindexRunnable, IMConstants.UPDATE_SPELL_AND_INDEX_DELAY_MILLIS);
                }
            }

            @Override
            public void onProfileUpdate() {
                //just empty
            }
        };

    }

    private void doPreloadContactProfiles(final List<YWTribeMember> members) {
        int length = Math.min(members.size(), IMConstants.PRELOAD_PROFILE_NUM);
        ArrayList<IYWContact> contacts = new ArrayList<>();


        for (int i = 0; i < length; i++) {
            YWTribeMember ywTribeMember = members.get(i);
            contacts.add(ywTribeMember);
        }
        if (mIMKit != null && mIMKit.getContactService() != null)
            mIMKit.getContactService().getCrossContactProfileInfos(contacts);
    }

    private Runnable reindexRunnable = new Runnable() {
        @Override
        public void run() {
            refreshAdapter();
        }
    };

    public void addListeners() {
        if (mIMKit != null && mIMKit.getContactService() != null)
            ((YWContactManagerImpl) mIMKit.getContactService()).addProfileUpdateListener(mContactProfileUpdateListener);
        mTribeService.addTribeListener(mTribeChangedListener);

    }

    public void removeListeners() {
        if (mIMKit != null && mIMKit.getContactService() != null)
            ((YWContactManagerImpl) mIMKit.getContactService()).removeProfileUpdateListener(mContactProfileUpdateListener);
        mTribeService.removeTribeListener(mTribeChangedListener);

    }

    private void getTribeMembers() {
        mTribeService.getMembers(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                onSuccessGetMembers((List<YWTribeMember>) result[0]);
                doPreloadContactProfiles((List<YWTribeMember>) result[0]);
                //同时触发一下向服务器更新列表
                //TODO 其实每次深登录后做一次查询就可以了
                getMembersFromServer();
            }

            @Override
            public void onError(int code, String info) {
                if (isFinishing()) {
                    return;
                }
                IMNotificationUtils.getInstance().showToast(TribeMembers.this, "error, code = " + code + ", info = " + info);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshListView.onRefreshComplete(false, false);
                    }
                });
            }

            @Override
            public void onProgress(int progress) {

            }
        }, mTribeId);
    }

    private void getMembersFromServer() {
        mTribeService.getMembersFromServer(new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                onSuccessGetMembers((List<YWTribeMember>) result[0]);
            }

            @Override
            public void onError(int code, String info) {

            }

            @Override
            public void onProgress(int progress) {

            }
        }, mTribeId);
    }

    private void onSuccessGetMembers(final List<YWTribeMember> members) {
        if (members == null || isFinishing()) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                mList.addAll(members);
                mContactUserIdSet.clear();
                for (YWTribeMember member : members) {
                    mContactUserIdSet.add(member.getUserId());
                }
                refreshAdapter();
                mPullToRefreshListView.onRefreshComplete(false, true);
            }
        });
    }
    //刷新当前列表
    private void refreshAdapter() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null) {
                    mAdapter.refreshData(mList);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeListeners();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final int pos = position - mListView.getHeaderViewsCount();
        final YWTribeMember member = mList.get(pos);
        YWTribe tribe = mTribeService.getTribe(mTribeId);
        final String[] items = getItems(tribe, member);
        if (items == null) {
            return true;
        }
        new YWAlertDialog.Builder(this)
                .setTitle("群成员管理")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals(TribeConstants.TRIBE_SET_MANAGER)) {
                            mTribeService.setTribeManager(mTribeId, member, null);
                        } else if (items[which].equals(TribeConstants.TRIBE_CANCEL_MANAGER)) {
                            mTribeService.cancelTribeManager(mTribeId, member, null);
                        } else if (items[which].equals(TribeConstants.TRIBE_EXPEL_MEMBER)) {
                            mTribeService.expelMember(mTribeId, member, new IWxCallback() {
                                @Override
                                public void onSuccess(Object... result) {
                                    IMNotificationUtils.getInstance().showToast(TribeMembers.this, "踢人成功！");
                                    mList.remove(member);
                                    refreshAdapter();
                                }

                                @Override
                                public void onError(int code, String info) {

                                }

                                @Override
                                public void onProgress(int progress) {

                                }
                            });
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
        return true;
    }
    //判断当前登录用户在群组中的身份
    private int getLoginUserRole() {
        int role = YWTribeMember.ROLE_NORMAL;
        String loginUser = mIMKit.getIMCore().getLoginUserId();
        for (YWTribeMember member : mList) {
            if (member.getUserId().equals(loginUser)) {
                myself = member;
                role = member.getTribeRole();
            }
        }
        return role;
    }

    private String[] getItems(YWTribe tribe, YWTribeMember member) {
        String[] items = null;
        if (tribe.getTribeType() == YWTribeType.CHATTING_TRIBE && getLoginUserRole() == YWTribeMember.ROLE_HOST) {
            if (member.getTribeRole() == YWTribeMember.ROLE_NORMAL) {
                items = new String[]{TribeConstants.TRIBE_SET_MANAGER, TribeConstants.TRIBE_EXPEL_MEMBER};
            } else if (member.getTribeRole() == YWTribeMember.ROLE_MANAGER) {
                items = new String[]{TribeConstants.TRIBE_CANCEL_MANAGER, TribeConstants.TRIBE_EXPEL_MEMBER};
            }
        } else if (tribe.getTribeType() == YWTribeType.CHATTING_GROUP && getLoginUserRole() == YWTribeMember.ROLE_HOST && member.getTribeRole() != YWTribeMember.ROLE_HOST) {
            items = new String[]{TribeConstants.TRIBE_EXPEL_MEMBER};
        }
        return items;
    }
    private void initTribeChangedListener() {
        mTribeChangedListener = new IYWTribeChangeListener() {
            @Override
            public void onInvite(YWTribe tribe, YWTribeMember user) {

            }

            @Override
            public void onUserJoin(YWTribe tribe, YWTribeMember user) {
                mList.add(user);
                refreshAdapter();
            }

            @Override
            public void onUserQuit(YWTribe tribe, YWTribeMember user) {
                if (user.equals(myself)) {
                    mTribeService.clearTribeSystemMessages(tribe.getTribeId());
                }
                mList.remove(user);
                refreshAdapter();
            }

            @Override
            public void onUserRemoved(YWTribe tribe, YWTribeMember user) {
                //只有被踢出群的用户会收到该回调，即如果收到该回调表示自己被踢出群了
                mTribeService.clearTribeSystemMessages(tribe.getTribeId());
                //openTribeListFragment();
            }

            @Override
            public void onTribeDestroyed(YWTribe tribe) {
                mTribeService.clearTribeSystemMessages(tribe.getTribeId());
                openTribeListFragment();
            }

            @Override
            public void onTribeInfoUpdated(YWTribe tribe) {

            }

            @Override
            public void onTribeManagerChanged(YWTribe tribe, YWTribeMember user) {
                for (YWTribeMember member : mList) {
                    if (member.getUserId().equals(user.getUserId()) && member.getAppKey().equals(user.getAppKey())) {
                        mList.remove(member);
                        mList.add(user);
                        refreshAdapter();
                        break;
                    }
                }
            }

            @Override
            public void onTribeRoleChanged(YWTribe tribe, YWTribeMember user) {
                for (YWTribeMember member : mList) {
                    if (member.getUserId().equals(user.getUserId()) && member.getAppKey().equals(user.getAppKey())) {
                        mList.remove(member);
                        mList.add(user);
                        refreshAdapter();
                        break;
                    }
                }
            }
        };
    }
    private void openTribeListFragment() {
        EventBus.getDefault().post(new NewsEvent(1));
        ActivityManagerUtil.destoryActivity("SearchTribe");
        finish();
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        //群的普通成员没有加入权限，所以因此加入view
        if (getLoginUserRole() == YWTribeMember.ROLE_NORMAL) {
            IMNotificationUtils.getInstance().showToast(TribeMembers.this, "您不是群主，没有管理权限~");
        } else {
            Class c=tribeType==0?InviteTribeMember.class:TribeCompany.class;
            Intent intent = new Intent(TribeMembers.this, c);
            intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
            startActivity(intent);
        }
    }
}
