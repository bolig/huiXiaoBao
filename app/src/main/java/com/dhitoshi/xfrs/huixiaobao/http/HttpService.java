package com.dhitoshi.xfrs.huixiaobao.http;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.fragment.Relation;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by dxs on 2017/5/27.
 */
public interface HttpService {
    //获取地区列表
    @GET("area/areaLists")
    Observable<HttpBean<List<AreaBean>>> getAreaLists();
    //获取权限组列表
    @GET("group/groupLists")
    Observable<HttpBean<List<UserRole>>> getGroupLists(@Query("token") String token);
    //获取客户列表
    @GET("customer/list")
    Observable<HttpBean<PageBean<ClientBean>>> getClientList(@QueryMap Map<String,String> map);
    @GET("customer/list")
    Observable<HttpBean<PageBean<ClientBean>>> getClientList();
    //获取筛选框信息
    @GET("customer/selectCustomer")
    Observable<HttpBean<ScreenBean>> getSelectCustomer();
    //获取增加客户所需列表
    @GET("customer/infoForAdd")
    Observable<HttpBean<InfoAddClientBean>> getInfoForAdd();
    //电话号码查重
    @GET("customer/checkRepeat")
    Observable<HttpBean<Object>> checkRepeat(@Query("area") String area,@Query("phone") String phone,@Query("id") String id);
    //获取产品类别
    @GET("item/itemType")
    Observable<HttpBean<List<ProductTypeBean>>> getItemType();
    //获取产品列表
    @GET("item/item")
    Observable<HttpBean<List<ProductBean>>> getItem();
    //获取消费所需列表
    @GET("customer/spending/listForSpending")
    Observable<HttpBean<InfoAddSpendBean>> getListForSpending();
    //获取回访所需列表
    @GET("customer/feedback/listForFeedBack")
    Observable<HttpBean<InfoAddVisitBean>> getListForVisit();
    //获取社会关系所需列表
    @GET("customer/relation/listForRelation")
    Observable<HttpBean<InfoAddRelationBean>> getListForRelation();
    //获取赠品所需列表
    @GET("customer/gift/listForGift")
    Observable<HttpBean<InfoAddSpendBean>> getListForGift();
    //获取会议所需列表
    @GET("customer/meeting/listForMeeting")
    Observable<HttpBean<InfoAddMeetBean>> getListForMeeting();
    //获取参会记录列表
    @GET("customer/meeting/list")
    Observable<HttpBean<PageBean<MeetBean>>> getMeetingLists(@Query("userid") String userid, @Query("page") String page);
    //获取社会关系列表
    @GET("customer/relation/list")
    Observable<HttpBean<PageBean<RelationBean>>> getRelationLists(@Query("userid") String userid, @Query("page") String page);
    //获取回访记录列表
    @GET("customer/feedback/list")
    Observable<HttpBean<PageBean<VisitBean>>> getFeedbackLists(@Query("userid") String userid, @Query("page") String page);
    //获取消费记录列表
    @GET("customer/spending/list")
    Observable<HttpBean<PageBean<SpendBean>>> getSpendingLists(@Query("userid") String userid, @Query("page") String page);
    //获取赠品记录列表
    @GET("customer/gift/list")
    Observable<HttpBean<PageBean<GiftBean>>> getGiftLists(@Query("userid") String userid, @Query("page") String page);
    //删除产品
    @GET("public/item/delete")
    Observable<HttpBean<Object>> deleteItem(@Query("token") String token,@Query("id") String id);
    //删除产品类型
    @GET("public/itemType/delete")
    Observable<HttpBean<Object>> deleteItemType(@Query("token") String token,@Query("id") String id);

    //更改密码
    @POST("resetPassword")
    Observable<Integer> resetPassword(@Body RequestBody body);
    //登录
    @POST("login")
    Observable<HttpBean<UserBean>> login(@QueryMap Map<String,String> map);
    //注册
    @POST("signUp")
    Observable<Integer> signUp(@Body RequestBody body);
    //添加客户
    @POST("customer/add")
    Observable<HttpBean<ClientBean>> addClient(@Body AddClientBean addClientBean);
    //编辑用户资料
    @POST("customer/edit")
    Observable<HttpBean<ClientBean>> editClient(@Body AddClientBean addClientBean);
    //添加消费记录
    @POST("customer/spending/add")
    Observable<HttpBean<SpendBean>> addSpend(@Body AddSpendBean addSpendBean);
    //编辑消费记录
    @POST("customer/spending/edit")
    Observable<HttpBean<SpendBean>> editSpend(@Body AddSpendBean addSpendBean);
    //添加回访
    @POST("customer/feedback/add")
    Observable<HttpBean<VisitBean>> addVisit(@Body AddVisitBean addVisitBean);
    //编辑回访
    @POST("feedback/edit")
    Observable<HttpBean<VisitBean>> editVisit(@Body AddVisitBean addVisitBean);
    //添加社会关系
    @POST("customer/relation/add")
    Observable<HttpBean<RelationBean>> addRelation(@Body AddRelationBean addRelationBean);
    //编辑社会关系
    @POST("customer/relation/edit")
    Observable<HttpBean<RelationBean>> editRelation(@Body AddRelationBean addRelationBean);
    //添加赠品
    @POST("customer/gift/add")
    Observable<HttpBean<GiftBean>> addGift(@Body AddGiftBean addGiftBean);
    //编辑赠品
    @POST("customer/gift/edit")
    Observable<HttpBean<GiftBean>> editGift(@Body AddGiftBean addGiftBean);
    //添加会议记录
    @POST("customer/meeting/add")
    Observable<HttpBean<MeetBean>> addMeet(@Body AddMeetBean addMeetBean);
    //编辑会议记录
    @POST("customer/meeting/edit")
    Observable<HttpBean<MeetBean>> editMeet(@Body AddMeetBean addMeetBean);
    //添加产品
    @POST("item/add")
    Observable<HttpBean<Object>> addItem(@Body RequestBody body);
    //编辑产品
    @POST("item/edit")
    Observable<HttpBean<Object>> updateItem(@Body RequestBody body);
    //添加产品类型
    @POST("itemType/add")
    Observable<HttpBean<Object>> addItemType(@Body RequestBody body);
    //编辑产品类型
    @POST("itemType/edit")
    Observable<HttpBean<Object>> updateItemType(@Body RequestBody body);

}
