package com.javasocket.chatroom;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatService {
    public static ArrayList<Client> allclient = new ArrayList<Client>(); 
    public static int clientnum = 0; 
    public static void main(String args[]) {
        try {
            ServerSocket serverSocket = new ServerSocket(54321);
            System.out.println("服务器启动，等待客户端连接....");
            while (true) {
                Socket socket = serverSocket.accept(); 
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream din = new DataInputStream(socket.getInputStream());
                Client client = new Client(clientnum, dos, din);
                allclient.add(client); 
                client.start();
                clientnum++;
            }
        } catch (IOException e) {
        }
    }

}

// 多线程处理客户端信息
class Client extends Thread {
    int id; 
    DataOutputStream dos; // 去往客户的输出流
    DataInputStream din; // 来自客户的输入流

    public Client(int id, DataOutputStream dos, DataInputStream din) {
        this.id = id;
        this.dos = dos;
        this.din = din;
    }

    public void run() {
        while (true) {
            try {
                String message = "客户" + id + ":" + din.readUTF(); // 读客户数据
                for (int i = 0; i < ChatService.clientnum; i++) {
                    // 将消息转发给其他客户
                    if (i != id) {
                        ChatService.allclient.get(i).dos.writeUTF(message);
                    } else {
                        String remessage = "";
                        remessage = message.replace("客户" + id, "自己");
                        StringBuffer space = new StringBuffer();
                        ChatService.allclient.get(id).dos.writeUTF((space.toString()) + remessage);
                    }
                }
            } catch (IOException e) {
            }
        }
    }
}
