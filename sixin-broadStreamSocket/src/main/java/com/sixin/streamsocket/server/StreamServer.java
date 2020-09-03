package com.sixin.streamsocket.server;

import com.sixin.streamsocket.domain.Stream;
import com.sixin.streamsocket.domain.TerStream;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author 张超 teavamc
 * @Description: 流媒体服务器
 * @ClassName StreamServer
 * @date 2019/2/16 14:38
 **/
public class StreamServer {

    // 端口号
    private int SX_SERVERPORT = 8800;
    // 可以接受客户端的数量
    private int SX_SERVERbacklog = 2000;

    //终端端口
    private int PORT = 8800;
    //允许客户端数量
    private static int Socketbacklog = 1000;
    final static String UTF_8 = "utf-8";
    public static ExecutorService executorService =  Executors.newCachedThreadPool();

    // 消息的中止判断符
    public String EndChar = "cc";
    // 消息的开始判断符
    public String BeginChar = "aa";

    private static final Logger log = LoggerFactory.getLogger(StreamServer.class);

    private List<Stream> streamlist;
    private List<TerStream> terstreamlist;

    public StreamServer() {
        //  8800
        this.PORT= SX_SERVERPORT;
        //  2000
        StreamServer.Socketbacklog = SX_SERVERbacklog;
        // 实例化
        streamlist = new ArrayList<Stream>();
        terstreamlist = new ArrayList<TerStream>();
    }

    public void bind() {

        //  处理 Nio的Accept
        EventLoopGroup boss = new NioEventLoopGroup();
        //  处理 Nio的Read和Write事件
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            //  Netty 中 服务端 启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            //  启动类配置 启动类中 加入两个线程组
            bootstrap.group(boss, worker);
            //  启动类配置 Channel配置，Channel 为 异步TCP Socket 连接
            bootstrap.channel(NioServerSocketChannel.class);
            //  启动类配置 SO_BACKLOG 含义 允许连接客户端数量 2000
            bootstrap.option(ChannelOption.SO_BACKLOG, Socketbacklog);
            //  启动类配置 SO_REUSEADDR 含义 地址复用 允许重复使用本地地址和端口
            bootstrap.option(ChannelOption.SO_REUSEADDR, true);
            //  启动类配置 childHandler 代表 客户端成功connect后才执行 NettyChannelHandler类的业务放阿飞
            bootstrap.childHandler(new NettyChannelHandler());
    //  保存Channel异步操作的结果
    ChannelFuture channelFuture = bootstrap.bind(PORT).sync();
            if (channelFuture.isSuccess()) {
        System.out.println("启动流媒体监听");
        log.info("正常日志  信息： 开始启动流媒体监听，端口:" + PORT + "；" );
    }
    // 关闭连接
            channelFuture.channel().closeFuture().sync();

} catch (Exception e) {
        log.info("启动流媒体服务异常，异常信息：" + e.getMessage());
        } finally {
        boss.shutdownGracefully();
        worker.shutdownGracefully();
        }
        }

    private class NettyChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel)
                throws Exception {
            // 自己的逻辑Handler
            socketChannel.pipeline().addLast(new NettyServerHandler(streamlist,terstreamlist));
        }
    }
}
