package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Dialog.HeadPopup;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class UserInfo extends BaseView {
    @BindView(R.id.user_nickname)
    EditText userNickname;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_email)
    EditText userEmail;
    @BindView(R.id.user_head)
    CircleImageView userHead;
    private HeadPopup headPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("我的个人信息");
        setRightText("提交");
        userNickname.setText(SharedPreferencesUtil.Obtain(this, "name", "").toString());
        userName.setText(SharedPreferencesUtil.Obtain(this, "truename", "").toString());
        userPhone.setText(SharedPreferencesUtil.Obtain(this, "phone", "").toString());
        userEmail.setText(SharedPreferencesUtil.Obtain(this, "email", "").toString());
        Glide.with(this).load(SharedPreferencesUtil.Obtain(this, "head", "").toString()).placeholder(R.mipmap.head).error(R.mipmap.head).into(userHead);
    }
    @OnClick({R.id.right_text, R.id.update_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                break;
            case R.id.update_head:
                selectHead();
                break;
        }
    }
    private void selectHead() {
        if (null == headPopup) {
            headPopup = HeadPopup.Build(this,userHead).init();
            headPopup.show();
        } else {
            if (!headPopup.isShowing()) {
                headPopup.show();
            } else {
                headPopup.dismisss();
            }
        }
    }
}
