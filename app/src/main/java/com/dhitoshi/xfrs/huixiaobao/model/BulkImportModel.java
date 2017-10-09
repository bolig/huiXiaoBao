package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BulkImportBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.BulkImportManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import java.util.Map;

import static com.dhitoshi.xfrs.huixiaobao.R.id.smartRefreshLayout;

/**
 * Created by dxs on 2017/9/9.
 */

public class BulkImportModel implements BulkImportManage.Model {
    private Context context;
    public BulkImportModel(Context context) {
        this.context = context;
    }
    @Override
    public void getClientList(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<ClientBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(map),new CommonObserver(new HttpResult<HttpBean<PageBean<ClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<ClientBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if (httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getClientList(map, smartRefreshLayout, callback);
                        }
                    });
                } else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void addCustomerAll(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<BulkImportBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addCustomerAll(map),new CommonObserver(new HttpResult<HttpBean<BulkImportBean>>() {
            @Override
            public void OnSuccess(HttpBean<BulkImportBean> httpBean) {
                dialog.dismiss();
                if (httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            addCustomerAll(map, dialog, callback);
                        }
                    });
                } else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
