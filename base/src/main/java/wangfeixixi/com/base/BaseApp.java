package wangfeixixi.com.base;

import me.goldze.mvvmhabit.base.BaseApplication;
import wangfeixixi.com.base.test.CrashHandler;

public class BaseApp extends BaseApplication {

    public void onCreate() {
        super.onCreate();

        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();

        }
    }
}
