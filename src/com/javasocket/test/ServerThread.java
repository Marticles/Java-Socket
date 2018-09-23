package com.javasocket.test;
/*
 * 多线程实现基于TCP的socket通信
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket = null;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        InputStream inputStream;      
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;  
        OutputStream outputStream;
        PrintWriter printWriter;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            // 字节流转换为字符流
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            while((info=bufferedReader.readLine())!=null) {
                System.out.println("服务端输出：客户端请求为："+info);            
            }
            // 响应请求
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write("response info from server");
            printWriter.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    
}
