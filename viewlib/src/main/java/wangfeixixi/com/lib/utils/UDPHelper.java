//package wangfeixixi.com.lib.utils;
//
//import android.content.Context;
//import android.net.wifi.WifiInfo;
//import android.net.wifi.WifiManager;
//
//import java.net.DatagramSocket;
//
//public class UDPHelper implements Runnable {
//    private static final String TAG = "UDPHelper";
//    private Context context;
//    private WifiManager.MulticastLock lock;
//    private DatagramSocket socket = null;
//
//    public UDPHelper(Context context, WifiManager wifiManager) {
//        this.context = context;
//        this.lock = wifiManager.createMulticastLock("localWifi");
//        //获取本地IP地址
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        int ipAddress = wifiInfo.getIpAddress();
//        Constants.APP_IP_ADDRESS = TransformUtils.intToIp(ipAddress);
//        TransformUtils.SplitToIp(Constants.APP_IP_ADDRESS);
//    }
//
//    @Override
//    public void run() {
//        StartListen();
//    }
//
//    public void StartListen() {
//        // 接收的字节大小，客户端发送的数据不能超过这个大小
//        byte[] message = new byte[1024];
//        int num = 0;
//        try {
//            // 建立Socket连接
//            if (socket == null) {
//                socket = new DatagramSocket(null);
//                socket.setReuseAddress(true);
//                socket.bind(new InetSocketAddress(Constants.UDP_PORT));
//            }
//            while (num < 10) {//接收10次
//                num++;
//                Thread.sleep(1000);
//                // 准备接收数据
//                lock.acquire();
//                DatagramPacket packet = new DatagramPacket(message, message.length);
//                try {
//                    socket.receive(packet);//接收数据
//                } catch (InterruptedIOException e) {
//                    System.out.println("continue....................");
//                    continue;  //非阻塞循环Operation not permitted
//                }
//                String str_message = new String(packet.getData()).trim();
//                String ip_address = packet.getAddress().getHostAddress().toString();
//                int port_int = packet.getPort();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            Log.e(TAG, "Exception");
//            lock.release();
//            killThread();
//        }
//    }
//
//    public void killThread() {
//        try {
//            if (socket.isConnected() & socket != null) {
//                socket.close();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}