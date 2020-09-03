package com.sixin.streamsocket.domain;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author 张超 teavamc
 * @Description: 流媒体直播 模型类
 * @ClassName Stream
 * @date 2019/2/16 14:35
 **/
public class Stream {
    private String streamid;
    private ChannelHandlerContext ctx;
    private Boolean status = false;//false:停止，true：开始

    public Stream(String streamid){
        this.streamid = streamid;
    }
    public Stream(ChannelHandlerContext ctx,String streamid,Boolean status){
        this.ctx = ctx;
        this.streamid = streamid;
        this.status = status;
    }
    public String getStreamid() {
        return streamid;
    }
    public void setStreamid(String streamid) {
        this.streamid = streamid;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((streamid == null) ? 0 : streamid.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stream other = (Stream) obj;
        if (streamid == null) {
            if (other.streamid != null)
                return false;
        } else if (!streamid.equals(other.streamid))
            return false;
        return true;
    }
    public ChannelHandlerContext getCtx() {
        return ctx;
    }
    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
