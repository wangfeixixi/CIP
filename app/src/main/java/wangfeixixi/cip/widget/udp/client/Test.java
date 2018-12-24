package wangfeixixi.cip.widget.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Test {

    private static String msgSend1 = "{\n" +
            "\t\"cmd\" : 0,\n" +
            "\t\"hvDatas\" : \n" +
            "\t{\n" +
            "\t\t\"fcwAlarm\" : 1,\n" +
            "\t\t\"heading\" : 121.236812,\n" +
            "\t\t\"latitude\" : 30.338475 ,\n" +
            "\t\t\"longitude\" : 121.236812,\n" +
            "\t\t\"remoteId\" : 0,\n" +
            "\t\t\"speed\" : 5,\n" +
            "\t\t\"timestampMs\" : 0,\n" +
            "\t\t\"timestampSecond\" : 0,\n" +
            "\t\t\"x\" : 0.0,\n" +
            "\t\t\"y\" : 0.0\n" +
            "\t},\n" +
            "\t\"magic\" : 2857740885,\n" +
            "\t\"nov\" : 1,\n" +
            "\t\"rvDatas\" : \n" +
            "\t[\n" +
            "\t\t{\n" +
            "\t\t\t\"fcwAlarm\" : 1,\n" +
            "\t\t\t\"heading\" : 51.200000000000003,\n" +
            "\t\t\t\"latitude\" : 30.329397,\n" +
            "\t\t\t\"longitude\" : 121.315383,\n" +
            "\t\t\t\"remoteId\" : 67239940,\n" +
            "\t\t\t\"speed\" : 30,\n" +
            "\t\t\t\"timestampMs\" : 850,\n" +
            "\t\t\t\"timestampSecond\" : 1544169563,\n" +
            "\t\t\t\"x\" : 5,\n" +
            "\t\t\t\"y\" : 6,\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"sn\" : 1\n" +
            "}";

    private static String msgSend2 = "{\n" +
            "\t\"cmd\" : 0,\n" +
            "\t\"hvDatas\" : \n" +
            "\t{\n" +
            "\t\t\"fcwAlarm\" : 1,\n" +
            "\t\t\"heading\" : 121.236812,\n" +
            "\t\t\"latitude\" : 30.338475 ,\n" +
            "\t\t\"longitude\" : 121.236812,\n" +
            "\t\t\"remoteId\" : 0,\n" +
            "\t\t\"speed\" : 5,\n" +
            "\t\t\"timestampMs\" : 0,\n" +
            "\t\t\"timestampSecond\" : 0,\n" +
            "\t\t\"x\" : 0.0,\n" +
            "\t\t\"y\" : 0.0\n" +
            "\t},\n" +
            "\t\"magic\" : 2857740885,\n" +
            "\t\"nov\" : 1,\n" +
            "\t\"rvDatas\" : \n" +
            "\t[\n" +
            "\t\t{\n" +
            "\t\t\t\"fcwAlarm\" : 1,\n" +
            "\t\t\t\"heading\" : 51.200000000000003,\n" +
            "\t\t\t\"latitude\" : 30.329397,\n" +
            "\t\t\t\"longitude\" : 121.315383,\n" +
            "\t\t\t\"remoteId\" : 67239940,\n" +
            "\t\t\t\"speed\" : 30,\n" +
            "\t\t\t\"timestampMs\" : 850,\n" +
            "\t\t\t\"timestampSecond\" : 1544169563,\n" +
            "\t\t\t\"x\" : 5,\n" +
            "\t\t\t\"y\" : 6,\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"sn\" : 1\n" +
            "}";

    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            send();
            System.out.println("send" + System.currentTimeMillis());
        }
    }

    public static void testUDPSend() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                test();
            }
        }).start();
    }

    public static void test() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            send();
            System.out.println("send" + System.currentTimeMillis());
        }
    }

    private static boolean isSend = false;

    public static void send() {
        DatagramSocket socket = null;
        DatagramPacket packetSend, packetRcv;
//        boolean udpLife = true; //udp生命线程
//        byte[] msgRcv = new byte[1024]; //接收消息
        InetAddress hostAddress = null;
        try {
            hostAddress = InetAddress.getByName("255.255.255.255");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        if (isSend) {
            packetSend = new DatagramPacket(msgSend1.getBytes(), msgSend1.getBytes().length, hostAddress, 9999);
        } else {
            packetSend = new DatagramPacket(msgSend2.getBytes(), msgSend2.getBytes().length, hostAddress, 9999);
        }

        isSend = !isSend;

        try {
            socket.send(packetSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
