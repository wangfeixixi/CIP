package wangfeixixi.com.commen.fram;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;

import wangfeixixi.com.commen.bugly.SampleApplication;
import wangfeixixi.com.commen.utils.ConfigUtils;
import wangfeixixi.com.commen.utils.crash.CrashHandler;

public class BaseApp extends SampleApplication {

    private static Application instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();
            LeakCanary.install(this);

            //arouter
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        ARouter.init(instance);

//        ThreadUtils.runOnBackThread(new Runnable() {
//            @Override
//            public void run() {
//                VoiceUtil.getInstance().initKey(UIUtils.getContext(), "14678940", "F7aZGFVk9cOQdb9X6nPw2Aog", "2wkI4xprZ8sMmxICY9iZYim704j1qy65");
//            }
//        });

    }

    public static Application getInstance() {
        return instance;
    }
}
