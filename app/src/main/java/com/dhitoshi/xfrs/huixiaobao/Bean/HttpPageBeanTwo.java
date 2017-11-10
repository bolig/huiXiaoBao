package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/11/10.
 */
public class HttpPageBeanTwo<T>{
    private List<T> data;
    private PageBean paging;
    public List<T> getList() {
        return data;
    }
    public void setList(List<T> list) {
        this.data = list;
    }
    public PageBean getPaging() {
        return paging;
    }
    public void setPaging(PageBean paging) {
        this.paging = paging;
    }
}
