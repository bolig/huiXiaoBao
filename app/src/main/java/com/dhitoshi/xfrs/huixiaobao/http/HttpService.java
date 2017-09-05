package com.dhitoshi.xfrs.huixiaobao.http;
import android.database.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
/**
 * Created by dxs on 2017/5/27.
 */
public interface HttpService {
    //获取地区列表
//    @GET("area/areaLists")
//    Observable<Home> getHome(@Query("token") String token, @Query("city_id") int cityId);
    //更改密码
    @POST("resetPassword")
    Observable<Integer> resetPassword(@Body RequestBody body);
    //登录
    @POST("login")
    Observable<Integer> login(@Body RequestBody body);
    //注册
    @POST("signUp")
    Observable<Integer> signUp(@Body RequestBody body);
}
