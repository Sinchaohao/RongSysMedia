package com.sixin.socket.nettyserver;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Discards any incoming data.
 * @author liuyanbin
 */
public class HeartBeatServer {
    private static final Logger logger = LoggerFactory.getLogger(HeartBeatServer.class);
    private int port;
    private AtomicInteger connectNum;
    //private static SpringContextUtils springContextUtils= new SpringContextUtils();

    public HeartBeatServer(int por) {

        this.port = por;
        //设置最大连接数
        connectNum = new AtomicInteger(2);
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(0,new DefaultThreadFactory("bossGroup"));
        EventLoopGroup workerGroup = new NioEventLoopGroup(0,new DefaultThreadFactory("workGroup"));
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.handler(new LoggingHandler(LogLevel.DEBUG));
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置tcp缓冲区
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 设置发送缓冲大小
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    // 这是接收缓冲大小
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    // 保持连接
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new HeartBeatServerChannelHandler());

            /**绑定端口并且添加监听和异步启动**/
            ChannelFuture f = b.bind(port).addListener(future -> {
                if(future.isSuccess()) {
                    logger.info( new Date() + ": 端口["+ port + "]绑定成功!");
                } else{
                    logger.error( "端口["+ port + "]绑定失败!");
                }
            }).sync();

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            logger.info( new Date() + ":socket终端监控停机， 端口["+ port + "] ，优雅的关闭成功!");
        }
    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8090;
        }
        System.out.println("Netty服务启动成功：开始监听端口："+port);
        new HeartBeatServer(port).run();
    }
    public static void func(){
        // 先设置一个默认的，要是有其他的端口传进来就是执行下面的代码
        int port = 8090;
        System.out.println("Netty服务启动成功：开始监听端口："+port);
        new HeartBeatServer(port).run();
    }

    public static String dataTime(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }
}
