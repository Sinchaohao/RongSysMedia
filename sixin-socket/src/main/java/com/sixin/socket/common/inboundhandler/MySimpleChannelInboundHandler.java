package com.sixin.socket.common.inboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.TypeParameterMatcher;

/**
 * @program: ruoyi
 * @description: 自定义SimpleChannelInboundHandler
 *                   netty 4.x版本的，5.x版本要将channelRead0()改掉为messageReceived()
 *                 这里主要是为了多个handler能够都执行，添加了ctx.fireChannelRead(o)
 * @author: Mr.Liu
 * @create: 2020-03-21 17:13
 **/
public abstract class MySimpleChannelInboundHandler<I> extends SimpleChannelInboundHandler<I> {
    protected MySimpleChannelInboundHandler() {
        super();
    }
    /**
     * @param autoRelease 是否自动释放
     */
    protected MySimpleChannelInboundHandler(boolean autoRelease) {
        super(autoRelease);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, I i) throws Exception {
        this.messageRead(channelHandlerContext,i);
        /**
         * 继续将数据传给下一个handle
         */
        channelHandlerContext.fireChannelRead(i);
    }

    /**
     *
     * @param ctx
     * @param o
     * @throws Exception
     */
    protected abstract void messageRead(ChannelHandlerContext ctx, I o) throws Exception;
}
