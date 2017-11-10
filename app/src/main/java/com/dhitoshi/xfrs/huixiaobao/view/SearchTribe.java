package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
public class SearchTribe extends BaseView {
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private YWTribe mTribe;
    private long mTribeId;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_search_tribe);
        ActivityManagerUtil.addDestoryActivity(this,"SearchTribe");
        init();
    }
    private void init() {
        initTitle();
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        final EditText tribeIdEditText = (EditText) findViewById(R.id.input_tribe_id);
        final ImageView searchButton = (ImageView) findViewById(R.id.search_tribe);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tribeId = tribeIdEditText.getText().toString();
                try {
                    mTribeId = Long.valueOf(tribeId);
                } catch (Exception e) {
                    IMNotificationUtils.getInstance().showToast(SearchTribe.this, "请输入群id");
                }
                mTribe = mTribeService.getTribe(mTribeId);
                if (mTribe == null || mTribe.getTribeRole() == null) {
                    searchButton.setClickable(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mTribeService.getTribeFromServer(new IWxCallback() {
                        @Override
                        public void onSuccess(Object... result) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    searchButton.setClickable(true);
                                    mProgressBar.setVisibility(View.GONE);
                                    startTribeInfoActivity(TribeConstants.TRIBE_JOIN);
                                }
                            });
                        }

                        @Override
                        public void onError(int code, String info) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    searchButton.setClickable(true);
                                    mProgressBar.setVisibility(View.GONE);
                                    IMNotificationUtils.getInstance().showToast(SearchTribe.this, "没有搜索到该群，请确认群id是否正确！");
                                }
                            });
                        }

                        @Override
                        public void onProgress(int progress) {

                        }
                    }, mTribeId);
                } else {
                    startTribeInfoActivity(null);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("SearchTribe");
    }
    private void startTribeInfoActivity(String tribeOp) {
        Intent intent = new Intent(this, TribeInfo.class);
        intent.putExtra(TribeConstants.TRIBE_ID, mTribeId);
        if (!TextUtils.isEmpty(tribeOp)) {
            intent.putExtra(TribeConstants.TRIBE_OP, tribeOp);
        }
        startActivity(intent);
        finish();
    }
    private void initTitle() {
        initBaseViews();
        setTitle("搜索加入群");
    }
}
