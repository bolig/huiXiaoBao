package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Bean.OrderBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Event.ClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MyDismiss;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ClientAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.OrderByAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.TypeAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.PopupArea;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;
import com.dhitoshi.xfrs.huixiaobao.common.PopupScreen;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.ClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.AddClient;
import com.dhitoshi.xfrs.huixiaobao.view.ClientInfo;
import com.dhitoshi.xfrs.huixiaobao.view.Contact;
import com.dhitoshi.xfrs.huixiaobao.view.Query;
import com.dhitoshi.xfrs.huixiaobao.view.Remind;
import com.dhitoshi.xfrs.huixiaobao.view.Resource;
import com.dhitoshi.xfrs.huixiaobao.view.SearchClient;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//客户页面
public class Client extends BaseFragment implements ClientManage.View, View.OnTouchListener {
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
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
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
    private PopupArea popupArea;
    private Intent it;
    private int screen_oldPosition = -1;
    private ClientPresenter clientPresenter;
    private int page = 1;
    private String type = "";
    private String area = "";
    private String order = "";
    private List<ClientBean> clients;
    private ClientAdapter adapter;
    public Client() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
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
        EventBus.getDefault().register(this);
        return view;
    }
    private void initViews() {
        area=SharedPreferencesUtil.Obtain(getContext(),"areId","").toString();
        clients = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        clientPresenter = new ClientPresenter(this, getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                clients.removeAll(clients);
                page = 1;
                Map<String, String> map = new HashMap<>();
                map.put("area", area);
                if(!type.isEmpty()){
                    map.put("type", type);
                }
                if(!order.isEmpty()){
                    map.put("order", order);
                }
                map.put("page", String.valueOf(page));
                String token=SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                map.put("token",token);
                clientPresenter.getClientList(map, smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                Map<String, String> map = new HashMap<>();
                map.put("area", area);
                if(!type.isEmpty()){
                    map.put("type", type);
                }
                if(!order.isEmpty()){
                    map.put("order", order);
                }
                map.put("page", String.valueOf(page));
                String token=SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                map.put("token",token);
                clientPresenter.getClientList(map, smartRefreshLayout);
            }
        });
        clientPresenter.getSelectCustomer(SharedPreferencesUtil.Obtain(getContext(),"token","").toString());
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
        EventBus.getDefault().unregister(this);
    }
    @OnClick({R.id.client_menu, R.id.client_role, R.id.client_type, R.id.client_sort, R.id.client_search})
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
            case R.id.client_search:
                startActivity(new Intent(getContext(), SearchClient.class));
                break;
        }
    }
    //筛选角色
    private void screenRole() {
        popupRole();
    }
    //弹出筛选框(地区)
    private void popupRole() {
        if(null==areas){
                reSelectCustomer(0);
        }else{
            if (screen_oldPosition == 0) {
                roleText.setCompoundDrawables(null, null, down, null);
                roleText.setTextColor(Color.parseColor("#666666"));
                screen_oldPosition = -1;
            } else {
                roleText.setCompoundDrawables(null, null, up, null);
                roleText.setTextColor(Color.parseColor("#34B1FF"));
                screen_oldPosition = 0;
            }
            if (null == popupArea) {
                popupArea = PopupArea.Build(getContext(), screenView).init(areas);
                popupArea.show();
                popupArea.addDismiss(new MyDismiss() {
                    @Override
                    public void dismiss() {
                        roleText.setCompoundDrawables(null, null, down, null);
                        roleText.setTextColor(Color.parseColor("#666666"));
                        screen_oldPosition = -1;
                        popupArea.dismisss();
                    }
                });
                popupArea.addAreaClick(new AreaCallback() {
                    @Override
                    public void getArea(String id, String areaName) {
                        area = id;
                        roleText.setText(areaName);
                        roleText.setCompoundDrawables(null, null, down, null);
                        roleText.setTextColor(Color.parseColor("#666666"));
                        screen_oldPosition = -1;
                        smartRefreshLayout.autoRefresh();
                    }
                });
            } else {
                if (!popupArea.isShowing()) {
                    popupArea.show();
                } else {
                    popupArea.dismisss();
                }
            }
        }
    }
    //筛选类型
    private void screenRoleType() {
        popupScreen(1);
    }
    //筛选排序
    private void screenType() {
        popupScreen(2);
    }
    //弹出筛选框(类型 排序)
    private void popupScreen(final int type)  {
        if(null==typeAdapter||null==orderByAdapter){
            reSelectCustomer(type);
        }else{
            if(type==1){
                if (screen_oldPosition == 1) {
                    typeText.setCompoundDrawables(null, null, down, null);
                    typeText.setTextColor(Color.parseColor("#666666"));
                    screen_oldPosition = -1;
                } else {
                    typeText.setCompoundDrawables(null, null, up, null);
                    typeText.setTextColor(Color.parseColor("#34B1FF"));
                    screen_oldPosition = 1;
                }
            }else{
                if (screen_oldPosition == 2) {
                    sortText.setCompoundDrawables(null, null, down, null);
                    sortText.setTextColor(Color.parseColor("#666666"));
                    screen_oldPosition = -1;
                } else {
                    sortText.setCompoundDrawables(null, null, up, null);
                    sortText.setTextColor(Color.parseColor("#34B1FF"));
                    screen_oldPosition = 2;
                }
            }
            if (null == popupScreen) {
                popupScreen = PopupScreen.Build(getContext(), screenView).init();
                typeAdapter.setSelected(typeText.getText().toString());
                orderByAdapter.setSelected(sortText.getText().toString());
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
                        popupScreen.dismisss();
                    }
                });
            } else {
                if (!popupScreen.isShowing()) {
                    typeAdapter.setSelected(typeText.getText().toString());
                    orderByAdapter.setSelected(sortText.getText().toString());
                    popupScreen.setResource(type == 1 ? typeAdapter : orderByAdapter);
                    popupScreen.show();
                } else {
                    popupScreen.dismisss();
                }
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
                        it = new Intent(getContext(), AddClient.class);
                        startActivity(it);
                        break;
                    case 1:
                        it = new Intent(getContext(), Query.class);
                        startActivity(it);
                        break;
                    case 2:
                        it = new Intent(getContext(), Remind.class);
                        startActivity(it);
                        break;
                    case 3:
                        it = new Intent(getContext(), Resource.class);
                        startActivity(it);
                        break;
                    case 4:
                        it = new Intent(getContext(), Contact.class);
                        startActivity(it);
                        break;
                }
            }
        });
    }
    //初始化菜单数据
    private void initMenuData() {
        menus = new ArrayList<>();
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
        drawable = getContext().getResources().getDrawable(R.mipmap.address_book);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        menu = new Menu("导入通讯录", drawable);
        menus.add(menu);
    }
    //获取客户列表
    @Override
    public void getClientList(HttpPageBean<ClientBean> httpPageBean) {
        clients.addAll(httpPageBean.getList());
        int size = clients.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new ClientAdapter(clients, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<ClientBean>() {
                @Override
                public void onItemClick(View view, ClientBean clientBean, int position) {
                    startActivity(new Intent(getContext(), ClientInfo.class).putExtra("info", clientBean));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }
    //获取筛选条件信息
    @Override
    public void getSelectCustomer(ScreenBean screenBean) {
        areas = screenBean.getArea();
        customerTypes = screenBean.getCustomer_type();
        typeAdapter = new TypeAdapter(customerTypes, getContext());
        typeAdapter.addItemClickListener(new ItemClick<CustomerTypeBean>() {
            @Override
            public void onItemClick(View view, CustomerTypeBean customerTypeBean, int position) {
                popupScreen.dismisss();
                typeText.setCompoundDrawables(null, null, down, null);
                typeText.setTextColor(Color.parseColor("#666666"));
                screen_oldPosition = -1;
                typeText.setText(customerTypeBean.getName());
                type = String.valueOf(customerTypeBean.getId());
                smartRefreshLayout.autoRefresh();
            }
        });
        orders = screenBean.getOrder();
        orderByAdapter = new OrderByAdapter(orders, getContext());
        orderByAdapter.addItemClickListener(new ItemClick<OrderBean>() {
            @Override
            public void onItemClick(View view, OrderBean orderBean, int position) {
                popupScreen.dismisss();
                sortText.setCompoundDrawables(null, null, down, null);
                sortText.setTextColor(Color.parseColor("#666666"));
                screen_oldPosition = -1;
                sortText.setText(orderBean.getName());
                order = String.valueOf(orderBean.getId());
                smartRefreshLayout.autoRefresh();
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ClientEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
    private void reSelectCustomer(final int location){
        MyHttp http=MyHttp.getInstance();
        String token=SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
        http.send(http.getHttpService().getSelectCustomer(token),new CommonObserver(new HttpResult<HttpBean<ScreenBean>>() {
            @Override
            public void OnSuccess(HttpBean<ScreenBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    areas = httpBean.getData().getArea();
                    customerTypes =  httpBean.getData().getCustomer_type();
                    typeAdapter = new TypeAdapter(customerTypes, getContext());
                    typeAdapter.addItemClickListener(new ItemClick<CustomerTypeBean>() {
                        @Override
                        public void onItemClick(View view, CustomerTypeBean customerTypeBean, int position) {
                            popupScreen.dismisss();
                            typeText.setCompoundDrawables(null, null, down, null);
                            typeText.setTextColor(Color.parseColor("#666666"));
                            screen_oldPosition = -1;
                            typeText.setText(customerTypeBean.getName());
                            type = String.valueOf(customerTypeBean.getId());
                            smartRefreshLayout.autoRefresh();
                        }
                    });
                    orders =  httpBean.getData().getOrder();
                    orderByAdapter = new OrderByAdapter(orders, getContext());
                    orderByAdapter.addItemClickListener(new ItemClick<OrderBean>() {
                        @Override
                        public void onItemClick(View view, OrderBean orderBean, int position) {
                            popupScreen.dismisss();
                            sortText.setCompoundDrawables(null, null, down, null);
                            sortText.setTextColor(Color.parseColor("#666666"));
                            screen_oldPosition = -1;
                            sortText.setText(orderBean.getName());
                            order = String.valueOf(orderBean.getId());
                            smartRefreshLayout.autoRefresh();
                        }
                    });
                    if(location==0){
                        popupRole();
                    }else {
                        popupScreen(location);
                    }
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(getContext(), new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           reSelectCustomer(location);
                        }
                    });
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(getContext(),"获取筛选条件失败...",Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
