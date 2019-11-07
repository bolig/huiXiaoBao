package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MenuItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.MenuAdapter;
import com.dhitoshi.xfrs.huixiaobao.utils.DensityUtil;

import java.util.List;
/**
 * Created by dxs on 2017/9/5.
 */
public class PopupMenu {
    private View parent;
    private Context context;
    private List<Menu> menus;
    private  PopupWindow popupWindow;
    private MenuItemClick<Menu> menuItemClick;
    public PopupMenu(List<Menu> menus,Context context,View view) {
        this.parent = view;
        this.context=context;
        this.menus=menus;
    }
    public  static  PopupMenu Build(List<Menu> menus,Context context,View view){
        return new PopupMenu(menus,context,view);
    }
    public PopupMenu init(){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.client_menu, null);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.menu_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        MenuAdapter adapter=new MenuAdapter(menus,context,R.layout.menu_item,2);
        recyclerView.setAdapter(adapter);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.anim_style);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        adapter.addItemClickListener(new ItemClick<Menu>() {
            @Override
            public void onItemClick(View view, Menu menu, int position) {
                menuItemClick.onMenuItemClick(menu,position);
                popupWindow.dismiss();
            }
        });
        return this;
    }
    public void addMenuItemClickListener(MenuItemClick<Menu> menuItemClick) {
        this.menuItemClick = menuItemClick;
    }
    public void show(){
        popupWindow.showAtLocation(parent, Gravity.TOP|Gravity.RIGHT, DensityUtil.dp2px(10) ,parent.getHeight()+DensityUtil.dp2px(13));
    }
    public  void dismisss(){
        if(null!=popupWindow){
            popupWindow.dismiss();
        }
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }
}
