package wangfeixixi.cip.widget.udp;

import wangfeixixi.com.base.test.LogUtils;

public class UDPUtils {
    private static UdpServer udpServer;

    public static UDPServerThread udpserverthread;

    public static void startUDPServer() {
        udpserverthread = new UDPServerThread();
        udpserverthread.start();
    }

    public static void stopUDPServer() {
        if (udpserverthread == null) return;
        udpserverthread.isRunning = false;
        udpserverthread.interrupt();
        try {
            udpserverthread.join();
        } catch (InterruptedException e) {
//            e.printStackTrace();
            LogUtils.d("udp线程中止失败" + e.getMessage());
        }
        udpserverthread = null;
    }

    public static void startServer(UDPResultListener listener) {
        udpServer = new UdpServer();
        udpServer.setResultListener(listener);
        Thread thread = new Thread(udpServer);
        thread.start();
    }

    public static void stopServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //关闭UDP
                if (udpServer != null)
                    udpServer.setUdpLife(false);
            }
        }).start();
    }
}
