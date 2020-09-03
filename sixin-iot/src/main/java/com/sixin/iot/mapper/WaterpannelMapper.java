package com.sixin.iot.mapper;

import com.sixin.iot.domain.Water;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface WaterpannelMapper {

    /**
     * 查询所有水质数据记录
     *
     * @param
     *
     */
    public List<Water> selectWaterpannelList(Water water);
    /**
     *
     * 控制台水质数据终端按安装时间统计
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    public List<Water> ImeiInstallcount(@Param("deptid")Long deptId);

    /**
     *
     * 控制台水质数据终端按安装时间统计
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    public List<Water> WaterData(@Param("deptid")Long deptId);
}
