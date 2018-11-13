package wangfeixixi.cip.fram;

import android.app.Application;

import wangfeixixi.com.bdvoice.VoiceUtil;

/**
 * Created by xuany on 2018/10/26.
 */

public class BaseApp extends Application {
    private static BaseApp instance;

    public BaseApp() {
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        initData();
    }

    private void initData() {
        VoiceUtil.initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");
    }

    public static Application getInstance() {
        return instance;
    }
}
