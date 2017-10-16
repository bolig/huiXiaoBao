package com.dhitoshi.xfrs.huixiaobao.view;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by weiquanyun on 15/11/2.
 */
public class EditMyTribeProfile extends BaseView {

    private long tribeId;
    private String userId;
    private String oldNick;
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private EditText mNickInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_edit_my_tribe_profile);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent != null) {
            tribeId = intent.getLongExtra(TribeConstants.TRIBE_ID, 0);
            oldNick = intent.getStringExtra(TribeConstants.TRIBE_NICK);
        }
        if (tribeId == 0) {
            //TODO 群ID没获取到的处理逻辑
        }
        init();
        initTitle();
        initViews();
    }

    private void init() {
        userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
    }

    private void initViews() {
        mNickInput = (EditText) findViewById(R.id.my_profile_input);
        mNickInput.setText(oldNick);
    }

    private void initTitle() {
        initBaseViews();
        setTitle("编辑群名片");
        setRightText("提交");
    }

    private void uploadModifiedUserNick(final String userNick) {
        mTribeService.modifyTribeUserNick(tribeId, mIMKit.getIMCore().getAppKey(), userId, userNick, new IWxCallback() {
            @Override
            public void onSuccess(Object... result) {
                IMNotificationUtils.getInstance().showToast("修改成功", EditMyTribeProfile.this);
                Intent intent = new Intent();
                intent.putExtra(TribeConstants.TRIBE_NICK, userNick);
                setResult(Activity.RESULT_OK, intent);
                EditMyTribeProfile.this.finish();
            }

            @Override
            public void onError(int code, String info) {
                IMNotificationUtils.getInstance().showToast("修改失败", EditMyTribeProfile.this);
            }

            @Override
            public void onProgress(int progress) {

            }
        });
    }

    @OnClick(R.id.right_text)
    public void onViewClicked() {
        uploadModifiedUserNick(mNickInput.getText().toString());
    }
}
