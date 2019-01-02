//package wangfeixixi.cip.widget.udp.server;
//
//import android.util.Log;
//
//import com.alibaba.fastjson.JSON;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetSocketAddress;
//import java.net.SocketException;
//
//import wangfeixixi.cip.beans.JsonRootBean;
//import wangfeixixi.cip.widget.udp.UDPConfig;
//import wangfeixixi.com.base.crash.LogUtils;
//
//public class UDPServerThread extends Thread {
//
//    public UDPServerThread() {
//    }
//
//
//    public boolean isRunning = true;     //udp生命线程
//    private byte[] msgRcv = new byte[1024];
//
//    @Override
//    public void run() {
//        InetSocketAddress inetSocketAddress = new InetSocketAddress(UDPConfig.url, UDPConfig.port);
//        DatagramSocket ds = null;
//        try {
//            ds = new DatagramSocket(inetSocketAddress);
//            LogUtils.d("UDP服务器已经启动");
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        LogUtils.d("UDP监听中");
//        DatagramPacket dpRcv = new DatagramPacket(msgRcv, msgRcv.length);
//        while (isRunning) {
//            try {
//                ds.receive(dpRcv);
//                String string = new String(dpRcv.getData(), dpRcv.getOffset(), dpRcv.getLength());
//                EventBus.getDefault().post(JSON.parseObject(string, JsonRootBean.class));
//                Log.i("SocketInfo", string);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        ds.close();
//        LogUtils.d("UDP监听关闭");
//    }
//}
