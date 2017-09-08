package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.R;
//赠品
public class Gift extends Fragment implements GiftManage.View{

    public Gift() {

    }
    public static Gift newInstance() {
        Gift fragment = new Gift();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gift, container, false);
    }

    @Override
    public void getGiftLists(PageBean<GiftBean> pageBean) {

    }
}
