package com.sixin.iot.service;

import com.sixin.iot.domain.EngPannel;

import java.util.List;

public interface IEngPannelService {
    /*
          功能：能源数据分页
          @Param : engPannel
     */
    public List<EngPannel> selectEnergyList(EngPannel engPannel);
    /*
          功能：控制台图表能源数据查询
          @Param : deptId地址编号
     */
    public List<EngPannel>selectEnergyListOnControl(String addrid);

}
