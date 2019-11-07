package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.PlayCountBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingVideoManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.VideoAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.MeetingVideoPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class MeetingVideo extends BaseView implements MeetingVideoManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int id;
    private MeetingVideoPresenter presenter;
    private int page=1;
    private List<VideoBean> videos;
    private VideoAdapter adapter;
    private VideoBean video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_video);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        initBaseViews();
        setTitle("视频列表");
        id=getIntent().getIntExtra("id",0);
        presenter=new MeetingVideoPresenter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                videos.removeAll(videos);
                String token= SharedPreferencesUtil.Obtain(MeetingVideo.this,"token","").toString();
                page=1;
                presenter.getVideoList(token,String.valueOf(id),String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                String token= SharedPreferencesUtil.Obtain(MeetingVideo.this,"token","").toString();
                ++page;
                presenter.getVideoList(token,String.valueOf(id),String.valueOf(page),smartRefreshLayout);
            }
        });
        videos=new ArrayList<>();
    }
    @Override
    public void getVideoList(HttpPageBeanTwo<VideoBean> httpPageBeanTwo) {
        videos.addAll(httpPageBeanTwo.getData());
        int size = videos.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0? View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new VideoAdapter(videos, MeetingVideo.this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<VideoBean>() {
                @Override
                public void onItemClick(View view, VideoBean videoBean, int position) {
                String token= SharedPreferencesUtil.Obtain(MeetingVideo.this,"token","").toString();
                video=videoBean;
                presenter.getPlayCount(token,String.valueOf(videoBean.getId()));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setPlayCount(PlayCountBean bean) {

    }
    @Override
    public void getPlayCount(PlayCountBean bean) {
        if(bean.getIs_play()==1){
            verifyAlert(video.getPassword(),video.getVideo_url());
            String token= SharedPreferencesUtil.Obtain(MeetingVideo.this,"token","").toString();
            presenter.setPlayCount(token,String.valueOf(video.getId()));
        }else{
            Toast.makeText(this,"播放次数已经超过最大次数",Toast.LENGTH_SHORT).show();
        }
    }
    private void verifyAlert(final String s, final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请输入访问密码");
        final EditText psw=new EditText(this);
        builder.setView(psw);
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog visit=builder.create();
        visit.show();
        visit.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = psw.getText().toString().trim();
                if(password.equals(s)){
                    Uri uri = Uri.parse(url);
                    //调用系统自带的播放器
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "video/*");
                    startActivity(intent);
                    visit.dismiss();
                }else{
                    Toast.makeText(MeetingVideo.this,"访问密码有误!请重新输入...",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
