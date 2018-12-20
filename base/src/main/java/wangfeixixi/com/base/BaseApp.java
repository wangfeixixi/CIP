package wangfeixixi.com.base;

import wangfeixixi.com.base.mvvm.base.BaseApplication;
import wangfeixixi.com.base.crash.CrashHandler;

public class BaseApp extends BaseApplication {

    public void onCreate() {
        super.onCreate();

        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();

        }
    }
}
