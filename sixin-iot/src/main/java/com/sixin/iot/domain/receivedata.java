package com.sixin.iot.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class receivedata {
    public String receive() {

        String s = "";
        try {

            // 创建socket
            Socket socketClient = new Socket("192.168.4.1", 5000);
            // 从服务器读取消息
            InputStreamReader inputStreamReader = new InputStreamReader(socketClient.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            s = bufferedReader.readLine();
            System.out.println("Client receive msg: " + s);
            socketClient.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
