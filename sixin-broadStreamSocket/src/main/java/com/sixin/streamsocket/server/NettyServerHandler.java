package com.sixin.streamsocket.server;

import com.sixin.broad.utils.bConvert;
import com.sixin.streamsocket.domain.Stream;
import com.sixin.streamsocket.domain.TerStream;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author 张超 teavamc
 * @Description:TODO
 * @ClassName NettyServerHandler
 * @date 2019/2/16 15:37
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //连接的客户端地址
    String address = "";
    final static String UTF8 = "UTF8";
    // 消息的中止判断符
    public String EndChar = "cc";
    // 消息的开始判断符
    public String BeginChar = "aa";
    private static final Logger log = LoggerFactory.getLogger(NettyServerHandler.class);
    protected int senderlenth = 510;
    long starttime;
    ByteBuf encoded;
    Boolean isEndSocket;//该连接是否是一个结束连接
    private List<Stream> streamlist;
    private List<TerStream> terstreamlist;
    public NettyServerHandler(List<Stream> streamlist, List<TerStream> terstreamlist){
        this.streamlist = streamlist;
        this.terstreamlist = terstreamlist;
    }
    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("完成连接: "+ctx.channel().remoteAddress());

        super.channelActive(ctx);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] content = new byte[result.readableBytes()];
        result.readBytes(content);
        result.release();//释放
//		ReferenceCountUtil.release(result);
        address = ctx.channel().remoteAddress().toString();
        StreamServer.executorService.execute(new Runnable(){
            @Override
            public void run() {
                String acceptData="";
                if(content.length>0){
                    if(bConvert.byteToHexString(content[0]).equals(BeginChar)){
                        String byteType = (int)content[1]+"";
//						System.out.println("终端握手类型："+byteType);
                        int byteLength = (int)content[2]+(int)content[3];
                        String byteOrder = (int)content[4]+"";
                        //接收数据
                        byte[] acceptbyte=null;
                        try {
                            acceptbyte = new byte[content.length-7];
                            System.arraycopy(content, 5, acceptbyte, 0, content.length-7);
                            acceptData = new String(acceptbyte, UTF8);
                        } catch (UnsupportedEncodingException e) {
                            log.error("出错日志  记录："+e.getMessage()+"  信息：Socket错误,channelRead" );
                        }
                        log.debug("跟踪日志  记录："+" 终端握手类型：" + byteType + " 命令：" + byteOrder+"  信息：" + address );
                        AcceptData(ctx,byteType,byteOrder,acceptData,acceptbyte);
                    }
                }
            }

        });

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelInactive(ctx);
        if(isEndSocket){
            for(int i=0;i<streamlist.size();i++){//websocket的client 关闭直播时与服务器的连接，该连接断开时删除该直播id的直播和终端信息
                if(streamlist.get(i).getCtx()==ctx){
                    streamlist.remove(i);
                    i--;
                }
            }
        }

        log.debug("跟踪日志  记录：终端中断连接  信息：" +address);
        ctx.close();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {//异常处理

        log.debug("跟踪日志  记录：终端中断连接  信息：" +address);
        ctx.close();
    }
    public void AcceptData(ChannelHandlerContext ctx,String byteType,String byteOrder,String acceptData,byte[] acceptbyte){
        byte[] ReturnData=null;
        String imei="";
        try{
            //显示
            switch(byteType){
                case "0"://网页发来消息，直播结束，删除直播列表中的该直播id
                    isEndSocket = true;
                    String streamid=acceptData;
                    for(Stream s:streamlist){
                        if(s.getStreamid().equals(streamid)){
                            s.getCtx().close();//收到直播结束时关闭该直播id的开始直播连接,不在发送终端开始直播状态
                            s.setCtx(ctx);
                            s.setStatus(false);
                        }
                    }
                    ReturnData = "".getBytes(UTF8);
                    log.info("正常日志  记录：前台直播结束命令  信息：直播" +streamid);
                    break;
                case "1"://网页发来消息，直播开始
                    isEndSocket = false;
                    String[] rs = acceptData.split(":");
                    String strid="";
                    String imeistrs="";
                    if(rs.length>=2){
                        strid=rs[0];
                        imeistrs=rs[1];
                    }
                    streamlist.add(new Stream(ctx,strid,true));

                    String[] imeis = imeistrs.split(",");
                    for(int i=0;i<imeis.length;i++){
//	        		terstreamlist.add(new TerStream(strid,imeis[i],true));//测试
                        terstreamlist.add(new TerStream(strid,imeis[i],false));
                    }
                    ReturnData = "".getBytes(UTF8);
                    log.info("正常日志  记录：前台直播开始命令  信息：直播" +strid);
                    break;
                case "2"://网页请求终端直播状态
                    String getstreamid=acceptData;
                    String returnstr="";
                    for(TerStream ts:terstreamlist){
                        if(ts.getStreamid().equals(getstreamid)&&ts.getStatus()){//该直播流开始直播的终端
                            returnstr+=ts.getTerminalid()+",";
                        }
                    }
                    if(returnstr.equals(""))
                        ReturnData = "".getBytes(UTF8);
                    else{
                        returnstr = returnstr.substring(0,returnstr.length()-1);
                        ReturnData = returnstr.getBytes(UTF8);
                    }
                    log.info("正常日志  记录：前台请求终端直播状态  信息：直播" +getstreamid);
                    break;
                case "12"://流媒体心跳包
                    imei = acceptData;
//				//测试
//				String stxt = Tools.GetValueByKey(NettyServerHandler.class,Const.CONFIG, "playstatus");
//				streamlist.get(0).setStatus(Boolean.parseBoolean(stxt));
//				//测试-----end

                    String txt="";
                    //查询流直播是否开始
                    for(TerStream ts:terstreamlist){
                        if(ts.getTerminalid().equals(imei)){
                            String sid = ts.getStreamid();
                            int i = streamlist.indexOf(new Stream(sid));
                            if(i>=0 ) {//直播列表中有该直播id
                                if(streamlist.get(i).getStatus() && !ts.getStatus()){//直播开始，终端还没有播放
                                    txt = sid;//流媒体命名规则：时+分+秒 6位
                                }else if(!streamlist.get(i).getStatus() && ts.getStatus()){//直播结束，终端还没有结束
                                    txt = "ending";
                                }
                            }else if(ts.getStatus()){//直播列表中没有该直播id，终端还没有结束
                                txt = "ending";
                            }
                        }
                    }
                    if(txt.equals("")){
                        txt = "nothin";
                    }
                    ReturnData = txt.getBytes(UTF8);

                    System.out.println("发送:"+txt);
                    log.info("正常日志   记录：流媒体心跳包    信息："+ imei+":"+txt);
                    break;
                case "14"://终端发送流媒体播放成功指令
                    String[] rs14 = acceptData.split(":");
                    String res="";
                    if(rs14.length>=2){
                        imei=rs14[0];
                        res=rs14[1];
                    }
                    ReturnData = "".getBytes(UTF8);

                    if(res.equals("OK")){
                        for(int i=0;i<terstreamlist.size();i++){
                            if(terstreamlist.get(i).getTerminalid().equals(imei)){
                                terstreamlist.get(i).setStatus(true);
                            }
                        }

                    }
                    log.info("正常日志   记录：流媒体播放成功    信息："+ imei);
                    break;
                case "15"://终端发送终止流媒体播放成功指令
                    String[] rs15 = acceptData.split(":");
                    String result="";
                    if(rs15.length>=2){
                        imei=rs15[0];
                        result=rs15[1];
                    }
                    ReturnData = "".getBytes(UTF8);
                    if(result.equals("OK")){
                        for(int i=0;i<terstreamlist.size();i++){
                            if(terstreamlist.get(i).getTerminalid().equals(imei)){
                                terstreamlist.get(i).setStatus(false);
                                int index = streamlist.indexOf(new Stream(terstreamlist.get(i).getStreamid()));//该直播被删除后
                                if(index<0){
                                    terstreamlist.remove(i);
                                    i--;//删掉一个，迭代减1
                                }
                            }
                        }
                    }
                    log.info("正常日志   记录：流媒体终止播放成功    信息："+ imei);
                    break;
            }
            SendToByte(ctx,byteType,byteOrder,ReturnData);
        } catch (UnsupportedEncodingException e) {
            log.error("出错日志  记录："+e.getMessage()+"  信息：Socket错误,AcceptData" );
        }
    }
    private void SendToByte(ChannelHandlerContext ctx,String byteType,String byteOrder, byte[] SendData){
        if (SendData != null && SendData.length != 0){
            byte[] checkData = new byte[SendData.length+3];//用来计算校验和
            try {
                int buffersize = 7+SendData.length;
                encoded = ctx.alloc().buffer(buffersize);//建立发送数据字节数组
                //加上包头尾
                encoded.writeByte(bConvert.hexStringToBytes(BeginChar)[0]);//将开始标示复制到发送数据中去
                encoded.writeByte((byte)Integer.parseInt(byteType));//将类型复制到发送数据中去
                byte[] l = bConvert.hexStringToBytes(Integer.toHexString(SendData.length+2));
                if(l.length==1){
                    encoded.writeByte(l[0]);//将包长复制到发送数据中去
                    encoded.writeByte((byte)0);
                    checkData[0]=l[0];
                    checkData[1]=(byte)0;
                }else if(l.length==2){
                    encoded.writeByte(l[1]);//将包长复制到发送数据中去
                    encoded.writeByte(l[0]);
                    checkData[1]=l[0];
                    checkData[0]=l[1];
                }
                encoded.writeByte((byte)Integer.parseInt(byteOrder));//将命令设置到发送数据中去
                checkData[2] = (byte)Integer.parseInt(byteOrder);
                encoded.writeBytes(SendData);//将数据字节复制到发送数据中去
                System.arraycopy(SendData, 0, checkData, 3, SendData.length);
                String check = bConvert.checksum(checkData);
                encoded.writeByte(bConvert.hexStringToBytes(check)[0]);//将校验复制到发送数据中去
                encoded.writeByte(bConvert.hexStringToBytes(EndChar)[0]);//将结束标示复制到发送数据中去

                ctx.writeAndFlush(encoded);
            } catch (Exception ex) {
                log.error("出错日志  记录："+ex.getMessage()+"  信息：Socket错误,SendToByte  byteType:" + byteType + ",byteOrder:" + byteOrder);
            }
        }else{
            try {
                int buffersize = 7;
                encoded = ctx.alloc().buffer(buffersize);//建立发送数据字节数组
                //加上包头尾
                encoded.writeByte(bConvert.hexStringToBytes(BeginChar)[0]);//将开始标示复制到发送数据中去
                encoded.writeByte((byte)Integer.parseInt(byteType));//将类型复制到发送数据中去
                byte[] l = bConvert.hexStringToBytes(Integer.toHexString(2));
                encoded.writeByte( l[0]);
                encoded.writeByte( (byte)0);
                encoded.writeByte( (byte)1);
                encoded.writeByte( bConvert.hexStringToBytes("1e")[0]);//将校验复制到发送数据中去
                encoded.writeByte( bConvert.hexStringToBytes(EndChar)[0]);//将结束标示复制到发送数据中去
                ctx.writeAndFlush(encoded);
            } catch (Exception ex) {
                log.error("出错日志  记录："+ex.getMessage()+"  信息：Socket错误,SendToByte  byteType:" + byteType + ",byteOrder:" + byteOrder );
            }
        }
    }
}
