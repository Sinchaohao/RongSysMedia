package com.sixin.broadserver.domain;

import org.apache.mina.core.session.IoSession;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Date;
/*终端通信缓存类*/
public class SocketInfo {
    private String imei; //终端IMEI号
    private String type;//终端类型

    private IoSession heartioSession;//终端心跳连接对象信息 8800端口
    private IoSession iotSession;//终端物联网信息连接对象 8900端口
    private IoSession commandSession;//终端命令连接对象信息 8600端口

    private InetAddress  ipAddress;//终端IP地址
    private Date lastTime; //最后通信时间


    private ByteBuffer byteFile = null; //老终端发送的文件信息

//    private byte[] byteFile; //老终端发送的文件信息
    private String channelAddress;
    private Integer byteCount=0;//标志当前文件发送到哪个位置
    private String filelength; //设置当前发送文件的总字节数


    public String getImei() {
        return imei;
    }
    public void setImei(String imei) {
        this.imei = imei;
    }


    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public IoSession getHeartioSession() {
        return heartioSession;
    }

    public void setHeartioSession(IoSession heartioSession) {
        this.heartioSession = heartioSession;
    }

    public IoSession getIotSession() {
        return iotSession;
    }

    public void setIotSession(IoSession iotSession) {
        this.iotSession = iotSession;
    }

    public IoSession getCommandSession() {
        return commandSession;
    }

    public void setCommandSession(IoSession commandSession) {
        this.commandSession = commandSession;
    }

    public ByteBuffer getByteFile() {
        return byteFile;
    }

    public void setByteFile(ByteBuffer byteFile) {
        this.byteFile = byteFile;
    }

    public Integer getByteCount() {
        return byteCount;
    }

    public void setByteCount(Integer byteCount) {
        this.byteCount = byteCount;
    }

    public String getChannelAddress() {
        return channelAddress;
    }

    public void setChannelAddress(String channelAddress) {
        this.channelAddress = channelAddress;
    }

    public String getFilelength() {
        return filelength;
    }

    public void setFilelength(String filelength) {
        this.filelength = filelength;
    }

    /*做等同判断*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SocketInfo other = (SocketInfo) obj;
        if (imei == null) {
            if (other.imei != null){
                return false;
            }
        } else if (!imei.equals(other.imei)){
            return false;
        }
        return true;
    }
    public IoSession getsession(IoSession session){
        if(heartioSession != null &&heartioSession.equals(session)){
            return heartioSession;
        }else if(iotSession != null &&iotSession.equals(session)){
            return iotSession;
        }else if(commandSession != null &&commandSession.equals(session)){
            return commandSession;
        }else {
            return  null;
        }
    }
}

