package com.dhitoshi.xfrs.huixiaobao.http;
import com.dhitoshi.xfrs.huixiaobao.utils.PropertyUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by dxs on 2017/5/26.
 */
public class MyHttp {
    private Retrofit retrofit;
    private HttpService httpService;
    private static MyHttp http;
    public MyHttp() {
        OkHttpClient.Builder httpcientBuilder = new OkHttpClient.Builder();
        retrofit=new Retrofit.Builder()
                .client(httpcientBuilder.build())
                .baseUrl(PropertyUtil.Obtain(MyHttp.class,"BaseUrl"))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        httpService=retrofit.create(HttpService.class);
    }
    public static MyHttp getInstance(){
        if (http == null) {
            synchronized (MyHttp.class){
                if (http == null){
                    http = new MyHttp();
                }
            }
        }
        return http;
    }
    public HttpService getHttpService() {
        return httpService;
    }
    public void send(Observable observable,Observer observer){
      observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
