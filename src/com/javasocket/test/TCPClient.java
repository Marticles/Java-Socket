package com.javasocket.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.text.AbstractDocument.BranchElement;

/*
 * 客户端
 */
public class TCPClient {
    public static void main(String[] args) {
        try {
            // 建立连接
            Socket socket = new Socket("localhost", 54321);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("request info from client");
            printWriter.flush();
            socket.shutdownOutput();
            // 获取服务端响应
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String info = null;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println("客户端输出：服务端响应为：" + info);
            }
            // 关闭资源
            bufferedReader.close();
            inputStream.close();
            socket.shutdownInput();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
