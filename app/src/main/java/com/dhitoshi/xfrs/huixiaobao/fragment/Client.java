package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;
import com.dhitoshi.xfrs.huixiaobao.presenter.ClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddClient;
import com.dhitoshi.xfrs.huixiaobao.view.ClientInfo;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//客户页面
public class Client extends Fragment implements ClientManage.View {
    Unbinder unbinder;
    @BindView(R.id.client_menu)
    ImageView clientMenu;
    @BindView(R.id.role_text)
    TextView roleText;
    @BindView(R.id.type_text)
    TextView typeText;
    @BindView(R.id.sort_text)
    TextView sortText;
    private Drawable drawable;
    private Drawable defualt;
    private Menu menu;
    private List<Menu> menus;
    private PopupMenu popupMenu;
    private Intent it;
    private int screen_oldPosition=-1;
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
        defualt = getContext().getResources().getDrawable(R.mipmap.down);
        defualt.setBounds(0, 0, defualt.getMinimumWidth(), defualt.getMinimumHeight());
        drawable = getContext().getResources().getDrawable(R.mipmap.up);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
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
        if(screen_oldPosition==0){
            roleText.setCompoundDrawables(null,null,defualt,null);
            roleText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition=-1;
        }else{
            roleText.setCompoundDrawables(null,null,drawable,null);
            roleText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition=0;
        }
    }
    //筛选类型
    private void screenRoleType() {
        if(screen_oldPosition==1){
            typeText.setCompoundDrawables(null,null,defualt,null);
            typeText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition=-1;
        }else{
            typeText.setCompoundDrawables(null,null,drawable,null);
            typeText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition=1;
        }
    }
    //筛选排序
    private void screenType() {
        if(screen_oldPosition==2){
            sortText.setCompoundDrawables(null,null,defualt,null);
            sortText.setTextColor(Color.parseColor("#666666"));
            screen_oldPosition=-1;
        }else{
            sortText.setCompoundDrawables(null,null,drawable,null);
            sortText.setTextColor(Color.parseColor("#34B1FF"));
            screen_oldPosition=2;
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
                        it = new Intent(getContext(), ClientInfo.class);
                        startActivity(it);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
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
         Log.e("TAG","客户类型数量:"+screenBean.getCustomer_type().size());
    }
}
