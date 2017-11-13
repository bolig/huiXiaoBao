package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/11/12.
 */

public class VideoBean {

    /**
     * id : 1
     * title : 视频1
     * number : 一
     * url : htpp://www.baidu.com/1.mp4
     * hot : 12
     * create_time : 1506993303
     */

    private int id;
    private String title;
    private String number;
    private String url;
    private int hot;
    private int create_time;
    private String video_url;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
