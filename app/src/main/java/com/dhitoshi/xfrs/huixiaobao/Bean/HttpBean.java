package com.dhitoshi.xfrs.huixiaobao.Bean;

/**
 * Created by dxs on 2017/6/6.
 */

public class HttpBean<T> {
    private Status status;
    private T data;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
