package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GroupsInfo extends Fragment {

    Unbinder unbinder;

    public GroupsInfo() {

    }

    public static GroupsInfo newInstance() {
        GroupsInfo fragment = new GroupsInfo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.groups_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.group, R.id.friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.friend:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
