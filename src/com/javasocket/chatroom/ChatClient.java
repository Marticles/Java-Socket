package com.javasocket.chatroom;

import java.net.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 54321);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        final DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Frame myframe = new Frame("ChatRoom");
        Panel panelx = new Panel();
        final TextField input = new TextField(50);
        TextArea textWindows = new TextArea(20, 50);
        panelx.add(textWindows);
        panelx.add(input);
        myframe.add(panelx);
        new receiveThread(dis, textWindows); // 创建启动接收消息的线程
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dos.writeUTF(input.getText()); // 发送数据
                    input.setText(""); // 清空输入框内容
                } catch (IOException e2) {
                }
            }
        });
        myframe.setSize(500, 400);
        myframe.setVisible(true);
    }
}

class receiveThread extends Thread {
    DataInputStream dis;
    TextArea displayarea;

    public receiveThread(DataInputStream dis, TextArea m) {
        this.dis = dis;
        displayarea = m;
        this.start();
    }

    public void run() {
        for (;;) {
            try {
                String str = dis.readUTF(); 
                displayarea.append(str + "\n"); 
            } catch (IOException e) {
            }
        }
    }
}