package wangfeixixi.cip.udp;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import wangfeixixi.cip.udp.beans.JsonRootBean;

public class UDPServerThread extends Thread {

    public UDPServerThread() {
    }


    public boolean isRunning = true;     //udp生命线程
    private byte[] msgRcv = new byte[1024];

    @Override
    public void run() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ApiConstant.url, ApiConstant.port);
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(inetSocketAddress);
            Log.i("SocketInfo", "UDP服务器已经启动");
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.i("SocketInfo", "UDP监听中");
        DatagramPacket dpRcv = new DatagramPacket(msgRcv, msgRcv.length);
        while (isRunning) {
            try {
                ds.receive(dpRcv);
                String string = new String(dpRcv.getData(), dpRcv.getOffset(), dpRcv.getLength());
                JsonRootBean jsonRootBean = JSON.parseObject(string, JsonRootBean.class);
                EventBus.getDefault().post(jsonRootBean);
                Log.i("SocketInfo", string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ds.close();
        Log.i("SocketInfo", "UDP监听关闭");
    }
}
