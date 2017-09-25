package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;
/**
 * Created by dxs on 2017/9/25.
 */
public interface ApplyMeetingManage {
    interface View{
        void applyMeeting(String result);
    }
    interface Model{
        void applyMeeting(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
    }
    interface Prsenter{
        void applyMeeting(Map<String,String> map, LoadingDialog dialog);
    }
}
