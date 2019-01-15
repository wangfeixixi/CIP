package wangfeixixi.cip.widget.udp.sevice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import wangfeixixi.cip.utils.crash.LogUtils;


public class UDPService extends Service {
    //    @androidx.annotation.Nullable
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startUDP();
        return super.onStartCommand(intent, flags, startId);
    }

    private UdpInnerServerRunable udpServer;

    private void startUDP() {
        udpServer = new UdpInnerServerRunable();
        Thread thread = new Thread(udpServer);
        thread.start();
    }

    public void stopUDP() {
        LogUtils.d("停止");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //关闭UDP
                if (udpServer != null)
                    udpServer.setUdpLife(false);
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        stopUDP();
    }
}
