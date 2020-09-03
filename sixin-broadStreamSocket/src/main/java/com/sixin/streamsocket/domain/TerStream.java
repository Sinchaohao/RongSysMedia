package com.sixin.streamsocket.domain;

/**
 * @author 张超 teavamc
 * @Description: 直播流和终端关联模型类
 * @ClassName TerStream
 * @date 2019/2/16 14:35
 **/
public class TerStream {
    private String streamid;//直播流id
    private String terminalid;//终端编号
    private Boolean status;//终端播放状态，0:停止，1：开始

    public TerStream(String streamid, String terminalid, Boolean status) {
        super();
        this.streamid = streamid;
        this.terminalid = terminalid;
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
    public String getTerminalid() {
        return terminalid;
    }
    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((streamid == null) ? 0 : streamid.hashCode());
        result = prime * result
                + ((terminalid == null) ? 0 : terminalid.hashCode());
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
        TerStream other = (TerStream) obj;
        if (streamid == null) {
            if (other.streamid != null)
                return false;
        } else if (!streamid.equals(other.streamid))
            return false;
        if (terminalid == null) {
            if (other.terminalid != null)
                return false;
        } else if (!terminalid.equals(other.terminalid))
            return false;
        return true;
    }
}
