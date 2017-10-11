package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.common.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.common.SegmentTabLayout;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.Chat;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
        fragments.add(GroupsInfo.newInstance());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.group_search, R.id.group_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.group_search:
                break;
            case R.id.group_add:
                break;
        }
    }
}
