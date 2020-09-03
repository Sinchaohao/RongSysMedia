package com.sixin.iot.service.impl;

import com.sixin.iot.domain.EngPannel;
import com.sixin.iot.mapper.EngPannelMapper;
import com.sixin.iot.service.IEngPannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngPannelServiceImpl implements IEngPannelService {
    @Autowired
    private EngPannelMapper engPannelMapper;

    /*
          功能：能源数据分页
          @Param : engPannel
     */
    public List<EngPannel> selectEnergyList(EngPannel engPannel)
    {
        return engPannelMapper.selectEnergyList(engPannel);
    }

    /*
          功能：控制台图表能源数据查询
          @Param : deptId
     */
    public List<EngPannel> selectEnergyListOnControl(String addrid){
        return engPannelMapper.selectEnergyListOnControl(addrid);
    }
}
