package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.GiftEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetingEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddGiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.DialogAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddGiftPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddGift extends BaseView implements AddGiftManage.View{
    @BindView(R.id.gift_date)
    TextView giftDate;
    @BindView(R.id.gift_name)
    TextView giftName;
    @BindView(R.id.gift_number)
    EditText giftNumber;
    @BindView(R.id.gift_address)
    TextView giftAddress;
    @BindView(R.id.gift_salesman)
    TextView giftSalesman;
    @BindView(R.id.gift_notes)
    EditText giftNotes;
    private String createtime="";
    private String gift="";
    private String num="";
    private String saleaddress="";
    private String salesman="";
    private String notes;
    private int userId;
    private GiftBean giftBean;
    private ArrayList<GiftBean> item;
    private List<BaseBean> addresses;
    private ArrayList<BaseBean> salesmen;
    private AddGiftPresenter addGiftPresenter;
    private Map<String,String> map;
    private int type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_gift);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        userId=getIntent().getIntExtra("id",0);
        type=getIntent().getIntExtra("type",0);
        giftBean=getIntent().getParcelableExtra("gift");
        setTitle(null==giftBean?"新增赠品记录":"编辑赠品记录");
        if(null!=giftBean){
            initGiftInfo();
        }
        setRightText("提交");
        addGiftPresenter=new AddGiftPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        addGiftPresenter.getListForGift(token);
    }
    private void initGiftInfo() {
        createtime=giftBean.getCreatetime();
        giftDate.setText(giftBean.getCreatetime());
        giftDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        giftName.setText(giftBean.getGift());
        giftName.setTextColor(getResources().getColor(R.color.colorPrimary));
        num=giftBean.getNum();
        giftNumber.setText(giftBean.getNum());
        giftNumber.setTextColor(getResources().getColor(R.color.colorPrimary));
        giftAddress.setText(giftBean.getSaleaddress());
        giftAddress.setTextColor(getResources().getColor(R.color.colorPrimary));
        giftSalesman.setText(giftBean.getSalesman());
        giftSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
        giftNotes.setText(giftBean.getNotes());
        giftNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    @OnClick({R.id.right_text, R.id.gift_date, R.id.gift_name, R.id.gift_address, R.id.gift_salesman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.gift_date:
                selectGiftDate();
                break;
            case R.id.gift_name:
                selectGiftName();
                break;
            case R.id.gift_address:
                selectGiftAddress();
                break;
            case R.id.gift_salesman:
                selectGiftSalesman();
                break;
        }
    }
    private void commit() {
        if(juge()){
            if(map==null){
                map=new HashMap<>();
            }
            if(!createtime.isEmpty()){
                map.put("createtime",createtime);
            }
            if(!saleaddress.isEmpty()){
                map.put("saleaddress",saleaddress);
            }
            if(!gift.isEmpty()){
                map.put("gift",gift);
            }
            if(!num.isEmpty()){
                map.put("num",num);
            }
            if(!salesman.isEmpty()){
                map.put("salesman",salesman);
            }
            if(!notes.isEmpty()){
                map.put("notes",notes);
            }
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
            map.put("token",token);
            if(null==giftBean){
                map.put("userid",String.valueOf(userId));
                addGiftPresenter.addGift(map,dialog);
            }else{
                map.put("id",String.valueOf(giftBean.getId()));
                addGiftPresenter.editGift(map,dialog);
            }
        }
    }
    private boolean juge() {
        if(createtime.isEmpty()){
            Toast.makeText(this," 请选择赠送日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        num=giftName.getText().toString();
        notes=giftNotes.getText().toString();
        return true;
    }
    //选择赠送日期
    private void selectGiftDate() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择赠送日期").setTime(giftDate.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime=date;
                giftDate.setText(date);
                giftDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择赠送名称
    private void selectGiftName() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",item)
                .putExtra("type",7).putExtra("select",giftName.getText().toString()),7);
    }
    //选择赠送地点
    private void selectGiftAddress() {
        DialogAdapter adapter=new DialogAdapter(addresses,this,giftAddress.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择赠送地点").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                giftAddress.setText(baseBean.getName());
                saleaddress=String.valueOf(baseBean.getId());
                giftAddress.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择经手人
    private void selectGiftSalesman() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",salesmen)
                .putExtra("type",8).putExtra("select",giftSalesman.getText().toString()),8);
    }
    @Override
    public void addGift(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new GiftEvent(1));
        finish();
    }
    @Override
    public void editGift(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        if(type==1){
            EventBus.getDefault().post(new QueryResultEvent(1));
        }else{
            EventBus.getDefault().post(new GiftEvent(1));
        }
        finish();
    }
    @Override
    public void getListForGift(HttpBean<InfoAddGiftBean> httpBean) {
        item= (ArrayList<GiftBean>) httpBean.getData().getGift();
        addresses=httpBean.getData().getSaleaddress();
        salesmen= (ArrayList<BaseBean>) httpBean.getData().getSalesman();
        if(giftBean!=null){
            for (int j = 0; j < addresses.size(); j++) {
                if(addresses.get(j).getName().equals(giftBean.getSaleaddress())){
                    saleaddress=String.valueOf(addresses.get(j).getId());
                }
            }
            for (int k = 0; k < salesmen.size(); k++) {
                if(salesmen.get(k).getName().equals(giftBean.getSalesman())){
                    salesman=String.valueOf(salesmen.get(k).getId());
                }
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==200){
            switch (requestCode){
                case 7:
                    gift=data.getStringExtra("id");
                    giftName.setText(data.getStringExtra("name"));
                    giftName.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 8:
                    salesman=data.getStringExtra("id");
                    giftSalesman.setText(data.getStringExtra("name"));
                    giftSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
