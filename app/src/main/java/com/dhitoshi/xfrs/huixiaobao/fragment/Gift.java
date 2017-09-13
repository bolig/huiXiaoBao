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
import com.dhitoshi.xfrs.huixiaobao.presenter.GiftPresenter;
import com.dhitoshi.xfrs.huixiaobao.presenter.SpendPresenter;

import io.reactivex.disposables.Disposable;

//赠品
public class Gift extends BaseFragment implements GiftManage.View{
    private static final String ARG_ID = "id";
    private int id;
    public Gift() {

    }

    @Override
    public void loadData() {
        GiftPresenter giftPresenter=new GiftPresenter(this);
        giftPresenter.getGiftLists(String.valueOf(id),"1");
    }

    public static Gift newInstance(int id) {
        Gift fragment = new Gift();
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
        return inflater.inflate(R.layout.fragment_gift, container, false);
    }



    @Override
    public void getGiftLists(PageBean<GiftBean> pageBean) {

    }
}
