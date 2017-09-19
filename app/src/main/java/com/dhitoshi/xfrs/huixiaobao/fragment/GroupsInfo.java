package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dhitoshi.xfrs.huixiaobao.R;
public class GroupsInfo extends Fragment {

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
        return inflater.inflate(R.layout.groups_info, container, false);
    }

}
