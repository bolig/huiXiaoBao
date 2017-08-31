package com.dhitoshi.xfrs.huixiaobao.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhitoshi.xfrs.huixiaobao.R;

//报表页面
public class StateMent extends Fragment {

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

        return inflater.inflate(R.layout.fragment_state_ment, container, false);
    }

}
