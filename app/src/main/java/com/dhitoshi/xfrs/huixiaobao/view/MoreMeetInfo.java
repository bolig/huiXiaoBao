package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.webkit.WebView;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MoreMeetInfoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.MoreMeetInfoManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.MoreMeetInfoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MoreMeetInfo extends BaseView implements MoreMeetInfoManage.View {
    @BindView(R.id.more_webview)
    WebView moreWebview;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private MoreMeetInfoPresenter moreMeetInfoPresenter;
    private String aid;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more_meet_info);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        aid=getIntent().getStringExtra("body");
        id=String.valueOf(getIntent().getIntExtra("id",0));
        moreMeetInfoPresenter=new MoreMeetInfoPresenter(this,this);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                moreMeetInfoPresenter.getArticleBody(aid,id,smartRefreshLayout);
            }
        });
        setTitle("加载中...");
    }
    @Override
    public void getArticleBody(HttpBean<MoreMeetInfoBean> httpBean) {
        setTitle(httpBean.getData().getArticle().getTitle());
        moreWebview.loadDataWithBaseURL(null, httpBean.getData().getArticle().getBody(), "text/html", "UTF-8", null);
    }
}
