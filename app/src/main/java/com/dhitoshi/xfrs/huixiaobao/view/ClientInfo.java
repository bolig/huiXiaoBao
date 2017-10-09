package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.dhitoshi.bottombar.BottomBar;
import com.dhitoshi.bottombar.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;
import com.dhitoshi.xfrs.huixiaobao.fragment.Gift;
import com.dhitoshi.xfrs.huixiaobao.fragment.Info;
import com.dhitoshi.xfrs.huixiaobao.fragment.Meeting;
import com.dhitoshi.xfrs.huixiaobao.fragment.Relation;
import com.dhitoshi.xfrs.huixiaobao.fragment.Spending;
import com.dhitoshi.xfrs.huixiaobao.fragment.Visit;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientInfo extends BaseView {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    AppCompatImageView rightIcon;
    @BindView(R.id.client_viewpager)
    NoSlidingViewPager clientViewpager;
    @BindView(R.id.client_bottomBar)
    BottomBar clientBottomBar;
    private List<Fragment> themeFragments;
    private ViewPagerAdapter adapter;
    private Drawable drawable;
    private Menu menu;
    private List<Menu> menus;
    private PopupMenu popupMenu;
    private Intent it;
    private int current;
    private ClientBean clientBean;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_info);
        ButterKnife.bind(this);
        InitViews();
        initEvents();
        ActivityManagerUtil.addDestoryActivity(this,"ClientInfo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("ClientInfo");
    }

    private void InitViews() {
        initBaseViews();
        clientBean=getIntent().getParcelableExtra("info");
        id=clientBean.getId();
        getThemeFragments();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), themeFragments);
        clientViewpager.setAdapter(adapter);
        clientViewpager.setOffscreenPageLimit(6);
        setTitle(clientBean.getName());
        setRightIcon(R.mipmap.update);
    }

    //初始化页面事件
    private void initEvents() {
        clientBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId, int position) {
                clientViewpager.setCurrentItem(position, false);
                setRightIcon(position == 0 ? R.mipmap.update : R.mipmap.add);
                current = position;
                setTitle(position==0?clientBean.getName():clientBean.getName()+"的"+
                        clientBottomBar.getTabAtPosition(position).getTitle());
            }
        });
    }

    //弹出菜单
    private void popupMenu() {
        if (null == popupMenu) {
            initMenuData();
            popupMenu = PopupMenu.Build(menus, this, rightIcon).init();
            initMenuClick(popupMenu);
            popupMenu.show();
        } else {
            if (!popupMenu.isShowing()) {
                popupMenu.show();
            } else {
                popupMenu.dismisss();
            }
        }
    }
    //菜单点击事件
    private void initMenuClick(PopupMenu popupMenu) {
        popupMenu.addMenuItemClickListener(new MenuItemClick<Menu>() {
            @Override
            public void onMenuItemClick(Menu menu, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
    }
    //初始化菜单数据
    private void initMenuData() {
        menus = new ArrayList<>();
        drawable = getResources().getDrawable(R.mipmap.update);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("资料修改", drawable);
        menus.add(menu);
        drawable = getResources().getDrawable(R.mipmap.consign);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("寄存取货", drawable);
        menus.add(menu);
    }

    private List<Fragment> getThemeFragments() {
        themeFragments = new ArrayList<>();
        themeFragments.add(Info.newInstance(clientBean));
        themeFragments.add(Spending.newInstance(id));
        themeFragments.add(Visit.newInstance(id));
        themeFragments.add(Relation.newInstance(id));
        themeFragments.add(Gift.newInstance(id));
        themeFragments.add(Meeting.newInstance(id));
        return themeFragments;
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        switch (current){
            case 0:
                //popupMenu();
                it=new Intent(this,AddClient.class);
                it.putExtra("info",clientBean);
                startActivity(it);
                break;
            case 1:
                it=new Intent(this,AddSpend.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
            case 2:
                it=new Intent(this,AddVisit.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
            case 3:
                it=new Intent(this,AddRelation.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
            case 4:
                it=new Intent(this,AddGift.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
            case 5:
                it=new Intent(this,AddMeeting.class);
                it.putExtra("id",id);
                startActivity(it);
                break;
        }
    }
}
