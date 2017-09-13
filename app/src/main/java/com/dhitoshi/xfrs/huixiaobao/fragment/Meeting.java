package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingManage;
import com.dhitoshi.xfrs.huixiaobao.R;

import io.reactivex.disposables.Disposable;

//会议
public class Meeting extends BaseFragment implements MeetingManage.View{
    private static final String ARG_ID = "id";
    private int id;
    public Meeting() {
    }

    @Override
    public void loadData() {

    }

    public static Meeting newInstance(int id) {
        Meeting fragment = new Meeting();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meeting, container, false);
    }
    @Override
    public void getMeetingLists(PageBean<MeetBean> pageBean) {

    }
}
