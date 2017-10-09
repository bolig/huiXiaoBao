package com.dhitoshi.xfrs.huixiaobao.utils;
import android.app.Activity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * Created by Administrator on 2016/12/6.
 */

public class ActivityManager {
    private static Map<String, Activity> destoryMap = new HashMap();
    private ActivityManager() {
    }
    //添加到销毁队列
    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }
    //销毁指定Activity
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            if (key.equals(activityName))
                destoryMap.get(key).finish();
        }
        destoryMap.remove(activityName);
    }
    //销毁所有Activity
    public static void destoryAllActivity() {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
            destoryMap.remove(key);
        }
    }
    //销毁其他所有Activity
    public static void destoryOtherActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            if (!key.equals(activityName)){
                destoryMap.get(key).finish();
            }
        }
    }
}
