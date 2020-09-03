package com.sixin.socket.iot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @program: ruoyi
 * @description:
 * @author: Mr.Liu
 * @create: 2020-03-19 21:51
 **/
@Component
public class IOT implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(IOT.class);


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String serviceName = HelloService.class.getCanonicalName();
//                File tmpDirFile = new File(System.getProperty("java.io.tmpdir"));
//                File file = new File(tmpDirFile, "lx_rpc_name_service.data");
//                HelloService helloService = new HelloServiceImpl();
//                logger.info("创建并启动RpcAccessPoint...");
//                try(RpcAccessPoint rpcAccessPoint = ServiceSupport.load(RpcAccessPoint.class);
//                    Closeable ignored = rpcAccessPoint.startServer()) {
//                    NameService nameService = rpcAccessPoint.getNameService(file.toURI());
//                    assert nameService != null;
//                    logger.info("向RpcAccessPoint注册{}服务...", serviceName);
//                    URI uri = rpcAccessPoint.addServiceProvider(helloService, HelloService.class);
//                    logger.info("服务名: {}, 向NameService注册...", serviceName);
//                    nameService.registerService(serviceName, uri);
//                    while (true){
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
