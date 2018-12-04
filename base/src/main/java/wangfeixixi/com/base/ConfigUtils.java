package wangfeixixi.com.base;

import android.content.pm.ApplicationInfo;

import java.util.ResourceBundle;

public class ConfigUtils {

    public static boolean isDebug() {
        ResourceBundle bundle = ResourceBundle.getBundle("gradle");//gradle为properties的文件名
        String result = bundle.getString("isDebug");//test_key是properties文件中的key值
        return Boolean.parseBoolean(result);
    }

    //判断当前应用是否是debug状态
    public static boolean isApkInDebug() {
        try {
            ApplicationInfo info = UIUtils.getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
