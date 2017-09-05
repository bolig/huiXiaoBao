package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by dxs on 2017/9/5.
 */
public class PopupMenu {
    private View parent;
    private Context context;
    private List<Menu> menus;
    public PopupMenu(List<Menu> menus,Context context,View view) {
        this.parent = view;
        this.context=context;
        this.menus=menus;
    }
    public  static  PopupMenu Build(List<Menu> menus,Context context,View view){
        return new PopupMenu(menus,context,view);
    }
    public void init(){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.client_menu, null);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.menu_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        MenuAdapter adapter=new MenuAdapter(menus,context,R.layout.menu_item,2);
        recyclerView.setAdapter(adapter);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.anim_style);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(parent, Gravity.TOP|Gravity.RIGHT, 30,parent.getHeight()+40);
    }
}
