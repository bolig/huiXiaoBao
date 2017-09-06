package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.PopupMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//客户页面
public class Client extends Fragment implements ClientManage.View{
    Unbinder unbinder;
    @BindView(R.id.client_menu)
    ImageView clientMenu;
    private Drawable drawable;
    private Menu menu;
    private List<Menu> menus;
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
        return view;
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
                break;
            case R.id.client_type:
                break;
            case R.id.client_sort:
                break;
        }
    }
    //弹出菜单
    private void popupMenu() {
        initMenuData();
        PopupMenu.Build(menus, getContext(),clientMenu).init();
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

    @Override
    public void getClientList(List<ClientBean> clientBeens) {

    }
}
