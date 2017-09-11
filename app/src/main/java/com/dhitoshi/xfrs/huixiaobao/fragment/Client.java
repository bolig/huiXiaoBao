package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Bean.OrderBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MyDismiss;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.OrderByAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.TypeAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;
import com.dhitoshi.xfrs.huixiaobao.common.PopupScreen;
import com.dhitoshi.xfrs.huixiaobao.presenter.ClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddClient;
import com.dhitoshi.xfrs.huixiaobao.view.ClientInfo;
import com.dhitoshi.xfrs.huixiaobao.view.Contact;
import com.dhitoshi.xfrs.huixiaobao.view.Query;
import com.dhitoshi.xfrs.huixiaobao.view.Remind;
import com.dhitoshi.xfrs.huixiaobao.view.Resource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//客户页面
public class Client extends Fragment implements ClientManage.View, View.OnTouchListener {
    Unbinder unbinder;
    @BindView(R.id.client_menu)
    AppCompatImageView clientMenu;
    @BindView(R.id.role_text)
    TextView roleText;
    @BindView(R.id.type_text)
    TextView typeText;
    @BindView(R.id.sort_text)
    TextView sortText;
    @BindView(R.id.screen_view)
    View screenView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Drawable drawable;
    private Drawable down;
    private Drawable up;
    private Menu menu;
    private List<Menu> menus;
    private PopupMenu popupMenu;
    private List<AreaBean> areas;
    private List<CustomerTypeBean> customerTypes;
    private List<OrderBean> orders;
    private TypeAdapter typeAdapter;
    private OrderByAdapter orderByAdapter;
    private PopupScreen popupScreen;
    private Intent it;
    private int screen_oldPosition = -1;

    public Client() {
    }

    public static Client newInstance() {
        Client fragment = new Client();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_client, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        ClientPresenter clientPresenter = new ClientPresenter(this);
        clientPresenter.getSelectCustomer();
        clientMenu.setOnTouchListener(this);
        down = getContext().getResources().getDrawable(R.mipmap.down);
        down.setBounds(0, 0, down.getMinimumWidth(), down.getMinimumHeight());
        up = getContext().getResources().getDrawable(R.mipmap.up);
        up.setBounds(0, 0, up.getMinimumWidth(), up.getMinimumHeight());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.client_menu, R.id.client_role, R.id.client_type, R.id.client_sort})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.client_menu:
                popupMenu();
                break;
            case R.id.client_role:
                screenRole();
                break;
            case R.id.client_type:
                screenRoleType();
                break;
            case R.id.client_sort:
                screenType();
                break;
        }
    }

    //筛选角色
    private void screenRole() {
        if (screen_oldPosition == 0) {
            roleText.setCompoundDrawables(null, null, down, null);
            roleText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition = -1;
        } else {
            roleText.setCompoundDrawables(null, null, up, null);
            roleText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition = 0;
        }
    }

    //筛选类型
    private void screenRoleType() {
        if (screen_oldPosition == 1) {
            typeText.setCompoundDrawables(null, null, down, null);
            typeText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition = -1;
        } else {
            typeText.setCompoundDrawables(null, null, up, null);
            typeText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition = 1;
        }
        popupScreen(1);
    }

    //筛选排序
    private void screenType() {
        if (screen_oldPosition == 2) {
            sortText.setCompoundDrawables(null, null, down, null);
            sortText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition = -1;
        } else {
            sortText.setCompoundDrawables(null, null, up, null);
            sortText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition = 2;
        }
        popupScreen(2);
    }

    //弹出筛选框(类型 排序)
    private void popupScreen(final int type) {
        if (null == popupScreen) {
            popupScreen = PopupScreen.Build(getContext(), screenView).init();
            popupScreen.setResource(type == 1 ? typeAdapter : orderByAdapter);
            popupScreen.show();
            popupScreen.addDismiss(new MyDismiss() {
                @Override
                public void dismiss() {
                    typeText.setCompoundDrawables(null, null, down, null);
                    typeText.setTextColor(Color.parseColor("#666666"));
                    sortText.setCompoundDrawables(null, null, down, null);
                    sortText.setTextColor(Color.parseColor("#666666"));
                    screen_oldPosition = -1;
                }
            });
        } else {
            if (!popupScreen.isShowing()) {
                popupScreen.setResource(type == 1 ? typeAdapter : orderByAdapter);
                popupScreen.show();
            } else {
                popupScreen.dismisss();
            }
        }
    }
    //弹出菜单
    private void popupMenu() {
        if (null == popupMenu) {
            initMenuData();
            popupMenu = PopupMenu.Build(menus, getContext(), clientMenu).init();
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
                        it = new Intent(getContext(), AddClient.class);
                        startActivity(it);
                        break;
                    case 2:
                        it = new Intent(getContext(), Query.class);
                        startActivity(it);
                        break;
                    case 3:
                        it = new Intent(getContext(), Remind.class);
                        startActivity(it);
                        break;
                    case 4:
                        it = new Intent(getContext(), Resource.class);
                        startActivity(it);
                        break;
                    case 5:
                        it = new Intent(getContext(), ClientInfo.class);
                        startActivity(it);
                        break;
                    case 6:
                        it=new Intent(getContext(), Contact.class);
                        startActivity(it);
                        break;
                }
            }
        });
    }

    //初始化菜单数据
    private void initMenuData() {
        menus = new ArrayList<>();
        drawable = getContext().getResources().getDrawable(R.mipmap.scan);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("扫一扫", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.add_client);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("新增客户", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.query);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("高级查询", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.remind);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("客户提醒", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.resource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("我的资源", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.location);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("周边客户", drawable);
        menus.add(menu);
        drawable = getContext().getResources().getDrawable(R.mipmap.resource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("导入通讯录", drawable);
        menus.add(menu);
    }
    //获取客户列表
    @Override
    public void getClientList(List<ClientBean> clientBeens) {

    }
    //获取筛选条件信息
    @Override
    public void getSelectCustomer(ScreenBean screenBean) {
        areas = screenBean.getArea();
        customerTypes = screenBean.getCustomer_type();
        typeAdapter = new TypeAdapter(customerTypes, getContext(), R.layout.screen_item);
        typeAdapter.addItemClickListener(new ItemClick<CustomerTypeBean>() {
            @Override
            public void onItemClick(View view, CustomerTypeBean customerTypeBean, int position) {
                popupScreen.dismisss();
                typeText.setText(customerTypeBean.getName());
            }
        });
        orders = screenBean.getOrder();
        orderByAdapter = new OrderByAdapter(orders, getContext(), R.layout.screen_item);
        orderByAdapter.addItemClickListener(new ItemClick<OrderBean>() {
            @Override
            public void onItemClick(View view, OrderBean orderBean, int position) {
                popupScreen.dismisss();
                sortText.setText(orderBean.getName());
            }
        });
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewCompat.setAlpha(v, 0.5f);
                break;
            case MotionEvent.ACTION_UP:
                ViewCompat.setAlpha(v, 1.0f);
                break;
        }
        return false;
    }
}
