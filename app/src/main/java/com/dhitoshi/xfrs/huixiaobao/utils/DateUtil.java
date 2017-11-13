package com.dhitoshi.xfrs.huixiaobao.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtil {

    //string to date
    public static Date stringToDate(String str, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    //date to string
    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }
    //long to date
    public static Date longToDate(long currentTime, String format) {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生成一个date类型的时间
        String sDateTime = dateToString(dateOld, format); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, format); // 把String类型转换为Date类型
        return date;
    }
    // date to long
    public static long dateToLong(Date date)
    {
        return  date.getTime();
    }
    //long to string
    public static String longToString(long currentTime, String format) {
        Date date = longToDate(currentTime, format); // long类型转成Date类型
        String strTime = dateToString(date, format); // date类型转成String
        return strTime;
    }
    //倒计时
    public static String longToString(long currentTime) {
        String hour;
        String minute;
        String second;
        currentTime/=1000;
        hour=currentTime/3600<10?"0"+currentTime/3600:""+currentTime/3600;
        minute=(currentTime%3600)/60<10?"0"+(currentTime%3600)/60:""+(currentTime%3600)/60;
        second=currentTime%3600%60<10?"0"+currentTime%3600%60:""+currentTime%3600%60;
        return hour+":"+minute+":"+second;
    }
    //string to long
    public static long stringToLong(String str, String format) {
        Date date = stringToDate(str, format); // String类型转成date类型
        if (date == null) {
            return 0;
        }
        else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    //将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
    public static String convertTimeToFormat(long timeStamp) {
        long time = (System.currentTimeMillis() - timeStamp) / 1000;
        if (time < 60 && time >= -60) {
            return "刚刚";
        }
        else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        }
        else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        }
        else if(time >= 3600 * 24 && time <= 3600 * 24 * 7) {
            return time / (3600 * 24) + "天前";
        }
        else {
            return "10天前";
        }
    }
}
