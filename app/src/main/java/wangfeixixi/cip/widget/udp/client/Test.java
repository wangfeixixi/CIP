package wangfeixixi.cip.widget.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import wangfeixixi.cip.widget.udp.client.data.TxtUtils;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> jsonAraray = TxtUtils.getJsonAraray();

        for (int i = 0; i < jsonAraray.size(); i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            send(jsonAraray.get(i));
            System.out.println("send" + System.currentTimeMillis());
        }
    }

    public static void send(String msg) {
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


        packetSend = new DatagramPacket(msg.getBytes(), msg.getBytes().length, hostAddress, 9999);
        try {
            socket.send(packetSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
