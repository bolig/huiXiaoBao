package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HobbyBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.IllBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.HobbyAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.IllAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.SelectAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class Select extends BaseView {
    @BindView(R.id.select_recyclerView)
    RecyclerView selectRecyclerView;
    private ArrayList content;
    private int type;
    private String ids="";
    private String names="";
    private String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        type = getIntent().getIntExtra("type", 0);
        select=getIntent().getStringExtra("select");
        initTitle();
        content = getIntent().getParcelableArrayListExtra("list");
        selectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        selectRecyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        initData();
    }
    private void initTitle() {
        switch (type) {
            case 1:
                setTitle("选择爱好");
                setRightText("确定");
                break;
            case 2:
                setTitle("选择既往病史");
                setRightText("确定");
                break;
            case 3:
                setTitle("选择购买产品");
                break;
            case 4:
                setTitle("选择销售员");
                break;
        }
    }
    private void initData() {
        switch (type){
            case 1:
                initHobby();
                break;
            case 2:
                initIll();
                break;
            case 3:
                break;
            case 4:
                radioSelect();
                break;
        }
    }
    //选择购买产品 销售员(单选)
    private void radioSelect() {
        SelectAdapter adapter=new SelectAdapter(content,this,select);
        selectRecyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                Intent it=new Intent();
                it.putExtra("id",baseBean.getId());
                it.putExtra("name",baseBean.getName());
                setResult(200,it);
                finish();
            }
        });
        adapter.addCheckBoxClick(new CheckBoxClick() {
            @Override
            public void check(boolean isChecked, String name, int id) {
            Intent it=new Intent();
            it.putExtra("id",id);
            it.putExtra("name",name);
            setResult(200,it);
            finish();
            }
        });
    }

    //既往病史数据处理
    private void initIll() {
        IllAdapter adapter=new IllAdapter(content,this,select);
        selectRecyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<IllBean>() {
            @Override
            public void onItemClick(View view, IllBean illBean, int position) {
                CheckBox checkBox= (CheckBox) view.findViewById(R.id.multiple_select);
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        adapter.addCheckBoxClick(new CheckBoxClick() {
            @Override
            public void check(boolean isChecked, String name, int id) {
                if(isChecked){
                    names+=name+" ";
                    ids+=id+",";
                }else{
                    names=names.replace(name+" ","");
                    ids=ids.replace(id+",","");
                }
            }
        });
    }
    //爱好数据处理
    private void initHobby() {
        HobbyAdapter adapter=new HobbyAdapter(content,this,select);
        selectRecyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<HobbyBean>() {
            @Override
            public void onItemClick(View view, HobbyBean hobbyBean, int position) {
                CheckBox checkBox= (CheckBox) view.findViewById(R.id.multiple_select);
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        adapter.addCheckBoxClick(new CheckBoxClick() {
            @Override
            public void check(boolean isChecked, String name, int id) {
                if(isChecked){
                    names+=name+" ";
                    ids+=id+",";
                }else{
                    ids=ids.replace(id+",","");
                   names= names.replace(name+" ","");

                }
            }
        });
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        if(ids.isEmpty()){
            Toast.makeText(this,"您还未选择呢！请选择",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent it=new Intent();
        it.putExtra("ids",ids);
        it.putExtra("names",names);
        setResult(100,it);
        finish();
    }
}
