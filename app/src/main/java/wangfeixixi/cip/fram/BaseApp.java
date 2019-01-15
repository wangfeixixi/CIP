package wangfeixixi.cip.fram;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

import wangfeixixi.cip.utils.ConfigUtils;
import wangfeixixi.cip.utils.ServiceUtils;
import wangfeixixi.cip.utils.UIUtils;
import wangfeixixi.cip.utils.crash.CrashHandler;
import wangfeixixi.cip.widget.udp.sevice.UDPService;

public class BaseApp extends Application {

    private static Application instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();
            LeakCanary.install(this);
        }
        Bugly.init(UIUtils.getContext(), "7f4076eec5", false);
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
