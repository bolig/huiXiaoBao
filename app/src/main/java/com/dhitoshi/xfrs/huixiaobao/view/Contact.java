package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.R;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dxs.dhitoshi.com.index.IndexView;

public class Contact extends BaseView {
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.contact_list)
    ListView contactList;
    @BindView(R.id.indexView)
    IndexView indexView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        setTitle("通讯录联系人");
        setRightText("全部");
//        List<Item> items = getData();
//        Collections.sort(items, new PinyinComparator<Item>() {
//            @Override
//            public int compare(Item s1, Item s2) {
//                return compare(s1.getUserName(), s2.getUserName());
//            }
//        });
//
//        listView.setAdapter(new MyAdapter(this, items));
//
//        indexView = (IndexView) findViewById(R.id.indexView);
//
//        Binder binder = new Binder(listView, indexView) {
//            @Override
//            public String getListItemKey(int position) {
//                return ((Item)(listView.getAdapter().getItem(position))).getUserName();
//            }
//        };
//        binder.bind();
    }
}
