package com.sixin.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static com.sixin.redis.RedisWatchLock.*;

/**
 * 聊天室
 *
 * @author 周博
 * @date 2019-04-27
 */
public class CheckSoketHandler extends SimpleChannelInboundHandler<Object> {
    private WebSocketServerHandshaker handshaker;
    private final int port = 8888;
    private final String url = "ws://localhost:" + port + "/websocket";
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final Logger log = LoggerFactory.getLogger(CheckSoketHandler.class);

    /**
     * 处理客户端向服务端发起http握手请求的业务
     */
    @SuppressWarnings("deprecation")
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.getDecoderResult().isSuccess() || !("websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req,
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(url, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    /**
     * 服务端向客户端响应消息
     */
    @SuppressWarnings("deprecation")
    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 服务端向客户端发送数据
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

    /**
     * 处理客户端与服务端之前的websocket业务
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否是关闭webSocket的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel
        if (frame instanceof TextWebSocketFrame) {
            TextWebSocketFrame msg = (TextWebSocketFrame) frame;
            Channel incoming = ctx.channel();
            for (Channel channel : group) {
                if (channel != incoming) {
                    channel.writeAndFlush(new TextWebSocketFrame(msg.text()));
                } else {
                    channel.writeAndFlush(new TextWebSocketFrame(msg.text()));
                }
            }
            new TestThread(msg.text()).run();
        }
    }

    /**
     * 对客户端websocket请求的核心方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handHttpRequest(context, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {// 处理websocket连接业务
            handWebSocketFrame(context, (WebSocketFrame) msg);
        }
    }

    /**
     * 新用户加入
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : group) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        }
        group.add(ctx.channel());

    }

    /**
     * 有用户退出时
     */

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : group) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");
        }
        group.remove(ctx.channel());
    }

    /**
     * 服务端监听到客户端活动
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("Client:" + channel.remoteAddress() + "在线");
    }

    /**
     * 有用户断开连接时
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info("Client:" + channel.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        log.error("Client:" + channel.remoteAddress() + "异常");
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 用户发送过来的数据结束之后
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
