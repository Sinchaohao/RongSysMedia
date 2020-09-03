package com.sixin.socket.nettyserver;

import com.sixin.iot.domain.Torrent;
import com.sixin.socket.common.excention.FrameHeardExcetion;
import com.sixin.socket.common.inboundhandler.MySimpleChannelInboundHandler;
import com.sixin.socket.common.persist.BaseMo;
import com.sixin.socket.common.persist.spi.ServiceLoadException;
import com.sixin.socket.common.persist.spi.ServiceSupport;
import com.sixin.iot.domain.Energy;
import com.sixin.iot.domain.Environl;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Date;

/**
 * @program: ruoyi
 * @description:  环境与能源终端的数据解析获取
 * @author: Mr.Liu
 * @create: 2020-03-20 20:25
 **/
public class CheckHandler extends MySimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(CheckHandler.class);
    private static BaseMo baseMo;
    private boolean tag = false;
    static {
        try{
            baseMo = ServiceSupport.load(BaseMo.class);
        }catch (Throwable e){
            if(e instanceof ServiceLoadException){
                logger.error("com.hunau.iot.server.common.persist.BaseMo.class 未找到实现类",e);
            }
        }
    }

    protected CheckHandler(boolean autoRelease) {
        super(autoRelease);
    }


    @Override
    protected void messageRead(ChannelHandlerContext ctx, Object s) throws Exception {
        ByteBuf buf = (ByteBuf)s;

        //创建目标大小的数组
        byte[] n = new byte[buf.readableBytes()];
        //把数据从bytebuf转移到byte[]
        buf.getBytes(0,n);
        ReferenceCountUtil.retain(s);
        //收到的源数据，用于做0xaa或者其它处理用的
        //String ls = Convert.bytesToHexString(n);
        //下面是处理环境能源相关数据的
        String src = new String(n).trim();;
        if(!((src.contains("aa")&&src.contains("cc"))||(src.contains("AA")&&src.contains("CC"))
                ||(src.contains("aa")&&src.contains("CC"))||(src.contains("AA")&&src.contains("cc")))){
            return;
        }
        String[] list = src.substring(0,src.length()-2).split(",");
        if(list.length<3){
            return;
        }
        //02代表回传的是环境采集的回传参数
        if ("02".equals(list[2])&&"12".equals(list[1])){
//            if (list.length!=17){
//                throw new FrameHeardExcetion("环境采集终端上传的数据长度错误");
//            }
            //不需要接收的话，直接返回，不做数据处理
            if(ChannelManage.dontIn.containsKey(list[3])){
                if(!tag){
                    ChannelManage.addCache(ctx,list[3]);
                    tag = true;
                }
                ByteBuf resp = Unpooled.copiedBuffer("AA,02,12,0,0,CC".getBytes());
                ctx.channel().writeAndFlush(resp).addListener(future -> {
                    if(future.isSuccess()) {
                    } else{
                        logger.error( "数据发送失败!");
                    }
                });
                return;
            }
            Environl tbEnvironment = new Environl();
           tbEnvironment.setImei(list[3]);
//            tbEnvironment.setAmbient_temp(list[4]);
//            tbEnvironment.setAmbient_hum(list[5]);
//            tbEnvironment.setAmbient_light(list[6]);
//            tbEnvironment.setCo2(list[7]);
//            tbEnvironment.setSoil_temp(list[8]);
//            tbEnvironment.setSoil_hum(list[9]);
//            tbEnvironment.setAtmo_pressure(list[10]);
//            tbEnvironment.setWind_speed(list[11]);
//            tbEnvironment.setWind_direction(list[12]);
//            tbEnvironment.setSnow_rain(list[13]);
//            tbEnvironment.setPm25(list[14]);
//            tbEnvironment.setCo(list[15]);
//            tbEnvironment.setSo2(list[16]);
            tbEnvironment.setAmbient_temp(Float.parseFloat(list[4]));
            tbEnvironment.setAmbient_hum(Float.parseFloat(list[5]));
            tbEnvironment.setAmbient_light(Float.parseFloat(list[6]));
            tbEnvironment.setCo2(Float.parseFloat(list[7]));
            tbEnvironment.setSoil_temp(Float.parseFloat(list[8]));
            tbEnvironment.setSoil_hum(Float.parseFloat(list[9]));
            tbEnvironment.setAtmo_pressure(Float.parseFloat(list[10]));
            tbEnvironment.setWind_speed(Float.parseFloat(list[11]));
            tbEnvironment.setWind_direction(list[12]);
            tbEnvironment.setSnow_rain(list[13]);
            tbEnvironment.setPm25(Float.parseFloat(list[14]));
            tbEnvironment.setCo(Float.parseFloat(list[15]));
            tbEnvironment.setSo2(Float.parseFloat(list[16]));
            baseMo.insert(tbEnvironment);
            ChannelManage.addCache(ctx,list[3]);
            /**更新最后通信时间**/
            Torrent tbIoterminal = new Torrent();
            tbIoterminal.setImei(list[3]);
            tbIoterminal.setOnline("1");
            tbIoterminal.setConip(ctx.channel().remoteAddress().toString().substring(1).split(":")[0]);
            tbIoterminal.setEnddate(new Date());
            baseMo.update(tbIoterminal);
            /**环境数据上传才返回响应数据给终端**/
            ByteBuf resp = Unpooled.copiedBuffer("AA,02,12,0,0,CC".getBytes());
            ctx.channel().writeAndFlush(resp).addListener(future -> {
                if(future.isSuccess()) {
                } else{
                    logger.error( "数据发送失败!");
                }
            });
        }
        //能源终端
        else if ("01".equals(list[2])&&"12".equals(list[1])){
//            if (list.length!=13){
//                throw new FrameHeardExcetion("能源终端上传的数据错误");
//            }
            String imei = ChannelManage.dev2id.get(ctx.channel().id().toString());
            //不需要接收的话，直接返回，不做数据处理
            if(ChannelManage.dontIn.containsKey(ChannelManage.dev2id.get(ctx.channel().id().toString()))){
//                ByteBuf resp = Unpooled.copiedBuffer("AA,02,12,0,0,CC".getBytes());
//                ctx.channel().writeAndFlush(resp);
                /**更新最后通信时间**/
                Torrent tbIoterminal = new Torrent();
                tbIoterminal.setImei(imei);
                tbIoterminal.setEnddate(new Date());
                baseMo.update(tbIoterminal);
                return;
            }
            Energy tbEnergy = new Energy();

            tbEnergy.setImei(imei);
            tbEnergy.setPow1(list[3]);
            tbEnergy.setGrouppow(list[4]);
            tbEnergy.setVol18v1(list[5]);
            tbEnergy.setVol18v2(list[6]);
            tbEnergy.setVol24(list[7]);
            tbEnergy.setVol28(list[8]);
            tbEnergy.setExtendpow(list[9]);
            tbEnergy.setSolarpow(list[10]);
            tbEnergy.setCharge(list[11]);
            tbEnergy.setWork(Integer.parseInt(list[12]));
            baseMo.insert(tbEnergy);
            /**更新最后通信时间**/
            Torrent tbIoterminal = new Torrent();
            tbIoterminal.setImei(imei);
            tbIoterminal.setEnddate(new Date());
            baseMo.update(tbIoterminal);
        }else {
            throw new FrameHeardExcetion("未知终端类型数据");
        }
        //获取客户端ip
//        InetSocketAddress ipSocket = (InetSocketAddress)ctx.channel().remoteAddress();
//        String clientIp = ipSocket.getAddress().getHostAddress();
        //System.getProperty("line.separator") 这也是换行符,功能和"\n"是一致的,但是此种写法屏蔽了 Windows和Linux的区别 ，更保险一些
    }

    /**
     * channelRead()函数执行完之后执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx , Throwable cause){
        if (cause instanceof FrameHeardExcetion){
            logger.error(ctx.channel().id()+">>>发来的数据：\""+cause.getMessage()+"\"，关闭连接"+">>>"+HeartBeatServer.dataTime());
        }
        cause.printStackTrace();
        //应该发送响应码给客户端
//        ctx.writeAndFlush(Unpooled.copiedBuffer("501".getBytes())).addListener(future -> {
//            if(future.isSuccess()) {
//            } else{
//                logger.error( "数据发送失败!");
//            }
//        });;
        ctx.close();
    }
}
