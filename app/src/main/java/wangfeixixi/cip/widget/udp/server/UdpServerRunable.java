package wangfeixixi.cip.widget.udp.server;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import wangfeixixi.cip.beans.JsonRootBean;
import wangfeixixi.cip.widget.udp.UDPConfig;
import wangfeixixi.com.base.crash.LogUtils;

public class UdpServerRunable implements Runnable {
    private DatagramPacket dpRcv = null, dpSend = null;
    private static DatagramSocket ds = null;
    private InetSocketAddress inetSocketAddress = null;
    private byte[] msgRcv = new byte[1024];
    private boolean udpLife = true;     //udp生命线程
    private boolean udpLifeOver = true; //生命结束标志，false为结束


    public UdpServerRunable() {

    }

    private void SetSoTime(int ms) throws SocketException {
        ds.setSoTimeout(ms);
    }

    //返回udp生命线程因子是否存活
    public boolean isUdpLife() {
        return udpLife;
    }

    //返回具体线程生命信息是否完结
    public boolean getLifeMsg() {
        return udpLifeOver;
    }

    //更改UDP生命线程因子
    public void setUdpLife(boolean b) {
        udpLife = b;
    }

    public void Send(String sendStr) throws IOException {
        Log.i("SocketInfo", "客户端IP：" + dpRcv.getAddress().getHostAddress() + "客户端Port:" + dpRcv.getPort());
        dpSend = new DatagramPacket(sendStr.getBytes(), sendStr.getBytes().length, dpRcv.getAddress(), dpRcv.getPort());
        ds.send(dpSend);
    }

    public IUDPResultListener listener;

    public void setResultListener(IUDPResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        inetSocketAddress = new InetSocketAddress(UDPConfig.url, UDPConfig.port);
        try {
            ds = new DatagramSocket(inetSocketAddress);
            LogUtils.d("UDP服务器已经启动");
//            SetSoTime(10000);
            //设置超时，不需要可以删除
        } catch (SocketException e) {
            e.printStackTrace();
        }

        dpRcv = new DatagramPacket(msgRcv, msgRcv.length);
        LogUtils.d("UDP监听中");
        while (udpLife) {
            try {
                ds.receive(dpRcv);
                String string = new String(dpRcv.getData(), dpRcv.getOffset(), dpRcv.getLength());
                if (listener != null) {
                    listener.onResultListener(JSON.parseObject(string, JsonRootBean.class));
                }
//                LogUtils.d(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ds.close();
        LogUtils.d("UDP监听关闭");//udp生命结束
        udpLifeOver = false;
    }
}