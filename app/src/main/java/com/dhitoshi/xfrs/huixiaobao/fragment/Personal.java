package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.CircleImageView;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.ApplyMeeting;
import com.dhitoshi.xfrs.huixiaobao.view.Setting;

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
    CircleImageView personalHead;
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
        initViews();
        return view;
    }

    private void initViews() {
        title.setText("我的");
        personalName.setText(SharedPreferencesUtil.Obtain(getContext(), "truename", "").toString());
        Glide.with(this).load(SharedPreferencesUtil.Obtain(getContext(),"head","").toString()).placeholder(R.mipmap.head).error(R.mipmap.head).into(personalHead);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.personal_setting, R.id.personal_share, R.id.personal_meeting, R.id.personal_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
}
