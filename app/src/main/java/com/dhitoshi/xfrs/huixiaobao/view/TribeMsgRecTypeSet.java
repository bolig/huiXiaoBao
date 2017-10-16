package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.alibaba.mobileim.channel.constant.YWProfileSettingsConstants;
import com.dhitoshi.xfrs.huixiaobao.R;


public class TribeMsgRecTypeSet extends BaseView implements View.OnClickListener{

    private int flag;
    private ImageView mTribeMsgRecCheck;
    private ImageView mTribeMsgRecNotRemindCheck;
    private ImageView mTribeMsgRejCheck;
    private RelativeLayout mTribeMsgRecLayout;
    private RelativeLayout mTribeMsgRecNotRemindLayout;
    private RelativeLayout mTribeMsgRejLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tribe_msg_rec_type_set);
        initTitle();
        Intent intent = getIntent();
        flag = intent.getIntExtra("Flag", YWProfileSettingsConstants.TRIBE_MSG_REJ);
        initViews();
        initMsgRecFlag(flag);
    }

    private void initTitle() {
        initBaseViews();
        setTitle("聊天设置");
    }

    private void initViews() {
        mTribeMsgRecLayout = (RelativeLayout) findViewById(R.id.receive_and_remind_layout);
        mTribeMsgRecNotRemindLayout = (RelativeLayout) findViewById(R.id.only_receive_layout);
        mTribeMsgRejLayout = (RelativeLayout) findViewById(R.id.not_receive_layout);

        mTribeMsgRecCheck = (ImageView) findViewById(R.id.receive_and_remind);
        mTribeMsgRecNotRemindCheck = (ImageView) findViewById(R.id.only_receive);
        mTribeMsgRejCheck = (ImageView) findViewById(R.id.not_receive);

        mTribeMsgRecLayout.setOnClickListener(this);
        mTribeMsgRecNotRemindLayout.setOnClickListener(this);
        mTribeMsgRejLayout.setOnClickListener(this);
    }

    public static Intent getTribeMsgRecTypeSetActivityIntent(Context context, int flag) {
        Intent intent = new Intent(context, TribeMsgRecTypeSet.class);
        intent.putExtra("Flag", flag);
        return intent;
    }

    private void initMsgRecFlag(int flag) {
        switch (flag) {
            case YWProfileSettingsConstants.TRIBE_MSG_REC:
                mTribeMsgRecCheck.setVisibility(View.VISIBLE);
                mTribeMsgRecNotRemindCheck.setVisibility(View.GONE);
                mTribeMsgRejCheck.setVisibility(View.GONE);
                break;
            case YWProfileSettingsConstants.TRIBE_MSG_REC_NOT_REMIND:
                mTribeMsgRecCheck.setVisibility(View.GONE);
                mTribeMsgRecNotRemindCheck.setVisibility(View.VISIBLE);
                mTribeMsgRejCheck.setVisibility(View.GONE);
                break;
            case YWProfileSettingsConstants.TRIBE_MSG_REJ:
                mTribeMsgRecCheck.setVisibility(View.GONE);
                mTribeMsgRecNotRemindCheck.setVisibility(View.GONE);
                mTribeMsgRejCheck.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.receive_and_remind_layout:
                flag = YWProfileSettingsConstants.TRIBE_MSG_REC;
                Intent intent1 = new Intent();
                intent1.putExtra("Flag", flag);
                setResult(RESULT_OK, intent1);
                initMsgRecFlag(flag);
                finish();
                break;
            case R.id.only_receive_layout:
                flag = YWProfileSettingsConstants.TRIBE_MSG_REC_NOT_REMIND;
                Intent intent2 = new Intent();
                intent2.putExtra("Flag", flag);
                setResult(RESULT_OK, intent2);
                initMsgRecFlag(flag);
                finish();
                break;
            case R.id.not_receive_layout:
                flag = YWProfileSettingsConstants.TRIBE_MSG_REJ;
                Intent intent3 = new Intent();
                intent3.putExtra("Flag", flag);
                setResult(RESULT_OK, intent3);
                initMsgRecFlag(flag);
                finish();
                break;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                setResult(flag);
                finish();
                break;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        setResult(flag);
        super.onBackPressed();
    }
}
