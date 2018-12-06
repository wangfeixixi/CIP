package wangfeixixi.cip.widget.udp.client;

import org.greenrobot.eventbus.EventBus;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPClientRunnable implements Runnable {
    private MulticastSocket ds;
    String multicastHost = "224.0.0.1";
    InetAddress receiveAddress;
    private String result;

    public UDPClientRunnable() {
        System.out.println("创建");
        try {
            ds = new MulticastSocket(8003);
            receiveAddress = InetAddress.getByName(multicastHost);
            ds.joinGroup(receiveAddress);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static boolean isRunning = true;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        byte buf[] = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        while (isRunning) {
            try {
                ds.receive(dp);
                result = new String(buf, 0, dp.getLength());
                //Toast.makeText(this, new String(buf, 0, dp.getLength()), Toast.LENGTH_LONG);
                System.out.println("client ip : " + result);
                EventBus.getDefault().post(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
