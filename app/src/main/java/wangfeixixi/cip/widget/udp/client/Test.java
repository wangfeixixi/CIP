package wangfeixixi.cip.widget.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import wangfeixixi.cip.widget.udp.UDPConfig;

public class Test {
    public static void main(String[] args) {
        while (true) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            send();
            System.out.println("发送数据" + System.currentTimeMillis());
        }
    }

    public static void send() {
        DatagramSocket socket = null;
        DatagramPacket packetSend, packetRcv;
//        boolean udpLife = true; //udp生命线程
//        byte[] msgRcv = new byte[1024]; //接收消息
        InetAddress hostAddress = null;
        try {
            hostAddress = InetAddress.getByName(UDPConfig.url);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        String msgSend = "{\n" +
                "\t\"cmd\" : 0,\n" +
                "\t\"hvDatas\" : \n" +
                "\t{\n" +
                "\t\t\"fcwAlarm\" : 0,\n" +
                "\t\t\"heading\" : 280.0,\n" +
                "\t\t\"latitude\" : 303403470,\n" +
                "\t\t\"longitude\" : 1212800153,\n" +
                "\t\t\"remoteId\" : 0,\n" +
                "\t\t\"speed\" : 50,\n" +
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
                "\t\t\t\"fcwAlarm\" : 0,\n" +
                "\t\t\t\"heading\" : 51.200000000000003,\n" +
                "\t\t\t\"latitude\" : 303402356,\n" +
                "\t\t\t\"longitude\" : 1212809770,\n" +
                "\t\t\t\"remoteId\" : 67239940,\n" +
                "\t\t\t\"speed\" : 50,\n" +
                "\t\t\t\"timestampMs\" : 850,\n" +
                "\t\t\t\"timestampSecond\" : 1544169563,\n" +
                "\t\t\t\"x\" : 10,\n" +
                "\t\t\t\"y\" : 10,\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"sn\" : 1\n" +
                "}";
        packetSend = new DatagramPacket(msgSend.getBytes(), msgSend.getBytes().length, hostAddress, UDPConfig.port);

        try {
            socket.send(packetSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
