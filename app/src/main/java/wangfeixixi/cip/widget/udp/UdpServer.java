package wangfeixixi.cip.widget.udp;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import wangfeixixi.cip.beans.JsonRootBean;

/**
 * Created by 朱浩 on 2016/5/18.
 */
public class UdpServer implements Runnable {
    private String ip = ApiConstant.url;
    private int port = ApiConstant.port;
    private DatagramPacket dpRcv = null, dpSend = null;
    private static DatagramSocket ds = null;
    private InetSocketAddress inetSocketAddress = null;
    private byte[] msgRcv = new byte[1024];
    private boolean udpLife = true;     //udp生命线程
    private boolean udpLifeOver = true; //生命结束标志，false为结束


    public UdpServer(String mIp, int mPort) {
        this.ip = mIp;
        this.port = mPort;

        Log.i("SocketInfo", "创建");
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


    @Override
    public void run() {
        inetSocketAddress = new InetSocketAddress(ip, port);
        try {
            ds = new DatagramSocket(inetSocketAddress);
            Log.i("SocketInfo", "UDP服务器已经启动");

//            SetSoTime(10000);
            //设置超时，不需要可以删除
        } catch (SocketException e) {
            e.printStackTrace();
        }

        dpRcv = new DatagramPacket(msgRcv, msgRcv.length);
        while (udpLife) {
            try {
                Log.i("SocketInfo", "UDP监听中");
                ds.receive(dpRcv);

                String string = new String(dpRcv.getData(), dpRcv.getOffset(), dpRcv.getLength());
                Log.i("SocketInfo", "收到信息：" + string);

                JsonRootBean jsonRootBean = JSON.parseObject(string, JsonRootBean.class);

                EventBus.getDefault().post(jsonRootBean);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ds.close();
        Log.i("SocketInfo", "UDP监听关闭");
        //udp生命结束
        udpLifeOver = false;
    }
}