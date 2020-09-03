package com.sixin.iot.service.impl;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import com.sixin.common.support.Convert;
import com.sixin.iot.domain.Water;
import com.sixin.iot.mapper.WaterpannelMapper;
import com.sixin.iot.service.IWaterpannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service
public class WaterPannelServiceImpl implements IWaterpannelService{

    @Autowired
    private WaterpannelMapper waterpannelMapper;

    /**
     * 查询所有水质数据
     * @param
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.SXINFOM)
    public List<Water> selectWaterpannelList(Water water){
        return waterpannelMapper.selectWaterpannelList(water);
    }
    /**
     *
     * 控制台水质数据终端按安装时间统计
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    @Override
    public List<Water> ImeiInstallcount(String deptId){
        return waterpannelMapper.ImeiInstallcount(Convert.toLong(deptId));
    }

    /**
     *
     * 控制台水质数据终端按安装时间统计
     * @param  deptId 地址编号
     * @return 水质数据信息
     */
    public List<Water> WaterData(String deptId){
        return waterpannelMapper.WaterData(Convert.toLong(deptId));
    }
}
