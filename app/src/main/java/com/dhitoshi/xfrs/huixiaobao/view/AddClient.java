package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HobbyBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.IllBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.HeadPopup;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.InfoEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MyDismiss;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ClientTypeAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.PositionAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.SexAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CircleImageView;
import com.dhitoshi.xfrs.huixiaobao.common.PopupArea;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddClient extends BaseView implements AddClientManage.View {
    @BindView(R.id.client_name)
    EditText clientName;
    @BindView(R.id.client_sex)
    TextView clientSex;
    @BindView(R.id.client_birthday)
    TextView clientBirthday;
    @BindView(R.id.client_phone)
    EditText clientPhone;
    @BindView(R.id.client_hobby)
    TextView clientHobby;
    @BindView(R.id.client_vip)
    EditText clientVip;
    @BindView(R.id.client_area)
    TextView clientArea;
    @BindView(R.id.client_telphone)
    EditText clientTelphone;
    @BindView(R.id.client_email)
    EditText clientEmail;
    @BindView(R.id.client_position)
    TextView clientPosition;
    @BindView(R.id.client_address)
    EditText clientAddress;
    @BindView(R.id.client_company)
    EditText clientCompany;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.client_type)
    TextView clientType;
    @BindView(R.id.client_companyPone)
    EditText clientCompanyPone;
    @BindView(R.id.client_companyAddress)
    EditText clientCompanyAddress;
    @BindView(R.id.client_ill)
    TextView clientIll;
    @BindView(R.id.client_notes)
    EditText clientNotes;
    @BindView(R.id.client_entryMan)
    TextView clientEntryMan;
    @BindView(R.id.client_head)
    CircleImageView clientHead;
    private String name;
    private String sex = "";
    private String birthday = "";
    private String phone;
    private String vip;
    private String area = "";
    private String telPhone;
    private String email;
    private String workPosition = "";
    private String address;
    private String company;
    private String type = "";
    private String companyPhone;
    private String companyAddress;
    private String notes;
    private AddClientPresenter addClientPresenter;
    private ClientBean clientBean;
    private String hobby = "";
    private String ill = "";
    private String hobbyName = "";
    private String illName = "";
    private int areaId = 0;
    private ArrayList<HobbyBean> hobbys;
    private ArrayList<IllBean> ills;
    private List<PositionBean> positions;
    private List<CustomerTypeBean> customerTypes;
    private HeadPopup headPopup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        clientBean = getIntent().getParcelableExtra("info");
        setTitle(null == clientBean ? "新增客户" : "编辑客户");
        if (null != clientBean) {
            initClientInfo();
        } else {
            clientEntryMan.setText(SharedPreferencesUtil.Obtain(this, "truename", "").toString());
        }
        setRightText("提交");
        addClientPresenter = new AddClientPresenter(this, this);
        addClientPresenter.getInfoForAdd(SharedPreferencesUtil.Obtain(this,"token","").toString());
    }

    private void initClientInfo() {
        name = clientBean.getName();
        clientName.setText(clientBean.getName());
        sex = clientBean.getSex().equals("男") ? "1" : "0";
        clientSex.setText(clientBean.getSex());
        clientSex.setTextColor(getResources().getColor(R.color.colorPrimary));
        birthday = clientBean.getBirthday();
        clientBirthday.setText(birthday);
        clientBirthday.setTextColor(getResources().getColor(R.color.colorPrimary));
        phone = clientBean.getPhone();
        clientPhone.setText(clientBean.getPhone());
        int hobbySize = clientBean.getHobby().size();
        for (int i = 0; i < hobbySize; i++) {
            hobbyName += clientBean.getHobby().get(i).getHobbyname() + " ";
        }
        clientHobby.setText(hobbyName);
        clientHobby.setTextColor(getResources().getColor(R.color.colorPrimary));
        vip = clientBean.getVip_id();
        clientVip.setText(clientBean.getVip_id());
        area = clientBean.getArea_id();
        clientArea.setText(clientBean.getNotes());
        clientArea.setTextColor(getResources().getColor(R.color.colorPrimary));
        telPhone = clientBean.getTelephone();
        clientTelphone.setText(clientBean.getTelephone());
        email = clientBean.getEmail();
        clientEmail.setText(clientBean.getEmail());
        clientPosition.setText(clientBean.getPosition());
        clientPosition.setTextColor(getResources().getColor(R.color.colorPrimary));
        address = clientBean.getAddress();
        clientAddress.setText(clientBean.getAddress());
        company = clientBean.getCompany();
        clientCompany.setText(clientBean.getCompany());
        clientType.setText(clientBean.getType());
        clientType.setTextColor(getResources().getColor(R.color.colorPrimary));
        companyPhone = clientBean.getCompany_phone();
        clientCompanyPone.setText(clientBean.getCompany_phone());
        companyAddress = clientBean.getCompany_address();
        clientCompanyAddress.setText(clientBean.getCompany_address());
        clientEntryMan.setText(clientBean.getEntryman());
        clientEntryMan.setTextColor(getResources().getColor(R.color.colorPrimary));
        int illSize = clientBean.getIll().size();
        for (int i = 0; i < illSize; i++) {
            illName += clientBean.getIll().get(i).getIllname() + " ";
        }
        clientIll.setText(illName);
        clientIll.setTextColor(getResources().getColor(R.color.colorPrimary));
        clientNotes.setText(clientBean.getNotes());
    }

    //添加客户
    @Override
    public void addClient(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ClientEvent(1));
        finish();
    }

    //编辑客户
    @Override
    public void editClient(HttpBean<ClientBean> httpBean) {
        Toast.makeText(this, httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new InfoEvent(1, httpBean.getData()));
        finish();
    }

    //获取添加客户所需列表
    @Override
    public void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean) {
        hobbys = (ArrayList<HobbyBean>) httpBean.getData().getHobby();
        ills = (ArrayList<IllBean>) httpBean.getData().getIll();
        positions = httpBean.getData().getPosition();
        customerTypes = httpBean.getData().getCustomerType();
        if (clientBean != null) {
            if(clientBean.getHobby()!=null){
                for (int i = 0; i < clientBean.getHobby().size(); i++) {
                    for (int j = 0; j < hobbys.size(); j++) {
                        if (clientBean.getHobby().get(i).getHobbyname().equals(hobbys.get(j).getName())) {
                            if (hobby.isEmpty()) {
                                hobby += String.valueOf(hobbys.get(j).getId());
                            }
                            hobby += "," + String.valueOf(hobbys.get(j).getId());
                        }
                    }
                }
            }
           if(clientBean.getIll()!=null){
               for (int i = 0; i < clientBean.getIll().size(); i++) {
                   for (int j = 0; j < ills.size(); j++) {
                       if (clientBean.getIll().get(i).getIllname().equals(ills.get(j).getName())) {
                           if (ill.isEmpty()) {
                               ill += String.valueOf(ills.get(j).getId());
                           }
                           ill += "," + String.valueOf(ills.get(j).getId());
                       }
                   }
               }
           }
            for (int m = 0; m < positions.size(); m++) {
                if (positions.get(m).getName().equals(clientBean.getPosition())) {
                    workPosition = String.valueOf(positions.get(m).getId());
                }
            }
            for (int k = 0; k < customerTypes.size(); k++) {
                if (customerTypes.get(k).getName().equals(clientBean.getType())) {
                    type = String.valueOf(customerTypes.get(k).getId());
                }
            }
        }
    }

    //查重
    @Override
    public void checkRepeat(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    //提交
    private void commit() {
        if (juge()) {
            notes = clientNotes.getText().toString();
            AddClientBean addClientBean = new AddClientBean();
            addClientBean.setName(name);
            addClientBean.setSex(sex);
            addClientBean.setBirthday(birthday);
            addClientBean.setPhone(phone);
            addClientBean.setHobby(hobby);
            addClientBean.setVip_id(vip);
            addClientBean.setArea(area);
            addClientBean.setTelephone(telPhone);
            addClientBean.setEmail(email);
            addClientBean.setPosition(workPosition);
            addClientBean.setAddress(address);
            addClientBean.setCompany(company);
            addClientBean.setType(type);
            addClientBean.setCompany_phone(companyPhone);
            addClientBean.setCompany_address(companyAddress);
            addClientBean.setIll(ill);
            addClientBean.setNotes(notes);
            addClientBean.setEntryman(clientEntryMan.getText().toString());
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
            if (null == clientBean) {
                addClientPresenter.addClient(token,addClientBean, dialog);
            } else {
                addClientBean.setId(String.valueOf(clientBean.getId()));
                addClientPresenter.editClient(token,addClientBean, dialog);
            }
        }
    }

    //提交检查
    private boolean juge() {
        name = clientName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sex.isEmpty()) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (birthday.isEmpty()) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_SHORT).show();
            return false;
        }
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (hobby.isEmpty()) {
            Toast.makeText(this, "请选择爱好", Toast.LENGTH_SHORT).show();
            return false;
        }
        vip = clientVip.getText().toString();
        if (vip.isEmpty()) {
            Toast.makeText(this, "请填写会员卡号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (area.isEmpty()) {
            Toast.makeText(this, "请选择部门", Toast.LENGTH_SHORT).show();
            return false;
        }
        telPhone = clientTelphone.getText().toString();
        if (telPhone.isEmpty()) {
            Toast.makeText(this, "请填写住宅电话", Toast.LENGTH_SHORT).show();
            return false;
        }
        email = clientEmail.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "请填写电子邮箱", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (workPosition.isEmpty()) {
            Toast.makeText(this, "请选择职位", Toast.LENGTH_SHORT).show();
            return false;
        }
        address = clientAddress.getText().toString();
        if (address.isEmpty()) {
            Toast.makeText(this, "请填写通讯地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        company = clientCompany.getText().toString();
        if (company.isEmpty()) {
            Toast.makeText(this, "请填写工作单位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (type.isEmpty()) {
            Toast.makeText(this, "请选择客户类型", Toast.LENGTH_SHORT).show();
            return false;
        }
        companyPhone = clientCompanyPone.getText().toString();
        if (companyPhone.isEmpty()) {
            Toast.makeText(this, "请填写联系电话", Toast.LENGTH_SHORT).show();
            return false;
        }
        companyAddress = clientCompanyAddress.getText().toString();
        if (companyAddress.isEmpty()) {
            Toast.makeText(this, "请填写单位地址", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ill.isEmpty()) {
            Toast.makeText(this, "请选择既往病史", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //上传头像
    private void uploadHead() {
        if (null == headPopup) {
            headPopup = HeadPopup.Build(this,clientHead).init();
            headPopup.show();
        } else {
            if (!headPopup.isShowing()) {
                headPopup.show();
            } else {
                headPopup.dismisss();
            }
        }
    }

    //查重
    private void checkRepeat() {
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("查重中");
        dialog.show();
        String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
        if (null == clientBean) {
            addClientPresenter.checkRepeat(token,dialog, String.valueOf(areaId), phone, "");
        } else {
            addClientPresenter.checkRepeat(token,dialog, "", phone, String.valueOf(clientBean.getId()));
        }

    }

    //拨打电话
    private void call() {
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phone));
        startActivity(phoneIntent);
    }

    @OnClick({R.id.right_text, R.id.client_sex, R.id.client_birthday, R.id.client_head, R.id.checkRepeat,
            R.id.call, R.id.client_hobby, R.id.client_area, R.id.client_position, R.id.client_type, R.id.client_ill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.client_sex:
                selectSex();
                break;
            case R.id.client_birthday:
                selectBrithday();
                break;
            case R.id.client_head:
                uploadHead();
                break;
            case R.id.checkRepeat:
                checkRepeat();
                break;
            case R.id.call:
                call();
                break;
            case R.id.client_hobby:
                selectHobby();
                break;
            case R.id.client_area:
                selectArea();
                break;
            case R.id.client_position:
                selectPosition();
                break;
            case R.id.client_type:
                selectType();
                break;
            case R.id.client_ill:
                selectIll();
                break;
        }
    }

    //选择出生日期
    private void selectBrithday() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择出生日期").setTime(clientBirthday.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                birthday = date;
                clientBirthday.setText(date);
                clientBirthday.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }

    //选择爱好
    private void selectHobby() {
        startActivityForResult(new Intent(this, Select.class).putParcelableArrayListExtra("list", hobbys)
                .putExtra("type", 1).putExtra("select", clientHobby.getText().toString()), 1);
    }

    //选择地区
    private void selectArea() {
        startActivityForResult(new Intent(this, SelectArea.class), 0);
    }

    //选择疾病
    private void selectIll() {
        startActivityForResult(new Intent(this, Select.class).putParcelableArrayListExtra("list", ills)
                .putExtra("type", 2).putExtra("select", clientIll.getText().toString()), 2);
    }

    //选择客户类型
    private void selectType() {
        ClientTypeAdapter adapter = new ClientTypeAdapter(customerTypes, this, clientType.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择类型").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<CustomerTypeBean>() {
            @Override
            public void onItemClick(View view, CustomerTypeBean customerTypeBean, int position) {
                clientType.setText(customerTypeBean.getName());
                type = String.valueOf(customerTypeBean.getId());
                clientType.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }

    //选择职位
    private void selectPosition() {
        PositionAdapter adapter = new PositionAdapter(positions, this, clientPosition.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择职位").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<PositionBean>() {
            @Override
            public void onItemClick(View view, PositionBean positionBean, int position) {
                clientPosition.setText(positionBean.getName());
                workPosition = String.valueOf(positionBean.getId());
                clientPosition.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }

    //选择性别
    private void selectSex() {
        List<SexBean> sexBeens = new ArrayList<>();
        sexBeens.add(new SexBean("0", "男"));
        sexBeens.add(new SexBean("1", "女"));
        SexAdapter adapter = new SexAdapter(sexBeens, this, clientSex.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择性别").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                clientSex.setText(sexBean.getName());
                sex = sexBean.getId();
                clientSex.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 100) {
            switch (requestCode) {
                case 1:
                    hobby = data.getStringExtra("ids");
                    clientHobby.setText(data.getStringExtra("names"));
                    clientHobby.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 2:
                    ill = data.getStringExtra("ids");
                    clientIll.setText(data.getStringExtra("names"));
                    clientIll.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        } else if (resultCode == 200) {
            switch (requestCode) {
                case 0:
                    area = data.getStringExtra("area_id");
                    clientArea.setText(data.getStringExtra("area_name"));
                    clientArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
