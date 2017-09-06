package com.dhitoshi.xfrs.huixiaobao.http;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import java.util.List;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by dxs on 2017/5/27.
 */
public interface HttpService {
    //获取地区列表
//    @GET("area/areaLists")
//    Observable<Home> getHome(@Query("token") String token, @Query("city_id") int cityId);
    //获取客户列表
    @GET("customer/list")
    Observable<HttpBean<List<ClientBean>>> getClientList(@Query("type") String type, @Query("area") String area, @Query("order") String order, @Query("page") String page);

    //更改密码
    @POST("resetPassword")
    Observable<Integer> resetPassword(@Body RequestBody body);
    //登录
    @POST("login")
    Observable<Integer> login(@Body RequestBody body);
    //注册
    @POST("signUp")
    Observable<Integer> signUp(@Body RequestBody body);
    //添加客户
    @POST("customer/add")
    Observable<ClientBean> addClient(@Body RequestBody body);
}
