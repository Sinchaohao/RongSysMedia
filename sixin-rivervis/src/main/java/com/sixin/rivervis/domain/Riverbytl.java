package com.sixin.rivervis.domain;
/**
 * @function:sk流数据监测
 * @Systemname:融媒体数据管理综合服务平台
 * @Author:刘波
 * @Description:
 * @Date:Created in 2020-01-27 17:43
 * @Unitname:湖南农业大学信息学院物联网工程系
 * @Copyright:融媒体数据管理综合服务平台V3.2.0
 * @Rewriting:刘波
 */
public class Riverbytl {
    private Integer limit;
    private String begintime;
    private String endtime;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Riverbytl{" +
                "limit=" + limit +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}
