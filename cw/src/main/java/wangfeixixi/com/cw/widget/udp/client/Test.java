package wangfeixixi.com.cw.widget.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import wangfeixixi.com.cw.widget.udp.client.data.TxtUtils;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> jsonAraray = TxtUtils.getJsonAraray();
//        send(jsonAraray);
//        send(jsonAraray, 3100, 3300);//交叉碰撞预警
//        send(jsonAraray, 1800, 2100);///直线

        send(jsonAraray, 3900, 4200);//对撞
    }

    public static void send(ArrayList<String> msg) {
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
        try {
            for (int i = 0; i < msg.size(); i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                packetSend = new DatagramPacket(msg.get(i).getBytes(), msg.get(i).getBytes().length, hostAddress, 9999);
                socket.send(packetSend);
                System.out.println("send" + msg.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send(ArrayList<String> msg, int start, int end) {
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
        try {
            for (int i = start; i < end; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                packetSend = new DatagramPacket(msg.get(i).getBytes(), msg.get(i).getBytes().length, hostAddress, 9999);
                socket.send(packetSend);
                System.out.println("send" + msg.get(i));
                if (i == end - 1) i = start;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void send2(ArrayList<String> msgs, int startSN, int endSN) {
        for (int i = startSN; i < endSN; i++) {
            sendSocket(msgs.get(i));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == endSN - 1) {
                i = startSN;
            }
        }
    }

    public static void sendSocket(String msg) {
        DatagramSocket socket = null;
        DatagramPacket packetSend, packetRcv;
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
        try {
            packetSend = new DatagramPacket(msg.getBytes(), msg.getBytes().length, hostAddress, 9999);
            socket.send(packetSend);
            System.out.println("send" + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
