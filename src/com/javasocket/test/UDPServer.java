package com.javasocket.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.omg.CORBA.BAD_TYPECODE;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(54321);
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        // 接受到数据报前会一直阻塞
        socket.receive(packet);
        String info = new String(data, 0, packet.getLength());
        System.out.println("服务端输出：客户端请求为：" + info);
        // 响应客户端
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        byte[] data2 = "response info from server".getBytes();
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length,address,port);
        socket.send(packet2);
        socket.close();
    }
}
