package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/7.
 */
public class HttpPageBean<T> {
    private List<T> list;
    private int nextPage;
    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
