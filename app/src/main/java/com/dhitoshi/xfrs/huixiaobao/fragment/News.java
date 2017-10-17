package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeType;
import com.dhitoshi.xfrs.huixiaobao.Event.ClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.NewsEvent;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.app.MyApplication;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.common.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.common.SegmentTabLayout;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.EditTribeInfo;
import com.dhitoshi.xfrs.huixiaobao.view.SearchTribe;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.dhitoshi.xfrs.huixiaobao.R.id.smartRefreshLayout;
//消息页面
public class News extends Fragment {
    @BindView(R.id.segement)
    SegmentTabLayout segement;
    @BindView(R.id.news_viewpager)
    NoSlidingViewPager newsViewpager;
    @BindView(R.id.group_search)
    TextView groupSearch;
    @BindView(R.id.group_add)
    AppCompatImageView groupAdd;
    private ViewPagerAdapter<Fragment> adapter;
    private YWIMKit mIMKit;
    Unbinder unbinder;
    private String[] mTitles = {"消息", "联系人", "群组"};
    private List<Fragment> fragments;
    private PopupWindow mPopupWindow;
    public News() {
    }
    public static News newInstance() {
        News fragment = new News();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        EventBus.getDefault().register(this);
        return view;
    }
    private void initViews() {
        segement.setTabData(mTitles);
        String userId = SharedPreferencesUtil.Obtain(getContext(), "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        getFragments();
        adapter = new ViewPagerAdapter<>(getChildFragmentManager(), fragments);
        newsViewpager.setAdapter(adapter);
        segement.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                groupSearch.setVisibility(position==2?View.VISIBLE:View.GONE);
                groupAdd.setVisibility(position==2?View.VISIBLE:View.GONE);
                newsViewpager.setCurrentItem(position, false);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
    }
    public void getFragments() {
        fragments = new ArrayList<>();
        fragments.add(mIMKit.getConversationFragment());
        fragments.add(ContactInfo.newInstance());
        fragments.add(TribeFragment.newInstance());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @OnClick({R.id.group_search, R.id.group_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_search:
                Intent intent = new Intent(getActivity(), SearchTribe.class);
                startActivity(intent);
                break;
            case R.id.group_add:
                showPopupMenu(view);
                break;
        }
    }
    //弹出要创建的群类型选项
    private void showPopupMenu(View v) {
        View view = View.inflate(getContext(), R.layout.demo_popup_menu, null);
        //创建群组
        TextView tribe = (TextView) view.findViewById(R.id.create_tribe);
        tribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopupWindow();
                Intent intent = new Intent(getActivity(), EditTribeInfo.class);
                intent.putExtra(TribeConstants.TRIBE_OP, TribeConstants.TRIBE_CREATE);
                intent.putExtra(TribeConstants.TRIBE_TYPE, YWTribeType.CHATTING_TRIBE.toString());
                startActivityForResult(intent, 0);
            }
        });
        //创建讨论组
        TextView room = (TextView) view.findViewById(R.id.create_room);
        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopupWindow();
                Intent intent = new Intent(getActivity(), EditTribeInfo.class);
                intent.putExtra(TribeConstants.TRIBE_OP, TribeConstants.TRIBE_CREATE);
                intent.putExtra(TribeConstants.TRIBE_TYPE, YWTribeType.CHATTING_GROUP.toString());
                startActivityForResult(intent, 0);
            }
        });
        TextView cancel = (TextView) view.findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hidePopupWindow();
            }
        });
        WindowManager.LayoutParams params=getActivity().getWindow().getAttributes();
        params.alpha=0.7f;
        getActivity().getWindow().setAttributes(params);
        if (mPopupWindow == null) {
            mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }
    private void hidePopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            WindowManager.LayoutParams params=getActivity().getWindow().getAttributes();
            params.alpha=1f;
            getActivity().getWindow().setAttributes(params);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NewsEvent event) {
        switch (event.getState()) {
            case 1:
                newsViewpager.setCurrentItem(2);
                break;
        }
    }
}
