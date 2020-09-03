package com.sixin.web.core.config;

import com.sixin.socket.nettyserver.HeartBeatServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: ruoyi
 * @description: 终端TCP服务器
 * @author: Mr.Liu
 * @create: 2020-03-22 12:01
 **/
@Component
public class TerminalConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new HeartBeatServer(8900).run();
            }
        }).start();
    }
}
