package com.javasocket.test;
/*
 * Java URL
 */

import java.net.MalformedURLException;
import java.net.URL;

public class Test02 {
    public static void main(String[] args) {
        // URL实例
        try {
            URL test = new URL("http://www.test.com");
            // # 锚点
            URL url  = new URL(test,"/index.html?user=testuser#test");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
