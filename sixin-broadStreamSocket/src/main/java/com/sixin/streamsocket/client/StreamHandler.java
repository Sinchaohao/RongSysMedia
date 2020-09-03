package com.sixin.streamsocket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 张超 teavamc
 * @Description: 流媒体 WebSocket 处理类
 * @ClassName StreamHandler
 * @date 2019/2/16 15:57
 **/
public class StreamHandler  implements WebSocketHandler {
    // WebSocket 内容
    private final ArrayList<WebSocketSession> streams = new ArrayList<WebSocketSession>();
    // 流媒体业务 id
    private final ArrayList<String> streamids = new ArrayList<String>();
    // 流媒体业务 状态
    private final ArrayList<Boolean> streamstates = new ArrayList<Boolean>();

    //	private final ArrayList<VlcStreamThread> vlcthreads = new ArrayList<VlcStreamThread>();//vlc直播线程

    //声明线程池ExecutorService，创建一个定长线程池，线程数100，可控制线程最大并发数，超出的线程会在队列中等待。
    ExecutorService executorService =  Executors.newFixedThreadPool(100);

    protected static final Logger log = LoggerFactory.getLogger(StreamHandler.class);

    /**
        * 断开连接后触发的回调
        * 断开连接后触发的回调
        * 断开连接后触发的回调
        * @author 张超 teavamc
        * @date 2019/2/21
        * @param session arg1
        * @return void
        */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus arg1)
            throws Exception {
        // 遍历 WebSocket 数组，
        for(int i=0;i<streams.size();i++){
            if(session==streams.get(i)){
                if(streamids.size()>i){
                    String sid = streamids.get(i);
                    streamids.remove(i);
                    if(streamstates.size()>i){
                        if(streamstates.get(i)){
                            //ws连接结束，直播未关闭则关闭直播
                            sendCMDtoSocket(session,"end:"+sid);
                            streamstates.set(i, false);
                        }
                        streamstates.remove(i);
                    }
                }
            }
        }
        streams.remove(session);
        if(session.isOpen()) session.close();
        log.info( "正常日志："+session.getRemoteAddress() + " 断开连接!" );
    }

    /**
        * 建立连接后触发的回调
        * 建立连接后触发的回调
        * 建立连接后触发的回调
        * @author 张超 teavamc
        * @date 2019/2/20
        * @param [session]
        * @return void
        */
    @Override
    public void afterConnectionEstablished(WebSocketSession session)
            throws Exception {
        streams.add(session);
        log.info( "正常日志："+session.getRemoteAddress() + ": 打开连接！"  );
    }

    /**
        * 收到消息时触发的回调
        * 收到消息时触发的回调
        * 收到消息时触发的回调
        * @author 张超 teavamc
        * @date 2019/2/21
        * @param [conn, message]
        * @return void
        */
    @Override
    public void handleMessage(WebSocketSession conn, WebSocketMessage<?> message)
            throws Exception {
        System.out.println("String message: "+message.getPayload());
//        按：分割字符串数组
        String[] rs = message.getPayload().toString().split(":");
//        conn 中的 [0] 为指令
        String commd="";
//        conn 中的 [1] 为 streamid
        String streamid="";
//        conn 中的 [2] 为imeilist
        String imeilist="";
        if(rs.length>=2){
            commd=rs[0];
            streamid=rs[1];
            if(rs.length>2)
                imeilist=rs[2];
        }
        switch(commd){
            case "start":
//                遍历 stream ， 如果 conn 存在 streams 中，添加streamid和state
                for(int i=0;i<streams.size();i++){
                    if(conn==streams.get(i)){
                        streamids.add(i, streamid);
                        streamstates.add(i,true);
                    }
                }
                /**向页面发送连接成功通知*/
                conn.sendMessage(new TextMessage("start:success"));
                log.info( "正常日志："+conn.getRemoteAddress() + ":开始直播 ");
                break;
            case "end":
//                遍历 stream ， 如果 conn 存在 streams 中，添加streamid和state
                for(int i=0;i<streams.size();i++){
                    if(conn==streams.get(i)){
                        if(streamstates.size()>i){
                            streamstates.add(i,false);
                        }
                    }
                }
                conn.sendMessage(new TextMessage("end:success"));
                log.info( "正常日志："+ conn.getRemoteAddress() + ":结束直播 ");
                break;
                default:
                    log.info("错误日志：页面发送命令出错！");
                    break;
        }
//        发送指令给终端
        sendCMDtoSocket(conn,message.getPayload().toString());
//        打印目标地址 和 信息内容
        System.out.println( conn.getRemoteAddress() + ": " + message.getPayload() );
    }

    /**
        * 发送指令 描述
        * @author 张超 teavamc
        * @date 2019/2/21
        * @param [conn, msg]
        * @return void
        */
    private void sendCMDtoSocket(WebSocketSession conn,String msg){
        // 线程池开启 新线程
        executorService.execute(new Runnable(){

            @Override
            public void run() {
                try {
//                    面向 服务器上 SocketServer NettyServer 服务器 发送业务信息
                    new NettyClient(conn,msg).start();
                } catch (Exception e) {
                    log.error( "错误日志："+e.getMessage()+ " ");
                    try {
                        conn.sendMessage(new TextMessage("error:socketconnect"));
                    } catch (IOException e1) {
                        log.error( "错误日志："+e1.getMessage()+ " ");
                    }
                }
            }
        });
    }

    /**
        * 传输消息出错时触发的回调
        * 传输消息出错时触发的回调
        * 传输消息出错时触发的回调
        * @author 张超 teavamc
        * @date 2019/2/22
        * @param [session, arg1]
        * @return void
        */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable arg1)
            throws Exception {
        //关闭Session
        if(session.isOpen()){
            session.close();
        }
        log.error( "出错日志: IP:" +session.getRemoteAddress()+"  "+ arg1.getMessage() );
    }

    /**
        * 是否处理分片消息
        * 是否处理分片消息
        * 是否处理分片消息
        * @author 张超 teavamc
        * @date 2019/2/22
        * @param []
        * @return boolean
        */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}
