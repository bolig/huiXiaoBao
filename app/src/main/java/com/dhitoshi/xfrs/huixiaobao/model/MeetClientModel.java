package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetClientManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import java.util.Map;
/**
 * Created by dxs on 2017/9/26.
 */
public class MeetClientModel implements MeetClientManage.Model{
    private Context context;
    public MeetClientModel(Context context) {
        this.context = context;
    }
    @Override
    public void getCustomerList(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<MeetClientBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getCustomerList(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<MeetClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<MeetClientBean>> httpBean) {
                smartRefreshLayout.finishLoadmore();
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getCustomerList(map,smartRefreshLayout,callback);
                        }
                    });
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishLoadmore();
                smartRefreshLayout.finishRefresh();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
