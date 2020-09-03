package com.sixin.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * Created by MI on 2019/4/26.
 */
@Component
public class CheckServer {
    private int port = 8888;

    public void run(InetSocketAddress address) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new CheckSocketHandler());
            Channel channel = bootstrap.bind(address).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class CheckSocketHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast("http-codec", new HttpServerCodec());
            socketChannel.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
            socketChannel.pipeline().addLast("http-chunket", new ChunkedWriteHandler());
            socketChannel.pipeline().addLast("handler", new com.sixin.server.CheckSoketHandler());
        }
    }

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        new CheckServer().run(address);
    }
}
