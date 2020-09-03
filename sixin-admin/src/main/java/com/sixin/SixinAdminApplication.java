package com.sixin;

import com.sixin.socket.utils.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SixinAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SixinAdminApplication.class, args);
        SpringContextUtils.setApplicationCtx(applicationContext);
    }

}
