package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddClientPresenter;

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
    private String name;
    private String sex = "";
    private String birthday;
    private String phone;
    private String hobby;
    private String vip;
    private String area;
    private String telPhone;
    private String email;
    private String position;
    private String address;
    private String company;
    private String type;
    private String companyPhone;
    private String companyAddress;
    private String ill;
    private String notes;
    private AddClientPresenter addClientPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("新增客户");
        setRightText("提交");
        addClientPresenter = new AddClientPresenter(this);
        addClientPresenter.getInfoForAdd();
    }
    //添加客户
    @Override
    public void addClient(String result) {
        Log.e("TAG", "添加客户结果:---->>>>" + result);
    }
    //获取添加客户所需列表
    @Override
    public void getInfoForAdd(HttpBean<InfoAddClientBean> httpBean) {
        Log.e("TAG", "既往病史----->>>" + httpBean.getData().getIll());
    }
    //查重
    @Override
    public void checkRepeat(String result) {
    }
    //提交
    private void commit() {
        juge();
        notes = clientNotes.getText().toString();
        ClientBean clientBean = new ClientBean();
        clientBean.setName(name);
        clientBean.setSex(sex);
        clientBean.setBirthday(birthday);
        clientBean.setPhone(phone);
        //clientBean.setHobby();
        clientBean.setVip_id(vip);
        clientBean.setArea(area);
        clientBean.setTelephone(telPhone);
        clientBean.setEmail(email);
        clientBean.setPosition(position);
        clientBean.setAddress(address);
        clientBean.setCompany(company);
        clientBean.setType(type);
        clientBean.setCompany_phone(companyPhone);
        clientBean.setCompany_address(companyAddress);
        // clientBean.setIll();
        clientBean.setNotes(notes);
        clientBean.setEntryman(clientEntryMan.getText().toString());
        addClientPresenter.addClient(clientBean);
    }
    //提交检查
    private void juge() {
        name = clientName.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sex.isEmpty()) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        birthday = clientBirthday.getText().toString();
        if (birthday.isEmpty()) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_SHORT).show();
            return;
        }
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        hobby = clientHobby.getText().toString();
        if (hobby.isEmpty()) {
            Toast.makeText(this, "请选择爱好", Toast.LENGTH_SHORT).show();
            return;
        }
        vip = clientVip.getText().toString();
        if (vip.isEmpty()) {
            Toast.makeText(this, "请填写会员卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        area = clientArea.getText().toString();
        if (area.isEmpty()) {
            Toast.makeText(this, "请选择地区", Toast.LENGTH_SHORT).show();
            return;
        }
        telPhone = clientTelphone.getText().toString();
        if (telPhone.isEmpty()) {
            Toast.makeText(this, "请填写住宅电话", Toast.LENGTH_SHORT).show();
            return;
        }
        email = clientEmail.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(this, "请填写电子邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        position = clientPosition.getText().toString();
        if (position.isEmpty()) {
            Toast.makeText(this, "请选择职位", Toast.LENGTH_SHORT).show();
            return;
        }
        address = clientAddress.getText().toString();
        if (address.isEmpty()) {
            Toast.makeText(this, "请填写通讯地址", Toast.LENGTH_SHORT).show();
            return;
        }
        company = clientCompany.getText().toString();
        if (company.isEmpty()) {
            Toast.makeText(this, "请填写工作单位", Toast.LENGTH_SHORT).show();
            return;
        }
        type = clientType.getText().toString();
        if (type.isEmpty()) {
            Toast.makeText(this, "请选择客户类型", Toast.LENGTH_SHORT).show();
            return;
        }
        companyPhone = clientCompanyPone.getText().toString();
        if (companyPhone.isEmpty()) {
            Toast.makeText(this, "请填写联系电话", Toast.LENGTH_SHORT).show();
            return;
        }
        companyAddress = clientCompanyAddress.getText().toString();
        if (companyAddress.isEmpty()) {
            Toast.makeText(this, "请填写单位地址", Toast.LENGTH_SHORT).show();
            return;
        }
        ill = clientIll.getText().toString();
        if (ill.isEmpty()) {
            Toast.makeText(this, "请选择既往病史", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    //上传头像
    private void uploadHead() {
    }
    //查重
    private void checkRepeat() {
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //拨打电话
    private void call() {
        phone = clientPhone.getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    @OnClick({R.id.right_text, R.id.client_sex, R.id.client_birthday, R.id.client_head, R.id.checkRepeat,
            R.id.call, R.id.client_hobby, R.id.client_area, R.id.client_position, R.id.client_type, R.id.client_ill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.client_sex:
                break;
            case R.id.client_birthday:
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
                break;
            case R.id.client_area:
                break;
            case R.id.client_position:
                break;
            case R.id.client_type:
                break;
            case R.id.client_ill:
                break;
        }
    }
}
