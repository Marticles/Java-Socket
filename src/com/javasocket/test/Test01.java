package com.javasocket.test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
/*
 * InetAddress类
 */

public class Test01 {
    public static void main(String[] args) throws UnknownHostException{       
    //获取本机的InetAddress实例  
    InetAddress address = InetAddress.getLocalHost();
    
    System.out.println(address.getHostName());
    System.out.println(address.getHostAddress());
    byte[] bytes = address.getAddress(); // 字节数组形式的IP地址
    System.out.println(Arrays.toString(bytes));
    System.out.println(address);
    }
}
