package com.sixin.streamsocket.client;

import com.sixin.broad.utils.bConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;

import java.io.UnsupportedEncodingException;

/**
 * @author 张超 teavamc
 * @Description: 流媒体直播 客户端 业务类
 * @ClassName NettyClientHandler
 * @date 2019/2/16 15:55
 **/
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    // 声明 消息的中止判断符
    public String EndChar = "cc";
    // 声明 消息的开始判断符
    public static String BeginChar = "aa";
    // 声明 WebSocketSession 对象
    public WebSocketSession conn;
    // 声明 message
    public String message;
    // 声明 流的 id 号
    String streamid;
    // 声明 ByteBuf 对象  encoded 用于编码
    ByteBuf encoded;
    // 日志记录
    private static final Logger log = LoggerFactory.getLogger(NettyClientHandler.class);
    final static String UTF8 = "UTF8";

    /**
        * 初始化 NettyClientHandler 对象
        * @author 张超 teavamc
        * @date 2019/2/16
        * @param [connection, message]
        * @return
        */
    public NettyClientHandler(WebSocketSession connection,String message){
        this.conn = connection;
        this.message = message;
    }

    /**
        * 激活通道
        * @author 张超 teavamc
        * @date 2019/2/16
        * @param [ctx]
        * @return void
        */
    @Override
//    每个ChannelHandler通过add方法加入到ChannelPipeline中去的时候，会创建一个对应的ChannelHandlerContext，并且绑定，ChannelPipeline实际维护的是ChannelHandlerContext 的关系
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    	System.out.println("完成与服务器连接: "+ctx.channel().remoteAddress());
        // 将接受到的信息按照，分割成字符串数组
        String[] rs = message.split(":");
        // 获取命令码
        String commd="";
        // 获取 IMEI
        String imeilist="";
        if(rs.length>=2){
            commd=rs[0];
            streamid=rs[1];
            if(rs.length>2){
                imeilist=rs[2];
            }
        }
        switch(commd){
            case "start":
                // 发送信息，流媒体节目id + imei 终端号
                String sendtext = streamid+":" + imeilist;
                // 转成 Byte 格式
                SendToByte(ctx,"1","1",sendtext.getBytes(UTF8));
                log.info("正常日志  记录：前台直播开始命令  信息：直播编号" +streamid);
                break;
            case "end":
                SendToByte(ctx,"0","1",streamid.getBytes(UTF8));
                log.info("正常日志  记录：前台直播结束命令  信息：直播编号" +streamid);
                break;
        }

    }

    /**
        * 线程处理 channelRead0
        * @author 张超 teavamc
        * @date 2019/2/17
        * @param [ctx, msg]
        * @return void
        */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuf buf = msg.readBytes(msg.readableBytes());
//        将ByteBuf buf 转为十六进制
        String recstr = ByteBufUtil.hexDump(buf);
        if(recstr!=null && recstr!=""){

            String result = parseResult(recstr);
            String[] rs = result.split(":");
            String byteType="";
            String recievedata="";
            if(rs.length>=2){
                byteType=rs[0];
                recievedata=rs[1];
            }else{
                byteType=rs[0];
            }
            log.debug("跟踪日志  记录："+" 终端握手类型：" + byteType +"  信息：" + recievedata );
            String sendstr = "";
            switch(byteType){
                //结束
                case "0":
                    //表示streamserver发送指令成功
                    sendstr = byteType+":success";
                    //找到streamid相同的发送指令
                    conn.sendMessage(new TextMessage(sendstr));
                    //结束后关闭与server连接
                    ctx.close();
                    log.info("正常日志  记录：服务器直播结束命令返回  信息：直播" +streamid);
                    break;
                //开始
                case "1":
                    sendstr = byteType+":success";
                    //找到streamid相同的发送指令
                    conn.sendMessage(new TextMessage(sendstr));
                    log.info("正常日志  记录：服务器直播开始命令返回  信息：直播" +streamid);
                    //向服务器请求终端连接状态
                    SendToByte(ctx,"2","1",streamid.getBytes(UTF8));
                    break;
                //终端连接状态
                case "2":
                    sendstr=byteType+":"+recievedata;
                    //找到streamid相同的发送指令
                    conn.sendMessage(new TextMessage(sendstr));
                    log.info("正常日志  记录：服务器终端连接状态返回  信息：直播" +streamid);
//        		if(!ctx.isRemoved()){
                    //向服务器请求终端连接状态,隔2秒发送一次请求
                    Thread.sleep(2000);
                    SendToByte(ctx,"2","1",streamid.getBytes(UTF8));
//        		}
                    break;
            }
        }
    }

    /**
        * 异常抓取 关闭 ctx 线程
        * @author 张超 teavamc
        * @date 2019/2/17
        * @param [ctx, cause]
        * @return void
        */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
        ctx.close();
    }

    /**
    * 发送 Byte 指令
    * @author 张超 teavamc
    * @date 2019/2/22
    * @param [ctx, byteType, byteOrder, SendData]
    * @return void
    */
    private void SendToByte(ChannelHandlerContext ctx,String byteType,String byteOrder, byte[] SendData){
        if (SendData != null && SendData.length != 0){
            //用来计算校验和
            byte[] checkData = new byte[SendData.length + 3];
            try {
                int buffersize = 7 + SendData.length;
                //建立发送数据字节数组
                encoded = ctx.alloc().buffer(buffersize);
                //加上包头尾  将开始标示复制到发送数据中去
                encoded.writeByte(bConvert.hexStringToBytes(BeginChar)[0]);
                //将类型复制到发送数据中去
                encoded.writeByte((byte)Integer.parseInt(byteType));
                byte[] l = bConvert.hexStringToBytes(Integer.toHexString(SendData.length+2));
                if(l.length==1){
                    //将包长复制到发送数据中去
                    encoded.writeByte(l[0]);
                    encoded.writeByte((byte)0);
                    checkData[0]=l[0];
                    checkData[1]=(byte)0;
                }else if(l.length==2){
                    //将包长复制到发送数据中去
                    encoded.writeByte(l[1]);
                    encoded.writeByte(l[0]);
                    checkData[1]=l[0];
                    checkData[0]=l[1];
                }
                //将命令设置到发送数据中去
                encoded.writeByte((byte)Integer.parseInt(byteOrder));
                checkData[2] = (byte)Integer.parseInt(byteOrder);
                //将数据字节复制到发送数据中去
                encoded.writeBytes(SendData);
                System.arraycopy(SendData, 0, checkData, 3, SendData.length);
                String check = bConvert.checksum(checkData);
                //将校验复制到发送数据中去
                encoded.writeByte(bConvert.hexStringToBytes(check)[0]);
                //将结束标示复制到发送数据中去
                encoded.writeByte(bConvert.hexStringToBytes(EndChar)[0]);
                ctx.writeAndFlush(encoded);
            } catch (Exception ex) {
                log.error("出错日志  记录："+ex.getMessage()+"  信息：ClientSocket错误,SendToByte  byteType:" + byteType + ",byteOrder:" + byteOrder);
            }
        }
    }

/**
    * 结果处理
    * @author 张超 teavamc
    * @date 2019/2/22
    * @param [str]
    * @return java.lang.String
    */
    public String parseResult(String str){
        // 16进制 转 byte[ ]
        byte[] content =bConvert.hexStringToBytes(str);
        String byteType="";
        String byteOrder="";
        String acceptData="";
        if(content.length>0){
//            如果 content[0] 为 aa
            if(bConvert.byteToHexString(content[0]).equals(BeginChar)){
//                数据类型为 content[1] 转换 int
                byteType = bConvert.byteToInt(content[1])+"";
                int byteLength = bConvert.byteToInt(content[2])+bConvert.byteToInt(content[3]);
                byteOrder = bConvert.byteToInt(content[4])+"";
                //接收数据
                byte[] mybuf = new byte[content.length-7];
                for(int i=0;i<content.length-7;i++){
                    mybuf[i] = content[i+5];
                }
                try {
                    acceptData = new String(mybuf, "UTF-8");
                    System.out.println(" 收到："+acceptData);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return byteType+":"+acceptData;
    }
}
