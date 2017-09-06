package com.dhitoshi.xfrs.huixiaobao.http;

/**
 * Created by dxs on 2017/6/7.
 */

public interface HttpResult<T> {
    //请求成功回调
    void OnSuccess(T t);
    //请求失败回调
    void  OnFail(String msg);
}
