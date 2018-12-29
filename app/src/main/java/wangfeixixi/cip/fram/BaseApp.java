package wangfeixixi.cip.fram;

import com.tencent.bugly.Bugly;

import wangfeixixi.cip.widget.udp.sevice.UDPService;
import wangfeixixi.com.base.ConfigUtils;
import wangfeixixi.com.base.ServiceUtils;
import wangfeixixi.com.base.UIUtils;
import wangfeixixi.com.base.crash.CrashHandler;
import wangfeixixi.com.base.mvvm.base.BaseApplication;

public class BaseApp extends BaseApplication {

    public void onCreate() {
        super.onCreate();

        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();

        }
        ServiceUtils.startService(UDPService.class);
        Bugly.init(UIUtils.getContext(), "7f4076eec5", false);

    }
}
