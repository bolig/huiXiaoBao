package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.fundamental.widget.refreshlist.YWPullToRefreshBase;
import com.alibaba.mobileim.fundamental.widget.refreshlist.YWPullToRefreshListView;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeType;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.TribeAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.TribeAndRoomList;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.common.TribeSampleHelper;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.TribeChat;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TribeFragment extends Fragment implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    protected static final long POST_DELAYED_TIME = 300;
    private static final String TAG = "TribeFragment";
    private final Handler handler = new Handler();
    public IYWTribeService getTribeService() {
        String userId = SharedPreferencesUtil.Obtain(getContext(), "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        return mTribeService;
    }
    private IYWTribeService mTribeService;
    private ListView mMessageListView; // 消息列表视图
    private TribeAdapter adapter;
    private int max_visible_item_count = 0; // 当前页面列表最多显示个数
    private TribeAndRoomList mTribeAndRoomList;
    private List<YWTribe> mList;
    private List<YWTribe> mTribeList;
    private List<YWTribe> mRoomsList;
    private View mView;
    private YWIMKit mIMKit;
    private Activity mContext;
    private String mUserId;
    private View mProgress;
    private YWPullToRefreshListView mPullToRefreshListView;
    private Runnable cancelRefresh = new Runnable() {
        @Override
        public void run() {
            if (mPullToRefreshListView != null) {
                mPullToRefreshListView.onRefreshComplete(false, false,
                        R.string.aliwx_sync_failed);
            }
        }
    };
    public TribeFragment() {

    }
    public static TribeFragment newInstance() {
        TribeFragment fragment = new TribeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
            return mView;
        }
        mContext = getActivity();
        mView = inflater.inflate(R.layout.demo_fragment_tribe, container, false);
        mProgress = mView.findViewById(R.id.progress);
        String userId = SharedPreferencesUtil.Obtain(getContext(), "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mUserId = mIMKit.getIMCore().getLoginUserId();
        if (TextUtils.isEmpty(mUserId)) {
            YWLog.i(TAG, "user not login");
        }
        mTribeService = mIMKit.getTribeService();
        init();
        return mView;
    }
    @Override
    public void onResume() {
        super.onResume();
        initTribeListView();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (!TextUtils.isEmpty(intent.getStringExtra(TribeConstants.TRIBE_OP))) {
                mList = mTribeService.getAllTribes();
                updateTribeList();
            }
        }
    }
    protected void init() {
        mContext.getWindow().setWindowAnimations(0);
        mList = new ArrayList<>();
        mTribeList = new ArrayList<>();
        mRoomsList = new ArrayList<>();
        mTribeAndRoomList = new TribeAndRoomList(mTribeList, mRoomsList);
        adapter = new TribeAdapter(mContext, mTribeAndRoomList);
        mPullToRefreshListView = (YWPullToRefreshListView) mView.findViewById(R.id.message_list);
        mMessageListView = mPullToRefreshListView.getRefreshableView();
        mPullToRefreshListView.setMode(YWPullToRefreshBase.Mode.PULL_DOWN_TO_REFRESH);
        mPullToRefreshListView.setShowIndicator(false);
        mPullToRefreshListView.setDisableScrollingWhileRefreshing(false);
        mPullToRefreshListView.setRefreshingLabel("同步群组");
        mPullToRefreshListView.setReleaseLabel("松开同步群组");
        mPullToRefreshListView.setDisableRefresh(false);
        mPullToRefreshListView.setOnRefreshListener(new YWPullToRefreshBase.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.removeCallbacks(cancelRefresh);
                        IYWTribeService tribeService = TribeSampleHelper.getTribeService(getContext());
                        if (tribeService != null) {
                            tribeService.getAllTribesFromServer(new IWxCallback() {
                                @Override
                                public void onSuccess(Object... arg0) {
                                    // 返回值为列表
                                    mList.clear();
                                    mList.addAll((ArrayList<YWTribe>) arg0[0]);
                                    if (mList.size() > 0) {
                                        mTribeList.clear();
                                        mRoomsList.clear();
                                        for (YWTribe tribe : mList) {
                                            if (tribe.getTribeType() == YWTribeType.CHATTING_TRIBE) {
                                                mTribeList.add(tribe);
                                            } else {
                                                mRoomsList.add(tribe);
                                            }
                                        }
                                    }
                                    mPullToRefreshListView.onRefreshComplete(false, true,
                                            R.string.aliwx_sync_success);
                                    refreshAdapter();
                                }
                                @Override
                                public void onError(int code, String info) {
                                    mPullToRefreshListView.onRefreshComplete(false, false,
                                            R.string.aliwx_sync_failed);
                                }
                                @Override
                                public void onProgress(int progress) {

                                }
                            });
                        }
                    }
                }, POST_DELAYED_TIME);
            }
        });
    }

    /**
     * 初始化群列表行为
     */
    private void initTribeListView() {
        if (mMessageListView != null) {
            mMessageListView.setAdapter(adapter);
            mMessageListView.setOnItemClickListener(this);
            mMessageListView.setOnScrollListener(this);
        }

        getAllTribesFromServer();
    }

    private void getAllTribesFromServer() {
        getTribeService().getAllTribesFromServer(new IWxCallback() {
            @Override
            public void onSuccess(Object... arg0) {
                // 返回值为列表
                mList.clear();
                mList.addAll((ArrayList<YWTribe>) arg0[0]);
                updateTribeList();
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onError(int code, String info) {
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }

    private void updateTribeList() {
        mTribeList.clear();
        mRoomsList.clear();
        if (mList.size() > 0) {
            for (YWTribe tribe : mList) {
                if (tribe.getTribeType() == YWTribeType.CHATTING_TRIBE) {
                    mTribeList.add(tribe);
                } else {
                    mRoomsList.add(tribe);
                }
            }
        }
        refreshAdapter();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position1,
                            long id) {
        final int position = position1 - mMessageListView.getHeaderViewsCount();
        if (position >= 0 && position < mTribeAndRoomList.size()) {
            YWTribe tribe = (YWTribe) mTribeAndRoomList.getItem(position);
            Intent intent = new Intent(getContext(), TribeChat.class);
            intent.putExtra("target",tribe.getTribeId());
            intent.putExtra("name",tribe.getTribeName());
            startActivity(intent);
        }
    }

    /**
     * 刷新当前列表
     */
    private void refreshAdapter() {
        if (adapter != null) {
            adapter.notifyDataSetChangedWithAsyncLoad();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE && adapter != null) {
            adapter.loadAsyncTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        max_visible_item_count = visibleItemCount > max_visible_item_count ? visibleItemCount
                : max_visible_item_count;
        if (adapter != null) {
            adapter.setMax_visible_item_count(max_visible_item_count);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mList = mTribeService.getAllTribes();
        updateTribeList();
    }
}
