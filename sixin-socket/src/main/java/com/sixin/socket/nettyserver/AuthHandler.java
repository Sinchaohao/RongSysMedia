package com.sixin.socket.nettyserver;

import com.sixin.socket.common.excention.AuthFailExcetion;
import com.sixin.socket.common.persist.BaseMo;
import com.sixin.socket.common.persist.spi.ServiceSupport;
import com.sixin.socket.utils.Convert;
import com.sixin.socket.utils.Tools;
import com.sixin.iot.domain.Torrent;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: ruoyi
 * @description: 终端登陆鉴权，第一次的环境数据相当于告诉服务器它的imei号
 * AA 11 11 00 01 38 36 32 31 30 35 30 32 31 35 38 36 31 33 30 1E CC
 * 环境数据案例：aa,12,02,862105021586130,27.90,55.80,0.16,0,0.00,0.00,1009,0.00,正西,无雨雪,0,0,0CC
 * 能源数据案例，在第一次连接时发送的第一条数据为该数据时，不做处理：AA,12,01,22.51,0.00,0.00,0.00,22.26,22.28,0.02,0.02,01,1,CC
 * @author: Mr.Liu
 * @create: 2020-03-21 17:23
 **/
public class AuthHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(AuthHandler.class);
    private static BaseMo baseMo;
    private boolean tag = false;
    static {
        try{
            baseMo = ServiceSupport.load(BaseMo.class);
        }catch (Exception e){

        }
    }
    /**
     * 更新数据库状态
     * @param MiME
     * @param status
     */
    private void updateTbIot(String MiME , Integer status,String addr){
        Torrent tbIoterminal = new Torrent();
        tbIoterminal.setImei(MiME);
        tbIoterminal.setConip(addr);
        tbIoterminal.setOnline(String.valueOf(status));
        tbIoterminal.setEnddate(new Date());
        baseMo.update(tbIoterminal);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        ByteBuf buf = (ByteBuf)o;
        //创建目标大小的数组
        byte[] n = new byte[buf.readableBytes()];
        //把数据从bytebuf转移到byte[]
        buf.getBytes(0,n);
        ReferenceCountUtil.retain(o);
        byte[] returndata = Convert.hexStringToBytes("aa11"+"1100"+"01"+"6f"+"6b"+"1e"+"cc");
        if (("aa".equals(Convert.byteToHexString(n[0]))||"AA".equals(Convert.byteToHexString(n[0])))
                &&("cc".equals(Convert.byteToHexString(n[n.length-1]))||"CC".equals(Convert.byteToHexString(n[n.length-1])))) {
            if ("11".equals(Convert.byteToHexString(n[1]))){
                if(n[4] == 1) {
                    logger.debug("物联网终端参数透传接口(终端主动请求获取)");
                    byte[] IMEI = Tools.subBytes(n, 5, 15);
                    if(!tag){
                        //ctx.pipeline().addAfter("DelimiterBasedFrameDecoder","StringDecoder",new StringDecoder(CharsetUtil.UTF_8));
                        //ctx.pipeline().addAfter("StringDecoder","StringEncoder",new StringEncoder(CharsetUtil.UTF_8));
                        //这个不要它自动释放，不然后面的接收不到数据了
                        //ctx.pipeline().addBefore("heart","CheckH",new CheckHandler(true));
                        //参数true表示最后释放掉byteBuf
                        //ctx.pipeline().addBefore("heart","ConnectH",new ConnectHandler(new AtomicInteger(2),false));
                        String imei = new String(IMEI);
                        //保存通道
                        ChannelManage.addCache(ctx,imei);
                        //更新数据库状态
                        updateTbIot(imei,1,ctx.channel().remoteAddress().toString().substring(1).split(":")[0]);
                        tag=true;
                    }
                    ctx.channel().writeAndFlush(Unpooled.buffer().writeBytes(returndata)).addListener(future -> {
                        if(future.isSuccess()) {
                        } else{
                            logger.error( "数据发送失败!");
                        }
                    });
                    //ctx.pipeline().remove(this.getClass());
                }else {
                    logger.info("物联网终端参数透传接口(终端主动请求获取) 数据类型错误");
                }
            }
        }
        ctx.fireChannelRead(o);
//        String[] list = o.split(",");
//        if(list.length<4){
//            throw new AuthFailExcetion("鉴权：数据包格式错误");
//        }
//        if(!"AA".equals(list[0]) && !"aa".equals(list[0])){
//
//            throw new AuthFailExcetion("鉴权：数据包包头错误");
//        }
//        if(!"11".equals(list[1])){
//            throw new AuthFailExcetion("鉴权：数据包数据类型错误");
//        }
//        if("02".equals(list[2])){
//            //MIME                       //password
//            if(!"".equals(list[3])){
//                //这个不要它自动释放，不然后面的接收不到数据了
//                ctx.pipeline().addBefore("heart","CheckH",new CheckHandler(true));
//                //参数true表示最后释放掉byteBuf
//                ctx.pipeline().addBefore("heart","ConnectH",new ConnectHandler(new AtomicInteger(2),false));
//
//                //保存通道
//                ChannelManage.dev1Mime.put(list[3],ctx.channel());
//                ChannelManage.dev2id.put(ctx.channel().id().toString(),list[3]);
//                //更新数据库状态
//                updateTbIot(list[3],1,ctx.channel().remoteAddress().toString().substring(1));
//                //移除当前handler
//                ctx.pipeline().remove(this.getClass());
//                ctx.fireChannelRead(o);
//            }else {
//                throw new AuthFailExcetion("数据无效");
//            }
//        }
    }

    /**
     * 本handler中的异常拦截
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof AuthFailExcetion){
            logger.error(ctx.channel().id()+">>>发来的登陆数据错误：\""+cause.getMessage()+"\"，关闭连接"+">>>"+HeartBeatServer.dataTime());
        }
        cause.printStackTrace();
        //应该发送响应码给客户端
//        ctx.writeAndFlush(Unpooled.copiedBuffer("600".getBytes())).addListener(future -> {
//            if(future.isSuccess()) {
//            } else{
//                logger.error( "数据发送失败!");
//            }
//        });
        ctx.close();
    }
}
