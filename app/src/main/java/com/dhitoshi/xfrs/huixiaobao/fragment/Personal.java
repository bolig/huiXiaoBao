package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Event.PersonalEvent;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.GlideCircleTransform;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.ApplyMeeting;
import com.dhitoshi.xfrs.huixiaobao.view.Setting;
import com.dhitoshi.xfrs.huixiaobao.view.UserInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//我的页面
public class Personal extends Fragment {
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.personal_head)
    ImageView personalHead;
    @BindView(R.id.personal_account)
    TextView personalAccount;
    private Intent it;

    public Personal() {
    }

    public static Personal newInstance() {
        Personal fragment = new Personal();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initViews();
        return view;
    }

    private void initViews() {
        title.setText("我的");
        personalName.setText(SharedPreferencesUtil.Obtain(getContext(), "truename", "").toString());
        personalAccount.setText(SharedPreferencesUtil.Obtain(getContext(),"account","").toString().split("@")[0]);
        Glide.with(this).load(SharedPreferencesUtil.Obtain(getContext(), "head", "").toString())
                .placeholder(R.mipmap.head).error(R.mipmap.head).transform(new GlideCircleTransform(getContext())).into(personalHead);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.personal_setting, R.id.personal_share, R.id.personal_meeting, R.id.personal_help, R.id.personal_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_info:
                it = new Intent(getContext(), UserInfo.class);
                startActivity(it);
                break;
            case R.id.personal_setting:
                it = new Intent(getContext(), Setting.class);
                startActivity(it);
                break;
            case R.id.personal_share:
                break;
            case R.id.personal_meeting:
                it = new Intent(getContext(), ApplyMeeting.class);
                startActivity(it);
                break;
            case R.id.personal_help:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PersonalEvent event) {
        switch (event.getState()) {
            case 1:
                Glide.with(this).load(event.getResource())
                        .placeholder(R.mipmap.head).error(R.mipmap.head).transform(new GlideCircleTransform(getContext())).into(personalHead);
                break;
            case 2:
                personalName.setText(event.getResource());
                break;
        }
    }
}
