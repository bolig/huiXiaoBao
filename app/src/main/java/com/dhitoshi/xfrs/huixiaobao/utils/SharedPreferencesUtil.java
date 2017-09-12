package com.dhitoshi.xfrs.huixiaobao.utils;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by dxs on 2016/10/19.
 */

public class SharedPreferencesUtil {

    private static SharedPreferences getSP(Context c) {
        SharedPreferences sp = c.getSharedPreferences("common_data", 0);
        return sp;
    }
    /**
     * 保存数据到SharedPreferences
     * @param context
     * @param key
     * @param value
     */
    public static void Save(Context context, String key, Object value) {
        SharedPreferences sp = getSP(context);
        SharedPreferences.Editor edit = sp.edit();
        if(value instanceof String) {
            edit.putString(key,value.toString());
        }else if(value instanceof Boolean) {
            edit.putBoolean(key,(Boolean)value);
        }else if(value instanceof Integer) {
            edit.putInt(key,(Integer)value);
        }else if(value instanceof Float) {
            edit.putFloat(key,(Float) value);
        }else if(value instanceof Long) {
            edit.putLong(key,(Long) value);
        }else if(value instanceof Set) {
            edit.putStringSet(key,(Set<String>)value);
        }
        edit.commit();
    }
    /**
     * 从SharedPreferences取出数据
     * @param context
     * @param key
     * @param defaultValue
     */
    public static Object Obtain(Context context, String key, Object defaultValue) {
        SharedPreferences sp = getSP(context);
        if(defaultValue instanceof String) {
            return sp.getString(key,defaultValue.toString());
        }else if(defaultValue instanceof Boolean) {
            return sp.getBoolean(key,(Boolean)defaultValue);
        }else if(defaultValue instanceof Integer) {
            return sp.getInt(key,(Integer)defaultValue);
        }else if(defaultValue instanceof Float) {
            return sp.getFloat(key,(Float)defaultValue);
        }else if(defaultValue instanceof Long) {
            return sp.getLong(key,(Long)defaultValue);
        }else if(defaultValue instanceof Set) {
            return sp.getStringSet(key,(Set)defaultValue);
        }
        return null;
    }
}
