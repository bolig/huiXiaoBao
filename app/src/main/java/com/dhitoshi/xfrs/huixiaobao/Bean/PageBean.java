package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/11/10.
 */

public class PageBean {

    /**
     * totalCount : 128
     * numberOfPage : 7
     * page : 1
     */
    private int totalCount;
    private int numberOfPage;
    private int page;
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getNumberOfPage() {
        return numberOfPage;
    }
    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
}
