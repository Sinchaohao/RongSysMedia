package com.sixin.socket.nettyserver;

import com.sixin.socket.common.inboundhandler.MySimpleChannelInboundHandler;
import com.sixin.socket.common.persist.BaseMo;
import com.sixin.socket.common.persist.spi.ServiceLoadException;
import com.sixin.socket.common.persist.spi.ServiceSupport;
import com.sixin.iot.domain.Torrent;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  其它处理
 * @author liuyanbin
 */
public class ConnectHandler extends MySimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(ConnectHandler.class);
    private static BaseMo baseMo;
    static {
        try{
            baseMo = ServiceSupport.load(BaseMo.class);

        }catch (Throwable e){
            if(e instanceof ServiceLoadException){
                logger.error("com.hunau.iot.server.common.persist.BaseMo.class 未找到实现类",e);
            }
        }
    }

    /**
     * AtomicInteger满足在高并发的情况下,原生的整形数值自增线程不安全的问题
     */
    /**控制最大连接数量**/
    private AtomicInteger connectNum;
    /**当前最大连接数量**/
    private AtomicInteger nowConnectNum;
    /** $设备编号:地址+功能+数据位+数据+校验位# **/
    /**统计用户发送消息次数**/
    private int counter;
    public ConnectHandler(AtomicInteger connectNum,boolean autoRelease) {
        super(autoRelease);
        this.connectNum = connectNum;
    }

    /**
     * 覆盖了 channelRead0() 事件处理方法。
     * 每当从服务端读到客户端写入信息时，
     * 其中如果你使用的是 Netty 5.x 版本时，
     * 需要把 channelRead0() 重命名为messageReceived()
     */
    @Override
    protected void messageRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();

        //logger.info("Client:"+incoming.remoteAddress()+" ::"+msg+"; the counter is :"+ ++counter+">>>"+HeartBeatServer.dataTime());
        //throw new RuntimeException();
    }

    /**
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx , Throwable cause){
        logger.error(ctx.channel().id()+"出现异常关闭连接"+">>>"+HeartBeatServer.dataTime());
        //应该发送响应码给客户端
//        ctx.writeAndFlush(Unpooled.copiedBuffer("500".getBytes())).addListener(future -> {
//            if(future.isSuccess()) {
//            } else{
//                logger.error( "数据发送失败!");
//            }
//        });
        ctx.close();
    }
    /**
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    /**
     * (non-Javadoc)
     * .覆盖了 handlerRemoved() 事件处理方法。
     * 每当从服务端收到客户端断开时
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        deletInfo(ctx);
        logger.info("客户端断开连接："+ctx.channel().remoteAddress());
    }
    /**
     * (non-Javadoc)
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("客户端"+ctx.channel().remoteAddress()+"连接成功");
        //保存设备channel
        ChannelManage.group.add(ctx.channel());
        super.handlerAdded(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    /**
     * 删除保存的信息
     * @param ctx
     */
    private void deletInfo(ChannelHandlerContext ctx){
        ChannelManage.group.remove(ctx.channel());
        String MIME = ChannelManage.dev2id.get(ctx.channel().id().toString());
        if(MIME!=null && !"".equals(MIME)) {
            //如果通过MIME能够获取到channel，并且是对应的channel 则直接删除
            ChannelManage.delCache(ctx,MIME);
            //更新状态
            updateTbIot(MIME, 0);
            logger.info("更新状态成功");
        }
    }

    /**
     * 更新数据库状态
     * @param MiME
     * @param status
     */
    public void updateTbIot(String MiME , Integer status){
        Torrent tbIoterminal = new Torrent();
        tbIoterminal.setImei(MiME);
        tbIoterminal.setOnline(String.valueOf(status));
        tbIoterminal.setEnddate(new Date());
        baseMo.update(tbIoterminal);
    }
}
