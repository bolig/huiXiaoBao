package com.dhitoshi.xfrs.huixiaobao.Bean;
import android.graphics.drawable.Drawable;
/**
 * Created by dxs on 2017/9/5.
 */
public class Menu {
    private String title;
    private Drawable drawable;
    public Menu(String title, Drawable drawable) {
        this.title = title;
        this.drawable = drawable;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Drawable getDrawable() {
        return drawable;
    }
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
