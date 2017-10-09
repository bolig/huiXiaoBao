package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendInfoManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AttendInfoPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.DataZoom;
import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.AxisLine;
import com.github.abel533.echarts.axis.AxisTick;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.LineStyle;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendInfo extends BaseView implements AttendInfoManage.View {
    @BindView(R.id.attend_webview)
    WebView attendWebview;
    @BindView(R.id.attend_smartRefreshLayout)
    SmartRefreshLayout attendSmartRefreshLayout;
    private AttendInfoPresenter attendInfoPresenter;
    private String meetingid = "";
    private int height;
    private float density;
    private WebAppInterface appInterface;
    private List<String> times;
    private String start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_info);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AttendInfo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AttendInfo");
    }

    private void initViews() {
        initBaseViews();
        setTitle("考勤数据");
        meetingid = getIntent().getStringExtra("id");
        start = getIntent().getStringExtra("start");
        attendInfoPresenter = new AttendInfoPresenter(this, this);
        initWebView();
        attendSmartRefreshLayout.autoRefresh();
        attendSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                attendInfoPresenter.GetaAttendList(SharedPreferencesUtil.Obtain(AttendInfo.this,"token","").toString(),
                        meetingid,attendSmartRefreshLayout);
            }
        });
        ViewTreeObserver vto = attendWebview.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                attendWebview.getViewTreeObserver().removeOnPreDrawListener(this);
                height = attendWebview.getMeasuredHeight();
                density = getResources().getDisplayMetrics().density;
                return true;
            }
        });
    }
    private void initDates(int days) {
        times = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(start));
        } catch (ParseException e) {
            e.printStackTrace();

        }
        int day=c.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < days; i++) {
            c.set(Calendar.DAY_OF_MONTH,day + i);
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            if(c.get(Calendar.MONTH) +1<10){
                mMonth="0"+mMonth;
            }
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前日份的日期号码
            if(c.get(Calendar.DAY_OF_MONTH)<10){
                mDay="0"+mDay;
            }
            times.add(mMonth + "-" + mDay + " 上午");
            times.add(mMonth + "-" + mDay + " 下午");
        }
    }
    private void initWebView() {
        WebSettings webSettings = attendWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        attendWebview.setWebChromeClient(new ChromeClient());
        attendWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                attendSmartRefreshLayout.finishRefresh();
                Toast.makeText(AttendInfo.this, "网络连接异常，请重试...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                attendSmartRefreshLayout.finishRefresh();
                Toast.makeText(AttendInfo.this, "网络连接异常，请重试...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }
    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                attendSmartRefreshLayout.finishRefresh();
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }
    @Override
    public void GetaAttendList(HttpBean<AttendBean<Integer>> httpBean) {
        appInterface = new WebAppInterface(AttendInfo.this);
        appInterface.setNumbers(httpBean.getData().getList());
        initDates(httpBean.getData().getDays());
        appInterface.setTimes(times);
        attendWebview.addJavascriptInterface(appInterface, "Android");
        attendWebview.loadUrl("file:///android_asset/jsWeb/echart.html");
    }
    class WebAppInterface {
        Context mContext;
        private List<String> times;
        private List<Integer> numbers;

        public WebAppInterface(Context c) {
            mContext = c;
        }

        public void setTimes(List<String> times) {
            this.times = times;
        }

        public void setNumbers(List<Integer> numbers) {
            this.numbers = numbers;
        }
        //获取
        @JavascriptInterface
        public String getLineChartOptions() {
            GsonOption option = markLineChartOptions();
            return option.toString();
        }

        @JavascriptInterface
        public void showToast(String msg) {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public int getHeight() {
            return (int) (height / density);
        }

        @JavascriptInterface
        public GsonOption markLineChartOptions() {
            GsonOption option = new GsonOption();
            option.color("#34B1FF");
            option.tooltip().trigger(Trigger.axis).setAxisPointer(getAxisPointer());
            option.grid(getGrid());
            option.toolbox(getToolbox());
            option.legend(getLegend("考勤数据"));
            option.dataZoom(getDataZoom());
            option.xAxis(getCategoryAxis(times));
            option.yAxis(getValueAxis());
            option.series(getLine(numbers));
            return option;
        }
    }
    //纵坐标
    private ValueAxis getValueAxis() {
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.name("参会人数");
        valueAxis.setMin(0);
        valueAxis.setSplitNumber(10);
        valueAxis.setAxisLine(getAxisLine());
        valueAxis.setAxisTick(getAxisTick());
        return valueAxis;
    }
    //横坐标
    private CategoryAxis getCategoryAxis(List<String> times) {
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.boundaryGap(false);
        categoryAxis.name("时间");
        categoryAxis.setData(times);
        SplitLine splitLine = new SplitLine();
        splitLine.setShow(false);
        categoryAxis.setSplitLine(splitLine);
        categoryAxis.setAxisLine(getAxisLine());
        categoryAxis.setAxisTick(getAxisTick());
        return categoryAxis;
    }
    //坐标轴轴线
    private AxisLine getAxisLine() {
        AxisLine axisLine = new AxisLine();
        axisLine.setLineStyle(getLineStyle("#34B1FF"));
        return axisLine;
    }
    //坐标轴刻度
    private AxisTick getAxisTick() {
        AxisTick axisTick = new AxisTick();
        axisTick.setLineStyle(getLineStyle("#34B1FF"));
        axisTick.setShow(true);
        axisTick.setLength(10);
        return axisTick;
    }
    //线图
    private Line getLine(List<Integer> numbers) {
        Line line = new Line();
        line.name("参会人数");
        line.setData(numbers);
        line.smooth(true);
        line.itemStyle().normal().lineStyle();
        return line;
    }
    //线的样式
    private LineStyle getLineStyle(String color) {
        LineStyle lineStyle = new LineStyle();
        lineStyle.color(color);
        return lineStyle;
    }
    //坐标轴指示器
    private AxisPointer getAxisPointer() {
        AxisPointer pointer = new AxisPointer();
        pointer.setLineStyle(getLineStyle("#34B1FF"));
        return pointer;
    }
    //图例组件
    private Legend getLegend(String title) {
        Legend legend = new Legend();
        legend.show(true);
        legend.data(title);
        legend.selectedMode(false);
        return legend;
    }
    //工具栏 数据视图 切换折线 切换柱状 刷新
    private Toolbox getToolbox() {
        Toolbox toolbox = new Toolbox();
        toolbox.show(true);
        toolbox.itemSize(16);
        toolbox.itemGap(15);
        toolbox.feature(Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore);
        return toolbox;
    }
    //直角坐标系内绘图网格
    private Grid getGrid() {
        Grid grid = new Grid();
        grid.show(true);
        grid.borderWidth(0);
        grid.x("12%");
        grid.y("10%");
        grid.width("78%");
        grid.height("80%");
        return grid;
    }
    //滑动条型数据区域缩放组件
    private DataZoom getDataZoom() {
        DataZoom dataZoom = new DataZoom();
        dataZoom.show(true);
        dataZoom.start(0);
        dataZoom.end(100);
        dataZoom.realtime(true);
        dataZoom.handleColor("#31d2c1");
        return dataZoom;
    }
}
