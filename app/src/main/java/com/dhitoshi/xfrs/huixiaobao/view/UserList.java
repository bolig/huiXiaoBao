package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Event.UserEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.UserManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.UserAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;
import com.dhitoshi.xfrs.huixiaobao.presenter.UserPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserList extends BaseView implements UserManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.right_icon)
    AppCompatImageView rightIcon;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private Drawable drawable;
    private UserPresenter userPresenter;
    private int page = 1;
    private Menu menu;
    private List<Menu> menus;
    private PopupMenu popupMenu;
    private UserAdapter adapter;
    private List<UserBean> userBeens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        initBaseViews();
        setTitle("用户");
        setRightIcon(R.mipmap.add);
        userBeens = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.autoRefresh();
        userPresenter = new UserPresenter(this, this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                userBeens.removeAll(userBeens);
                page = 1;
                userPresenter.getUserList(SharedPreferencesUtil.Obtain(UserList.this,"token","").toString(),String.valueOf(page), smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                userPresenter.getUserList(SharedPreferencesUtil.Obtain(UserList.this,"token","").toString(),String.valueOf(page), smartRefreshLayout);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        popupMenu();
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
                        startActivity(new Intent(UserList.this, FastSign.class));
                        break;
                    case 1:
                        startActivity(new Intent(UserList.this, AddUser.class));
                        break;
                }
            }
        });
    }

    //初始化菜单数据
    private void initMenuData() {
        menus = new ArrayList<>();
        drawable = getResources().getDrawable(R.mipmap.add_new);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("快速新增", drawable);
        menus.add(menu);
        drawable = getResources().getDrawable(R.mipmap.add_user);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("新增用户", drawable);
        menus.add(menu);
    }

    @Override
    public void getUserList(HttpBean<PageBean<UserBean>> httpBean) {
        userBeens.addAll(httpBean.getData().getList());
        int size = userBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
        if (adapter == null) {
            adapter = new UserAdapter(userBeens, this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<UserBean>() {
                @Override
                public void onItemClick(View view, UserBean userBean, int position) {
                    startActivity(new Intent(UserList.this, AddUser.class).putExtra("user", userBean));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(UserEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
