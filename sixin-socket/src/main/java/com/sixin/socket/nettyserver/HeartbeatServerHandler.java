package com.sixin.socket.nettyserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * @author liuyanbin
 */
public class HeartbeatServerHandler extends SimpleChannelInboundHandler {

    /**
     * Return a unreleasable view on the given ByteBuf
     * which will just ignore release and retain calls.
     * 定义的心跳包类型
     * **/
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat : Please send again",
                    CharsetUtil.UTF_8));
    private static final ByteBuf HEARTBEAT_CLOSE = Unpooled
            .unreleasableBuffer(Unpooled.copiedBuffer("Closed : Please reconnect",
                    CharsetUtil.UTF_8));
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if (event.state() == IdleState.READER_IDLE) {
                type = "read idle";
            } else if (event.state() == IdleState.WRITER_IDLE) {
                type = "write idle";
            } else if (event.state() == IdleState.ALL_IDLE) {
                type = "all idle";
            }
            System.out.println("超时类型："+type);
            final ByteBuf buf = ctx.alloc().buffer().writeBytes("ping".getBytes());
            ctx.writeAndFlush(buf);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
