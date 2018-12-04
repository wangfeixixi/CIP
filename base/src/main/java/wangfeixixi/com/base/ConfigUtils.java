package wangfeixixi.com.base;

import java.util.ResourceBundle;

public class ConfigUtils {

    public static boolean isDebug() {
        ResourceBundle bundle = ResourceBundle.getBundle("gradle");//gradle为properties的文件名
        String result = bundle.getString("isDebug");//test_key是properties文件中的key值
        return Boolean.parseBoolean(result);
    }
}
