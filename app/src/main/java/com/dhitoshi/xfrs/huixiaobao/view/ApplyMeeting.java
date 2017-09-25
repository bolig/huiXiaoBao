package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.common.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.common.SegmentTabLayout;
import com.dhitoshi.xfrs.huixiaobao.fragment.Expired;
import com.dhitoshi.xfrs.huixiaobao.fragment.OnGoing;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManager;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyMeeting extends AppCompatActivity {

    @BindView(R.id.apply_segement)
    SegmentTabLayout applySegement;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.apply_viewpager)
    NoSlidingViewPager applyViewpager;
    private List<Fragment> themeFragments;
    private ViewPagerAdapter adapter;
    private String[] mTitles = {"进行中", "已过期"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_meeting);
        ActivityManager.addDestoryActivity(this,"ApplyMeeting");
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.destoryActivity("ApplyMeeting");
    }

    private void initViews() {
        applySegement.setTabData(mTitles);
        applySegement.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                applyViewpager.setCurrentItem(position,false);
            }
            @Override
            public void onTabReselect(int position) {

            }
        });
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setStatusTint(R.color.colorPrimary);
        getThemeFragments();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), themeFragments);
        applyViewpager.setAdapter(adapter);
        applyViewpager.setOffscreenPageLimit(2);
    }
    private List<Fragment> getThemeFragments() {
        themeFragments = new ArrayList<>();
        themeFragments.add(OnGoing.newInstance());
        themeFragments.add(Expired.newInstance());
        return themeFragments;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
    public void setStatusTint(int resId) {
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(resId);
    }
}
