package com.dhitoshi.xfrs.huixiaobao.common;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by dxs on 2017/6/7.
 */
public class CommonObserver<T> implements Observer<T> {

    private HttpResult<T> result;
    private Disposable disposable;

    public CommonObserver(HttpResult<T> result) {
        this.result = result;
    }
    @Override
    public void onSubscribe(@NonNull Disposable d) {
    disposable=d;
    }
    @Override
    public void onNext(@NonNull T t) {
        result.OnSuccess(t);
        Log.e("TAG","result:--->>>"+ JSON.toJSONString(t));
    }
    @Override
    public void onError(@NonNull Throwable e) {
        Log.e("TAG","error:--->>>"+ e.toString());
        result.OnFail("网络君不给力,请稍后尝试...");
    }
    @Override
    public void onComplete() {

    }

}
