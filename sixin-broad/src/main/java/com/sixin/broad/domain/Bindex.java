package com.sixin.broad.domain;

import com.sixin.common.base.BaseEntity;

/**
 * @author 张超 teavamc
 * @Description:前台数据的封装模型
 * @ClassName BindexMapper
 * @date 2019/1/26 13:06
 **/
public class Bindex{

    private static final long serialVersionUID = 1L;

    private String dev;
    private String run;
    private String stop;
    private String aid;

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Bindex{" +
                "dev='" + dev + '\'' +
                ", run='" + run + '\'' +
                ", stop='" + stop + '\'' +
                ", aid='" + aid + '\'' +
                '}';
    }
}
