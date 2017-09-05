package com.dhitoshi.xfrs.huixiaobao.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dxs on 2017/5/26.
 */
public class PropertyUtil {
    private static Properties urlProps;
    private static String resourceName;
    public static Properties getProperties(Class c) {
        resourceName="/assets/config.properties";
        Properties props = new Properties();
        try {
            InputStream in=c.getResourceAsStream(resourceName);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlProps = props;
        return urlProps;
    }
    public static String Obtain(Class c,String name){
        return  getProperties(c).getProperty(name);
    }
}
