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
import com.dhitoshi.xfrs.huixiaobao.fragment.HasHeld;
import com.dhitoshi.xfrs.huixiaobao.fragment.Staging;
import com.dhitoshi.xfrs.huixiaobao.fragment.ToHeld;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OwnMeeting extends AppCompatActivity {
    @BindView(R.id.meet_segement)
    SegmentTabLayout segement;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.meet_viewpager)
    NoSlidingViewPager meetViewpager;
    private List<Fragment> themeFragments;
    private ViewPagerAdapter adapter;
    private String[] mTitles = {"举办中", "待举办", "已举办"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_meet);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"OwnMeeting");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("OwnMeeting");
    }

    private void initViews() {
        segement.setTabData(mTitles);
        segement.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                meetViewpager.setCurrentItem(position,false);
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
        meetViewpager.setAdapter(adapter);
        meetViewpager.setOffscreenPageLimit(3);
    }
    private List<Fragment> getThemeFragments() {
        themeFragments = new ArrayList<>();
        themeFragments.add(Staging.newInstance());
        themeFragments.add(ToHeld.newInstance());
        themeFragments.add(HasHeld.newInstance());
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
