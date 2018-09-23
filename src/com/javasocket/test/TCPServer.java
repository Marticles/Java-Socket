package com.javasocket.test;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
/*
 * 服务器端
 */
public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(54321);
            System.out.println("服务器启动，等待客户端连接....");
            // 开始监听
            while(true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端IP地址为："+address.getHostAddress());
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
