package com.sixin.socket.common.persist.spi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @program: lx-rpc-framework
 * @description: SPI类加载器帮助类
 * @author: Mr.Liu
 * @create: 2020-03-01 15:51
 **/
public class ServiceSupport {
    private final static Map<String,Object> singletonServices = new HashMap<>();
    public synchronized static <S> S load(Class<S> service) {
        return StreamSupport.
                stream(ServiceLoader.load(service).spliterator(), false)
                //.map(ServiceSupport::singletonFilter)
                .findFirst().orElseThrow(ServiceLoadException::new);
    }
    public synchronized static <S> Collection<S> loadAll(Class<S> service) {
        return StreamSupport.
                stream(ServiceLoader.load(service).spliterator(), false)
                //.map(ServiceSupport::singletonFilter)
                .collect(Collectors.toList());
    }
}
