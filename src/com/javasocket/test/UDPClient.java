package com.javasocket.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName("localhost");
        int port = 54321;
        byte[] data = "test info from client".getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length,address,port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        // 接受服务器响应
        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length,address,port);
        socket.receive(packet2);
        String response = new String(data2, 0, packet2.getLength());
        System.out.println("客户端输出：服务器响应为：" + response);
        socket.close();
    }
}
