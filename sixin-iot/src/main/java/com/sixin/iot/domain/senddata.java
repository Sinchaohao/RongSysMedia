package com.sixin.iot.domain;

import java.io.PrintWriter;
import java.net.Socket;

public class senddata {
    public void connect() {
        try {

            // 创建socket
            Socket socketClient = new Socket("192.168.4.1", 5000);
            // 向服务器发送消息
            PrintWriter ps = new PrintWriter(socketClient.getOutputStream());

            ps.write("Sclose_beepE");
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
