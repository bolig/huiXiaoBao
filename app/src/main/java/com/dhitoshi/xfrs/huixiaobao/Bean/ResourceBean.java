package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;
/**
 * Created by dxs on 2017/9/23.
 */
public class ResourceBean {
    private List<ClientBean> customer_month;
    private List<ClientBean> customer_now;
    private List<ClientBean> customer_today;
    private List<ClientBean> customer_week;
    private List<ClientBean> feedback_today;
    private List<SpendBean> sale_month;
    private List<SpendBean> sale_today;
    private List<SpendBean> sale_week;
    private int customer_month_count;
    private int customer_now_count;
    private int customer_today_count;
    private int customer_week_count;
    private int feedback_today_count;
    private int sale_month_total;
    private int sale_today_total;
    private int sale_week_total;

    public List<ClientBean> getCustomer_month() {
        return customer_month;
    }

    public void setCustomer_month(List<ClientBean> customer_month) {
        this.customer_month = customer_month;
    }

    public List<ClientBean> getCustomer_now() {
        return customer_now;
    }

    public void setCustomer_now(List<ClientBean> customer_now) {
        this.customer_now = customer_now;
    }

    public List<ClientBean> getCustomer_today() {
        return customer_today;
    }

    public void setCustomer_today(List<ClientBean> customer_today) {
        this.customer_today = customer_today;
    }

    public List<ClientBean> getCustomer_week() {
        return customer_week;
    }

    public void setCustomer_week(List<ClientBean> customer_week) {
        this.customer_week = customer_week;
    }

    public List<ClientBean> getFeedback_today() {
        return feedback_today;
    }

    public void setFeedback_today(List<ClientBean> feedback_today) {
        this.feedback_today = feedback_today;
    }

    public List<SpendBean> getSale_month() {
        return sale_month;
    }

    public void setSale_month(List<SpendBean> sale_month) {
        this.sale_month = sale_month;
    }

    public List<SpendBean> getSale_today() {
        return sale_today;
    }

    public void setSale_today(List<SpendBean> sale_today) {
        this.sale_today = sale_today;
    }

    public List<SpendBean> getSale_week() {
        return sale_week;
    }

    public void setSale_week(List<SpendBean> sale_week) {
        this.sale_week = sale_week;
    }

    public int getCustomer_month_count() {
        return customer_month_count;
    }

    public void setCustomer_month_count(int customer_month_count) {
        this.customer_month_count = customer_month_count;
    }

    public int getCustomer_now_count() {
        return customer_now_count;
    }

    public void setCustomer_now_count(int customer_now_count) {
        this.customer_now_count = customer_now_count;
    }

    public int getCustomer_today_count() {
        return customer_today_count;
    }

    public void setCustomer_today_count(int customer_today_count) {
        this.customer_today_count = customer_today_count;
    }

    public int getCustomer_week_count() {
        return customer_week_count;
    }

    public void setCustomer_week_count(int customer_week_count) {
        this.customer_week_count = customer_week_count;
    }

    public int getFeedback_today_count() {
        return feedback_today_count;
    }

    public void setFeedback_today_count(int feedback_today_count) {
        this.feedback_today_count = feedback_today_count;
    }

    public int getSale_month_total() {
        return sale_month_total;
    }

    public void setSale_month_total(int sale_month_total) {
        this.sale_month_total = sale_month_total;
    }

    public int getSale_today_total() {
        return sale_today_total;
    }

    public void setSale_today_total(int sale_today_total) {
        this.sale_today_total = sale_today_total;
    }

    public int getSale_week_total() {
        return sale_week_total;
    }

    public void setSale_week_total(int sale_week_total) {
        this.sale_week_total = sale_week_total;
    }
}
