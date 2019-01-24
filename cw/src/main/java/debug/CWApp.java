package debug;

import wangfeixixi.com.commen.fram.BaseApp;
import wangfeixixi.com.commen.utils.ServiceUtils;
import wangfeixixi.com.cw.widget.udp.sevice.UDPService;

public class CWApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();
        ServiceUtils.startService(UDPService.class);
    }
}
