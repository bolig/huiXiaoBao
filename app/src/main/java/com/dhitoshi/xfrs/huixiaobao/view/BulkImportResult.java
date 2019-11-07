package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import com.dhitoshi.xfrs.huixiaobao.Bean.EnterResultBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.common.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.common.SegmentTabLayout;
import com.dhitoshi.xfrs.huixiaobao.fragment.HasImport;
import com.dhitoshi.xfrs.huixiaobao.fragment.NoImport;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class BulkImportResult extends AppCompatActivity {
    @BindView(R.id.bulk_segement)
    SegmentTabLayout bulkSegement;
    @BindView(R.id.bulk_viewpager)
    NoSlidingViewPager bulkViewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ViewPagerAdapter adapter;
    private String[] mTitles = {"未录入", "已录入"};
    private List<Fragment> themeFragments;
    private ArrayList<EnterResultBean> fail;
    private ArrayList<EnterResultBean> success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulk_import_result);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"BulkImportResult");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("BulkImportResult");
    }

    private void initViews() {
        bulkSegement.setTabData(mTitles);
        bulkSegement.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                bulkViewpager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        fail=getIntent().getParcelableArrayListExtra("fail");
        success=getIntent().getParcelableArrayListExtra("success");
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
        bulkViewpager.setAdapter(adapter);
        bulkViewpager.setOffscreenPageLimit(2);
    }

    private List<Fragment> getThemeFragments() {
        themeFragments = new ArrayList<>();
        themeFragments.add(NoImport.newInstance(fail));
        themeFragments.add(HasImport.newInstance(success));
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
