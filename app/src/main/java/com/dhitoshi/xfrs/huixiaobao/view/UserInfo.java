package com.dhitoshi.xfrs.huixiaobao.view;
import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.HeadPopup;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.PersonalEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.HeadClickBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.PictureUtils;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

@RuntimePermissions
public class UserInfo extends BaseView {
    @BindView(R.id.user_nickname)
    EditText userNickname;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_phone)
    EditText userPhone;
    @BindView(R.id.user_email)
    EditText userEmail;
    @BindView(R.id.user_head)
    ImageView userHead;
    private HeadPopup headPopup;
    private static final int TAKE_PICTURE = 520;
    private static final int IMAGE_REQUEST_CODE = 521;
    private File mediaFile;
    private String cameraPath = null;
    private Uri uri;
    private String truename;
    private String phone;
    private String email;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"UserInfo");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("UserInfo");
    }

    private void initViews() {
        initBaseViews();
        setTitle("我的个人信息");
        setRightText("提交");
        userNickname.setText(SharedPreferencesUtil.Obtain(this, "name", "").toString());
        userName.setText(SharedPreferencesUtil.Obtain(this, "truename", "").toString());
        userPhone.setText(SharedPreferencesUtil.Obtain(this, "phone", "").toString());
        userEmail.setText(SharedPreferencesUtil.Obtain(this, "email", "").toString());
        loadHead(SharedPreferencesUtil.Obtain(this, "head", "").toString(),userHead);
    }
    @OnClick({R.id.right_text, R.id.update_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.update_head:
                selectHead();
                break;
        }
    }
    //提交个人资料
    private void commit() {
        truename=userName.getText().toString();
        phone=userPhone.getText().toString();
        email=userEmail.getText().toString();
        if(map==null){
            map=new HashMap<>();
        }
        map.put("id",String.valueOf(SharedPreferencesUtil.Obtain(this,"id",0)));
        map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
        map.put("truename",truename);
        map.put("phone",phone);
        map.put("email",email);
        final LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        editBase(map,dialog);
    }
    private void editBase(final Map<String, String> map, final LoadingDialog dialog ) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editBase(map),new CommonObserver(new HttpResult<HttpBean<UserBean>>() {
            @Override
            public void OnSuccess(HttpBean<UserBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    Toast.makeText(UserInfo.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                    SharedPreferencesUtil.Save(UserInfo.this, "phone", phone);
                    EventBus.getDefault().post(new PersonalEvent(2,truename));
                    SharedPreferencesUtil.Save(UserInfo.this, "email", email);
                    SharedPreferencesUtil.Save(UserInfo.this, "truename", truename);
                    finish();
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(UserInfo.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            editBase(map, dialog);
                        }
                    });
                }else{
                    Toast.makeText(UserInfo.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(UserInfo.this,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void selectHead() {
        if (null == headPopup) {
            headPopup = HeadPopup.Build(this,userHead).init();
            headPopup.show();
            headPopup.addHeadClick(new HeadClickBack() {
                @Override
                public void click(int position) {
                    switch (position){
                        case 0:
                            UserInfoPermissionsDispatcher.ToCamareWithCheck(UserInfo.this);
                            break;
                        case 1:
                            UserInfoPermissionsDispatcher.ToAblumWithCheck(UserInfo.this);
                            break;
                    }
                }
            });
        } else {
            if (!headPopup.isShowing()) {
                headPopup.show();
            } else {
                headPopup.dismisss();
            }
        }
    }
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
     void ToAblum() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void ShowRationaleForAblum(PermissionRequest request){
        request.proceed();
    }
    @NeedsPermission(Manifest.permission.CAMERA)
    void ToCamare() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = getOutputMediaFileUri();
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }
    @OnShowRationale(Manifest.permission.CAMERA)
    void ShowRationaleForTakePhoto(PermissionRequest request){
        request.proceed();
    }
    //上传头像
    private void uploadHead(File file, final String id, final String token) {
        Luban.with(this)
                .load(file)//传人要压缩的图片
                .setCompressListener(new OnCompressListener() { //设置回调
                    @Override
                    public void onStart() {
                    }
                    @Override
                    public void onSuccess(File file) {
                        //TODO 压缩成功后调用，返回压缩后的图片文件
                        MyHttp http=MyHttp.getInstance();
                        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                .addFormDataPart("id", id).addFormDataPart("token", token)
                                .addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("image/png"), file)).build();
                        http.send(http.getHttpService().uploadHead(requestBody),new CommonObserver(new HttpResult<HttpBean<String>>() {
                            @Override
                            public void OnSuccess(HttpBean<String> httpBean) {
                                Toast.makeText(UserInfo.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                                SharedPreferencesUtil.Obtain(UserInfo.this,"head",httpBean.getData());
                                EventBus.getDefault().post(new PersonalEvent(1,httpBean.getData()));
                            }
                            @Override
                            public void OnFail(String msg) {
                                Toast.makeText(UserInfo.this,msg,Toast.LENGTH_SHORT).show();
                            }
                        }));
                    }
                    @Override
                    public void onError(Throwable e) {
                        //TODO 当压缩过去出现问题时调用
                    }
                }).launch();    //启动压缩
    }
    //用于拍照时获取输出的Uri
    protected Uri getOutputMediaFileUri() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Camera");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return FileProvider.getUriForFile(this, "com.dhitoshi.hxb.fileprovider", mediaFile);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case TAKE_PICTURE:
                    if(cameraPath != null){
                        loadHead(cameraPath,userHead);
                    }
                    break;
                case IMAGE_REQUEST_CODE:
                    if (data == null) return;
                    uri = data.getData();
                    int sdkVersion = Integer.valueOf(Build.VERSION.SDK);
                    String path="";
                    if (sdkVersion >= 19) {
                        path = PictureUtils.getPath_above19(this,uri);
                    } else {
                        path = PictureUtils.getFilePath_below19(this, uri);
                    }
                    mediaFile=new File(path);
                    loadHead(path,userHead);
                    break;
            }
            if(getFileSizes(mediaFile) >16777216 ){
                Toast.makeText(this,"图片大小超过16M上传限制，请重新上传", Toast.LENGTH_SHORT).show();
                return;
            }
            uploadHead(mediaFile,String.valueOf(SharedPreferencesUtil.Obtain(this,"id",0))
                    ,SharedPreferencesUtil.Obtain(this,"token","").toString());
        }
    }
    //获取文件大小
    public long getFileSizes(File f) {
        long s = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            s = fis.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UserInfoPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
