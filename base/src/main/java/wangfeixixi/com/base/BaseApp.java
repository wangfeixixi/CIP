package wangfeixixi.com.base;

import com.tencent.bugly.Bugly;

import wangfeixixi.com.base.crash.CrashHandler;
import wangfeixixi.com.base.mvvm.base.BaseApplication;

public class BaseApp extends BaseApplication {

    public void onCreate() {
        super.onCreate();

        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();

        }
        Bugly.init(UIUtils.getContext(), "7f4076eec5", false);

    }
}
