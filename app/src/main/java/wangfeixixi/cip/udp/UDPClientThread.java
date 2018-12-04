package wangfeixixi.cip.udp;

import org.greenrobot.eventbus.EventBus;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPClientThread extends Thread {
    private MulticastSocket ds;
    String multicastHost = "224.0.0.1";
    InetAddress receiveAddress;
    private String result;

    public UDPClientThread() {
        System.out.println("创建");
        try {
            ds = new MulticastSocket(8003);
            receiveAddress = InetAddress.getByName(multicastHost);
            ds.joinGroup(receiveAddress);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public boolean isRunning = true;

    @Override
    public void run() {
        byte buf[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        while (isRunning) {
            try {
                ds.receive(dp);
                result = new String(buf, 0, dp.getLength());
                System.out.println(result);
                EventBus.getDefault().post(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
