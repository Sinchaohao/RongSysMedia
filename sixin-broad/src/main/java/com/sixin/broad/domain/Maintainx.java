package com.sixin.broad.domain;

/**
 * @author 张超
 * @ClassName Maintainx
 * @Description:设备维修表和设备管理表综合数据
 * @date 2019/1/25 19:21
 **/
public class Maintainx {

    private static final long serialVersionUID = 1L;

    /** 设备ID */
    private String tid;
    /** 设备保修内容 */
    private String content;
    /** 保修时间 */
    private String ctime;
    /** 经度 */
    private Double lo;
    /** 纬度 */
    private Double la;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    @Override
    public String toString() {
        return "Maintainx{" +
                "tid='" + tid + '\'' +
                ", content='" + content + '\'' +
                ", ctime='" + ctime + '\'' +
                ", lo=" + lo +
                ", la=" + la +
                '}';
    }
}
