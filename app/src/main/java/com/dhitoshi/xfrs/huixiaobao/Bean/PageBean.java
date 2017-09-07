package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/7.
 */

public class PageBean<T> {
    private List<T> data;
    private int nextPage;
    public  List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
