package com.sixin.socket.nettyserver;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: Netty
 * @description:
 * @author: Mr.Liu
 * @create: 2019-04-11 17:07
 **/
public class HeartBeatServerChannelHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ByteBuf delimiter= Unpooled.copiedBuffer("CC".getBytes());
        ch.pipeline().addLast("ping", new IdleStateHandler(300, 0, 600,TimeUnit.SECONDS));
        //创建DelimiterBasedFrameDecoder对象，将其加入到ChannelPipeline
        //参数1024表示单条消息的最大长度，当达到该长度仍然没有找到分隔符就抛出TooLongFrame异常，第二个参数就是分隔符
        //由于DelimiterBasedFrameDecoder自动对请求消息进行了解码，下面的ChannelHandler接收到的msg对象就是完整的消息包
        //ch.pipeline().addLast("DelimiterBasedFrameDecoder",new DelimiterBasedFrameDecoder(1024, delimiter));
        //ch.pipeline().addLast("LineBasedFrameDecoder",new LineBasedFrameDecoder(1024));
        //StringDecoder解码器将ByteBuf解码成字符串对象，这样在ChannelHandlerAdapter中读取消息时就不需要通过ByteBuf获取了
        //ch.pipeline().addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
        //ch.pipeline().addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));
        //终端IMEI信息获取
        ch.pipeline().addLast("Auth",new AuthHandler());
        //设置连接数
        //最后一个handler中，最好不要使用ctx.channel().writeAndFlush();可能会出现死循环的
        //参数true表示最后释放掉byteBuf
        ch.pipeline().addLast("ConnectH",new ConnectHandler(new AtomicInteger(2),false));
        //这个不要它自动释放，不然后面的接收不到数据了
        ch.pipeline().addLast("CheckH",new CheckHandler(true));
        ch.pipeline().addLast("heart",new HeartbeatServerHandler());


    }
}
