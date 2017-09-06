package com.dhitoshi.xfrs.huixiaobao.http;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.Product;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductType;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;

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
    @GET("area/areaLists")
    Observable<HttpBean<List<AreaBean>>> getAreaLists();
    @GET("group/groupLists")
    Observable<HttpBean<List<UserRole>>> getGroupLists(@Query("token") String token);
    //获取客户列表
    @GET("customer/list")
    Observable<HttpBean<List<ClientBean>>> getClientList(@Query("type") String type, @Query("area") String area, @Query("order") String order, @Query("page") String page);
    //获取筛选框信息
    @GET("customer/selectCustomer")
    Observable<HttpBean<ScreenBean>> getSelectCustomer();
    //电话号码查重
    @GET("customer/checkRepeat")
    Observable<HttpBean<Object>> checkRepeat(@Query("area") String area,@Query("phone") String phone);
    //获取产品类别
    @GET("item/itemType")
    Observable<HttpBean<List<ProductType>>> getItemType();
    //获取产品列表
    @GET("item/item")
    Observable<HttpBean<List<Product>>> getItem();
    //获取消费所需列表
    @GET("customer/spending/listForSpending")
    Observable<HttpBean<Object>> getListForSpending();
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
