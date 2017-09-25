package com.dhitoshi.xfrs.huixiaobao.view;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.PhoneInfo;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckContactClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ContactManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ContactAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.ContactPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dxs.dhitoshi.com.index.Binder;
import dxs.dhitoshi.com.index.IndexView;
import dxs.dhitoshi.com.index.PinyinComparator;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class Contact extends BaseView implements ContactManage.View{
    @BindView(R.id.right_text)
    TextView rightText;
    @BindView(R.id.contact_list)
    ListView contactList;
    @BindView(R.id.indexView)
    IndexView indexView;
    @BindView(R.id.contact_area)
    TextView contactArea;
    private ContactAdapter adapter;
    private List<PhoneInfo> phoneInfos;
    private String contactName="";
    private String phone="";
    private String area="";
    private boolean isAllSelect=false;
    private ContactPresenter contactPresenter;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        ButterKnife.bind(this);
        initViews();
        ContactPermissionsDispatcher.readContactsWithCheck(this);
    }

    private void initViews() {
        initBaseViews();
        setTitle("通讯录联系人");
        setRightText("全部");
        indexView = (IndexView) findViewById(R.id.indexView);
        contactPresenter=new ContactPresenter(this,this);
    }

    @NeedsPermission(Manifest.permission.READ_CONTACTS)
    void readContacts() {
        getContacts();
    }

    @OnShowRationale(Manifest.permission.READ_CONTACTS)
    void ShowRationaleFoCall(PermissionRequest request) {
        request.proceed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ContactPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public void getContacts() {
        phoneInfos = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            number=number.replace("-","").replace(" ","");
            PhoneInfo phoneInfo = new PhoneInfo(name, number);
            phoneInfos.add(phoneInfo);
        }
        cursor.close();
        Collections.sort(phoneInfos, new PinyinComparator<PhoneInfo>() {
            @Override
            public int compare(PhoneInfo s1, PhoneInfo s2) {
                return compare(s1.getName(), s2.getName());
            }
        });
        adapter=new ContactAdapter(this, phoneInfos);
        contactList.setAdapter(adapter);
        adapter.addCheckBoxClick(new CheckContactClick() {
            @Override
            public void check(boolean isChecked, String name, String id) {
                if(isChecked){
                    contactName+=name+",";
                    phone+=id+",";
                 }else {
                    contactName=contactName.replace(name+",","") ;
                    phone=phone.replace(id+",","") ;
                }
            }
        });
        Binder binder = new Binder(contactList, indexView) {
            @Override
            public String getListItemKey(int position) {
                return ((PhoneInfo) (contactList.getAdapter().getItem(position))).getName();
            }
        };
        binder.bind();
    }

    @OnClick({R.id.right_text, R.id.contact_area, R.id.contact_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                selectAll();
                break;
            case R.id.contact_area:
                selectArea();
                break;
            case R.id.contact_commit:
                commit();
                break;
        }
    }

    private void selectAll() {
        isAllSelect=!isAllSelect;
        setRightText(isAllSelect?"取消":"全部");
        if(isAllSelect){
            contactName="";
            phone="";
            int size=phoneInfos.size();
            for (int i = 0; i < size; i++) {
                contactName+=phoneInfos.get(i).getName()+",";
                phone+=phoneInfos.get(i).getNumber()+",";
            }
        }else {
            contactName="";
            phone="";
        }
        adapter.setAllSelect(isAllSelect);
        adapter.notifyDataSetChanged();
    }

    private void commit() {
        if(contactName.isEmpty()){
            Toast.makeText(this,"请选择联系人",Toast.LENGTH_SHORT).show();
            return;
        }
        if(area.isEmpty()){
            Toast.makeText(this,"请选择客户地区",Toast.LENGTH_SHORT).show();
            return;
        }
        if(map==null){
            map=new HashMap<>();
        }
        map.put("name",contactName.substring(0,contactName.length()-1));
        map.put("phone",phone.substring(0,phone.length()-1));
        map.put("area",area);
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        contactPresenter.addFast(map,dialog);
    }

    private void selectArea() {
        startActivityForResult(new Intent(this, SelectArea.class), 112);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 200) {
            switch (requestCode) {
                case 112:
                    area = data.getStringExtra("area_id");
                    contactArea.setText(data.getStringExtra("area_name"));
                    contactArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
    @Override
    public void addFast(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ClientEvent(1));
        finish();
    }
}
