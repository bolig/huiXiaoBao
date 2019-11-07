package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/11/10.
 */
public class HttpPageBeanTwo<T>{
    private List<T> data;
    private PageBean paging;
    private Status status;
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public PageBean getPaging() {
        return paging;
    }
    public void setPaging(PageBean paging) {
        this.paging = paging;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
