package com.dhitoshi.xfrs.huixiaobao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//报表页面
public class StateMent extends Fragment {

    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;

    public StateMent() {

    }

    public static StateMent newInstance() {
        StateMent fragment = new StateMent();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_ment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        title.setText("报表");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
