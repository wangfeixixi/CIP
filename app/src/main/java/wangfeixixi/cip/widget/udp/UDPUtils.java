package wangfeixixi.cip.widget.udp;

import wangfeixixi.com.base.test.LogUtils;

public class UDPUtils {

    private static UDPClientThread thread;
    private static UdpServer udpServer;

    public static void start() {
        thread = new UDPClientThread();
        thread.start();
    }

    public static void stop() {
        if (thread == null) return;
        thread.isRunning = false;
        try {
            thread.interrupt();
            thread.join();
        } catch (InterruptedException e) {
//            e.printStackTrace();

            LogUtils.d("udp线程中止失败" + e.getMessage());
        }
        thread = null;
    }

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

    public static void startServer() {
        udpServer = new UdpServer(ApiConstant.url, ApiConstant.port);
        Thread thread = new Thread(udpServer);
        thread.start();
    }

    public static void stopServer() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //关闭UDP
                udpServer.setUdpLife(false);
            }
        });
        thread.start();
    }
}
