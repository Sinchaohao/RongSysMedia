package com.sixin.iot.service;

import com.sixin.iot.domain.Water;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IWaterpannelService {

    /**
     * 查询所有水质数据
     *
     */
    public List<Water> selectWaterpannelList(Water water);
    /**
     *
     * 控制台水质数据终端按安装时间统计
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    public List<Water> ImeiInstallcount(String deptId);

    /**
     *
     * 控制台水质数据
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    public List<Water> WaterData(String deptId);
}
