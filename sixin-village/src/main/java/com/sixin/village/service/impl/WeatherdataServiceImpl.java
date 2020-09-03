package com.sixin.village.service.impl;

import java.util.List;

import com.sixin.common.annotation.DataSource;
import com.sixin.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sixin.village.mapper.WeatherdataMapper;
import com.sixin.village.domain.Weatherdata;
import com.sixin.village.service.IWeatherdataService;

/**
 * 采集天气信息 服务层实现
 *
 * @author hfz
 * @date 2019-10-06
 */
@Service
public class WeatherdataServiceImpl implements IWeatherdataService
{
    @Autowired
    private WeatherdataMapper weatherdataMapper;

    /**
     * 查询采集天气信息id
     *
     * @param userid 采集天气信息
     * @return 天气信息集合
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Weatherdata> selectWeatherdataList(String userid)
    {
        return weatherdataMapper.selectWeatherdataList(userid);
    }

}
