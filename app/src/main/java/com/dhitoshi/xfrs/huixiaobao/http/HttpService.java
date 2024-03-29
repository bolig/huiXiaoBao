package com.dhitoshi.xfrs.huixiaobao.http;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BulkImportBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MoreMeetInfoBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PlayCountBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.QueryResultBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RemindBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ResourceBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Bean.VersionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
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
    Observable<HttpBean<List<AreaBean>>> getAreaLists(@Query("token") String token);
    //获取权限组列表
    @GET("group/groupLists")
    Observable<HttpBean<List<UserRole>>> getGroupLists(@Query("token") String token);
    //获取客户列表
    @GET("customer/list")
    Observable<HttpBean<HttpPageBean<ClientBean>>> getClientList(@QueryMap Map<String,String> map);
    //搜索客户
    @GET("customer/search")
    Observable<HttpBean<HttpPageBean<ClientBean>>> searchClientList(@Query("token") String token, @Query("search") String search, @Query("page") String page);
    //获取筛选框信息
    @GET("customer/selectCustomer")
    Observable<HttpBean<ScreenBean>> getSelectCustomer(@Query("token") String token);
    //获取增加客户所需列表
    @GET("customer/infoForAdd")
    Observable<HttpBean<InfoAddClientBean>> getInfoForAdd(@Query("token") String token);
    //电话号码查重
    @GET("customer/checkRepeat")
    Observable<HttpBean<Object>> checkRepeat(@Query("token") String token,@Query("area") String area,@Query("phone") String phone,@Query("id") String id);
    //获取产品类别
    @GET("item/itemType")
    Observable<HttpBean<HttpPageBean<BaseBean>>> getItemType(@Query("token") String token, @Query("page") String page);
    //获取产品列表
    @GET("item/item")
    Observable<HttpBean<HttpPageBean<ProductBean>>> getItem(@Query("token") String token, @Query("page") String page);
    //获取消费所需列表
    @GET("customer/spending/listForSpending")
    Observable<HttpBean<InfoAddSpendBean>> getListForSpending(@Query("token") String token);
    //获取回访所需列表
    @GET("customer/feedback/listForFeedBack")
    Observable<HttpBean<InfoAddVisitBean>> getListForVisit(@Query("token") String token);
    //获取社会关系所需列表
    @GET("customer/relation/listForRelation")
    Observable<HttpBean<InfoAddRelationBean>> getListForRelation(@Query("token") String token);
    //获取赠品所需列表
    @GET("customer/gift/listForGift")
    Observable<HttpBean<InfoAddGiftBean>> getListForGift(@Query("token") String token);
    //获取会议所需列表
    @GET("customer/meeting/listForMeeting")
    Observable<HttpBean<InfoAddMeetBean>> getListForMeeting(@Query("token") String token);
    //获取参会记录列表
    @GET("customer/meeting/list")
    Observable<HttpBean<HttpPageBean<MeetBean>>> getMeetingLists(@Query("token") String token, @Query("userid") String userid, @Query("page") String page);
    //获取社会关系列表
    @GET("customer/relation/list")
    Observable<HttpBean<HttpPageBean<RelationBean>>> getRelationLists(@Query("token") String token, @Query("userid") String userid, @Query("page") String page);
    //获取回访记录列表
    @GET("customer/feedback/list")
    Observable<HttpBean<HttpPageBean<VisitBean>>> getFeedbackLists(@Query("token") String token, @Query("userid") String userid, @Query("page") String page);
    //获取消费记录列表
    @GET("customer/spending/list")
    Observable<HttpBean<HttpPageBean<SpendBean>>> getSpendingLists(@Query("token") String token, @Query("userid") String userid, @Query("page") String page);
    //获取赠品记录列表
    @GET("customer/gift/list")
    Observable<HttpBean<HttpPageBean<GiftBean>>> getGiftLists(@Query("token") String token, @Query("userid") String userid, @Query("page") String page);
    //删除产品
    @GET("item/delete")
    Observable<HttpBean<Object>> deleteItem(@Query("token") String token,@Query("id") String id);
    //删除产品类型
    @GET("itemType/delete")
    Observable<HttpBean<Object>> deleteItemType(@Query("token") String token,@Query("id") String id);
    //获得用户列表
    @GET("user/detailList")
    Observable<HttpBean<HttpPageBean<UserBean>>> getUserList(@Query("token") String token, @Query("page") String page);
    //删除权限组
    @GET("group/delete")//id name area is_super notes token
    Observable<HttpBean<Object>> deleteGroup(@Query("id") String id,@Query("token") String token);
    //删除地区
    @GET("area/delete")//id name area is_super notes token
    Observable<HttpBean<Object>> deleteArea(@Query("id") String id,@Query("token") String token);
    //我的资源
    @GET("customer/myResource")
    Observable<HttpBean<ResourceBean>> getMyResource(@Query("token") String token);
    //客户提醒
    @GET("customer/Remind")
    Observable<HttpBean<RemindBean>> getRemind(@Query("token") String token);
    //我的举办中会议
    @GET("Meeting/listForNow")
    Observable<HttpBean<HttpPageBean<OwnMeetBean>>> getListForNow(@Query("token") String token, @Query("page") String page);
    //我的待举办会议
    @GET("Meeting/listForFuture")
    Observable<HttpBean<HttpPageBean<OwnMeetBean>>> getListForFuture(@Query("token") String token, @Query("page") String page);
    //我的已举办会议
    @GET("Meeting/listForPast")
    Observable<HttpBean<HttpPageBean<OwnMeetBean>>> getListForPast(@Query("token") String token, @Query("page") String page);
    //正在进行的会议
    @GET("MeetingType/listForNow")
    Observable<HttpBean<HttpPageBean<ApplyMeetBean>>> getMeetForNow(@Query("token") String token, @Query("page") String page);
    //已过期的会议
    @GET("MeetingType/listForPast")
    Observable<HttpBean<HttpPageBean<ApplyMeetBean>>> getMeetForPast(@Query("token") String token, @Query("page") String page);
    //参会客户列表
    @GET("Meeting/customerList")
    Observable<HttpBean<HttpPageBean<MeetClientBean>>> getCustomerList(@QueryMap Map<String,String> map);
    //获取会议详情
    @GET("MeetingType/articleBody")
    Observable<HttpBean<MoreMeetInfoBean>> getArticleBody(@Query("token") String token,@Query("aid") String aid, @Query("id") String id);
    //高级查询（基本信息）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<ClientBean>>>> getSearchOne(@QueryMap Map<String,String> map);
    //高级查询（消费记录）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<SpendBean>>>> getSearchTwo(@QueryMap Map<String,String> map);
    //高级查询（回访记录）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<VisitBean>>>> getSearchThree(@QueryMap Map<String,String> map);
    //高级查询（社会关系）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<RelationBean>>>> getSearchFour(@QueryMap Map<String,String> map);
    //高级查询（赠品记录）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<GiftBean>>>> getSearchFive(@QueryMap Map<String,String> map);
    //高级查询（参会记录）
    @GET("customer/searchA")
    Observable<HttpBean<HttpPageBean<QueryResultBean<MeetBean>>>> getSearchSix(@QueryMap Map<String,String> map);
    //高级查询所需列表
    @GET("customer/listForSearch")
    Observable<HttpBean<InfoQuery>> getListForSearch(@Query("token") String token);
    //即时消息用户联系人
    @GET("user/GetUsers")
    Observable<HttpBean<List<ChatContact>>> GetUsers(@Query("token") String token);
    //参会记录数据
    @GET("Meeting/attendList")
    Observable<HttpBean<AttendBean<Integer>>> GetaAttendList(@Query("token") String token,@Query("meetingid") String meetingid);
    //获取版本情况
    @GET("getVersion")
    Observable<HttpBean<VersionBean>> getVersion();






    //更改密码
    @POST("resetPassword")
    Observable<HttpBean<Object>> resetPassword(@QueryMap Map<String,String> map);
    //登录
    @POST("login")
    Observable<HttpBean<UserBean>> login(@QueryMap Map<String,String> map);
    //注册
    @POST("signUp")
    Observable<HttpBean<Object>> signUp(@QueryMap Map<String,String> map);
    //添加客户
    @POST("customer/add")
    Observable<HttpBean<ClientBean>> addClient(@QueryMap Map<String,String> map);
    //编辑用户资料
    @POST("customer/edit")
    Observable<HttpBean<ClientBean>> editClient(@QueryMap Map<String,String> map);
    //添加消费记录
    @POST("customer/spending/add")
    Observable<HttpBean<SpendBean>> addSpend(@QueryMap Map<String,String> map);
    //编辑消费记录
    @POST("customer/spending/edit")
    Observable<HttpBean<SpendBean>> editSpend(@QueryMap Map<String,String> map);
    //添加回访
    @POST("customer/feedback/add")
    Observable<HttpBean<VisitBean>> addVisit(@QueryMap Map<String,String> map);
    //编辑回访
    @POST("customer/feedback/edit")
    Observable<HttpBean<VisitBean>> editVisit(@QueryMap Map<String,String> map);
    //添加社会关系
    @POST("customer/relation/add")
    Observable<HttpBean<RelationBean>> addRelation(@QueryMap Map<String,String> map);
    //编辑社会关系
    @POST("customer/relation/edit")
    Observable<HttpBean<RelationBean>> editRelation(@QueryMap Map<String,String> mapn);
    //添加赠品
    @POST("customer/gift/add")
    Observable<HttpBean<GiftBean>> addGift(@QueryMap Map<String,String> map);
    //编辑赠品
    @POST("customer/gift/edit")
    Observable<HttpBean<GiftBean>> editGift(@QueryMap Map<String,String> mapn);
    //添加会议记录
    @POST("customer/meeting/add")
    Observable<HttpBean<MeetBean>> addMeet(@QueryMap Map<String,String> map);
    //编辑会议记录
    @POST("customer/meeting/edit")
    Observable<HttpBean<MeetBean>> editMeet(@QueryMap Map<String,String> map);
    //添加产品
    @POST("item/add")
    Observable<HttpBean<Object>> addItem(@QueryMap Map<String,String> map);
    //编辑产品
    @POST("item/edit")
    Observable<HttpBean<Object>> editItem(@QueryMap Map<String,String> map);
    //添加产品类型
    @POST("itemType/add")
    Observable<HttpBean<Object>> addItemType(@Query("token") String token,@Query("name") String name);
    //编辑产品类型
    @POST("itemType/edit")
    Observable<HttpBean<Object>> editItemType(@Query("id") String id,@Query("token") String token,@Query("name") String name);
    //导入通讯录
    @POST("customer/addFast")
    Observable<HttpBean<Object>> addFast(@QueryMap Map<String,String> map);
    //快速新增
    @POST("fastSign")//name password group area CRM APP
    Observable<HttpBean<Object>> fastSign(@QueryMap Map<String,String> map);
    //修改用户
    @POST("user/edit")
    Observable<HttpBean<Object>> editUser(@QueryMap Map<String,String> map);
    //添加权限组
    @POST("group/add")//name area is_super notes token
    Observable<HttpBean<Object>> addGroup(@QueryMap Map<String,String> map);
    //编辑权限组
    @POST("group/edit")//id name area is_super notes token
    Observable<HttpBean<Object>> editGroup(@QueryMap Map<String,String> map);
    //添加地区
    @POST("area/add")
    Observable<HttpBean<KidBean>> addArea(@QueryMap Map<String,String> map);
    //编辑地区
    @POST("area/edit")
    Observable<HttpBean<Object>> editArea(@QueryMap Map<String,String> map);
    //申办会议
    @POST("Meeting/add")
    Observable<HttpBean<Object>> applyMeeting(@QueryMap Map<String,String> map);
    //上传头像
    @POST("customer/eidtHead")
    Observable<HttpBean<String>> eidtHead(@Body RequestBody Body);
    //修改个人头像
    @POST("user/eidtHead")
    Observable<HttpBean<String>> uploadHead(@Body RequestBody Body);
    //添加参会客户
    @POST("Meeting/addCustomer")
    Observable<HttpBean<Object>> addCustomer(@QueryMap Map<String,String> map);
    //批量添加参会客户
    @POST("Meeting/addCustomerAll")
    Observable<HttpBean<BulkImportBean>> addCustomerAll(@QueryMap Map<String,String> map);
    //修改个人基本资料
    @POST("user/editBase")
    Observable<HttpBean<UserBean>> editBase(@QueryMap Map<String,String> map);
    //考勤
    @POST("Meeting/attend")
    Observable<HttpBean<Object>> attend(@QueryMap Map<String,String> map);
    //修改参会客户信息
    @POST("Meeting/customerEdit")
    Observable<HttpBean<Object>> editCustomer(@QueryMap Map<String,String> map);
    //创建群组
    @POST("index.php/tribe/add")
    Observable<HttpBean<TribeBean>> addTribe(@QueryMap Map<String,String> map);
    //编辑群组
    @POST("index.php/tribe/edit")
    Observable<HttpBean<TribeBean>> editTribe(@QueryMap Map<String,String> map);
    //拉人入群
    @POST("index.php/tribe/invite")
    Observable<HttpBean<Object>> invite(@QueryMap Map<String,String> map);
    //公司列表
    @POST("index.php/area/companyList")
    Observable<HttpPageBeanTwo<BaseBean>> getCompanyList(@Query("token") String token, @Query("page") String page);
    //根据公司获取用户列表
    @POST("index.php/user/list")
    Observable<HttpPageBeanTwo<TribeMemberBean>> userList(@Query("token") String token, @Query("area_id") String area_id, @Query("page") String page);
    //获取视频列表
    @POST("index.php/meeting/videoList")
    Observable<HttpPageBeanTwo<VideoBean>> getVideoList(@Query("token") String token, @Query("meeting_id") String meeting_id, @Query("page") String page);
    //获取视频列表
    @POST("index.php/meeting/videoList")
    Observable<HttpPageBeanTwo<VideoBean>> getVideoList(@QueryMap Map<String,String> map);
    //记录播放次数
    @POST("index.php/meeting/setPlayCount")
    Observable<HttpBean<PlayCountBean>> setPlayCount(@Query("token") String token, @Query("video_id") String video_id);
    //获取已播放次数
    @POST("index.php/meeting/getPlayCount")
    Observable<HttpBean<PlayCountBean>> getPlayCount(@Query("token") String token, @Query("video_id") String video_id);


}
