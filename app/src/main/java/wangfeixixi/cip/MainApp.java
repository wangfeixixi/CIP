package wangfeixixi.cip;

import wangfeixixi.com.commen.fram.BaseApp;
import wangfeixixi.com.commen.utils.ServiceUtils;
import wangfeixixi.com.cw.widget.udp.sevice.UDPService;

public class MainApp extends BaseApp {
    @Override
    public void onCreate() {
        super.onCreate();

        ServiceUtils.startService(UDPService.class);
    }

}
