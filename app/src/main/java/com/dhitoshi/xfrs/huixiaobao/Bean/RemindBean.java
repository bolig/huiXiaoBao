package com.dhitoshi.xfrs.huixiaobao.Bean;
import java.util.List;

/**
 * Created by dxs on 2017/9/23.
 */
public class RemindBean {
    private List<ClientBean> birthday_today;
    private List<ClientBean> birthday_month;
    private List<ClientBean> feedtime_un;
    private List<ClientBean> feedtime_today;
    private List<ClientBean> feedtime_month;
    private List<ClientBean> buy_un;
    private List<ClientBean> buy_d_month;
    private int birthday_today_count;
    private int birthday_month_count;
    private int feedtime_un_count;
    private int feedtime_today_count;
    private int feedtime_month_count;
    private int buy_un_count;
    private int buy_d_month_count;

    public List<ClientBean> getBirthday_today() {
        return birthday_today;
    }

    public void setBirthday_today(List<ClientBean> birthday_today) {
        this.birthday_today = birthday_today;
    }

    public List<ClientBean> getBirthday_month() {
        return birthday_month;
    }

    public void setBirthday_month(List<ClientBean> birthday_month) {
        this.birthday_month = birthday_month;
    }

    public List<ClientBean> getFeedtime_un() {
        return feedtime_un;
    }

    public void setFeedtime_un(List<ClientBean> feedtime_un) {
        this.feedtime_un = feedtime_un;
    }

    public List<ClientBean> getFeedtime_today() {
        return feedtime_today;
    }

    public void setFeedtime_today(List<ClientBean> feedtime_today) {
        this.feedtime_today = feedtime_today;
    }

    public List<ClientBean> getFeedtime_month() {
        return feedtime_month;
    }

    public void setFeedtime_month(List<ClientBean> feedtime_month) {
        this.feedtime_month = feedtime_month;
    }

    public List<ClientBean> getBuy_un() {
        return buy_un;
    }

    public void setBuy_un(List<ClientBean> buy_un) {
        this.buy_un = buy_un;
    }

    public List<ClientBean> getBuy_d_month() {
        return buy_d_month;
    }

    public void setBuy_d_month(List<ClientBean> buy_d_month) {
        this.buy_d_month = buy_d_month;
    }

    public int getBirthday_today_count() {
        return birthday_today_count;
    }

    public void setBirthday_today_count(int birthday_today_count) {
        this.birthday_today_count = birthday_today_count;
    }

    public int getBirthday_month_count() {
        return birthday_month_count;
    }

    public void setBirthday_month_count(int birthday_month_count) {
        this.birthday_month_count = birthday_month_count;
    }

    public int getFeedtime_un_count() {
        return feedtime_un_count;
    }

    public void setFeedtime_un_count(int feedtime_un_count) {
        this.feedtime_un_count = feedtime_un_count;
    }

    public int getFeedtime_today_count() {
        return feedtime_today_count;
    }

    public void setFeedtime_today_count(int feedtime_today_count) {
        this.feedtime_today_count = feedtime_today_count;
    }

    public int getFeedtime_month_count() {
        return feedtime_month_count;
    }

    public void setFeedtime_month_count(int feedtime_month_count) {
        this.feedtime_month_count = feedtime_month_count;
    }

    public int getBuy_un_count() {
        return buy_un_count;
    }

    public void setBuy_un_count(int buy_un_count) {
        this.buy_un_count = buy_un_count;
    }

    public int getBuy_d_month_count() {
        return buy_d_month_count;
    }

    public void setBuy_d_month_count(int buy_d_month_count) {
        this.buy_d_month_count = buy_d_month_count;
    }
}
